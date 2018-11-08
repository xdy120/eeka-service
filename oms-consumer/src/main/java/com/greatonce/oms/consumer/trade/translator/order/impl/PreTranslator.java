package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.setting.OrderAuditStrategy;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.mall.MallStepOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * company:Shenzhen Greatonce Co Ltd
 * remark: 预转化器
 *
 * @author:buer
 */
@Component("orderPreTranslator")
@TranslatorOrderCondition
public class PreTranslator implements OrderTranslatable {

  /**
   * 自定义预处理异常
   */
  private class PreTranslateException extends Exception {

    PreTranslateException(String message) {
      super(message);
    }
  }

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  @Autowired
  private StoreService storeService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private MallSalesOrderService mallSalesOrderService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;

  @Override
  public void save(OrderTranslatableContext context) {
    MallSalesOrder mallSalesOrder = new MallSalesOrder();
    mallSalesOrder.setMallSalesOrderId(context.getMallSalesOrder().getMallSalesOrderId());
    mallSalesOrder.setStatus(context.getMode() == OrderTranslatableMode.ERROR ?
        MallDataProcessStatus.FAILED : MallDataProcessStatus.SUCCESS);
    mallSalesOrderService.modify(mallSalesOrder);
  }

  @Override
  public void rollback(OrderTranslatableContext context) {

  }

  /**
   * 预转化逻辑：
   * 初始化context，筛除店铺异常和异常的mall单。
   * 新订单只转入已付订单，旧订单如果是作废不再转化。
   */
  @Override
  public void translate(OrderTranslatableContext context) {
    //初始化context
    Store store = storeService.getByKey(context.getMallSalesOrder().getStoreId());
    context.setSalesOrderSetting(settingService.getSalesOrderSetting());
    context.setStore(store);
    context.setOrderAuditStrategy(
        settingService.getOrderAuditStrategy(store.getSetting().getAuditStrategy()));
    context.setStockDispatchStrategy(
        stockDispatchStrategyService.getByKey(store.getSetting().getStockStrategy()));
    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "订单开始进行转化");
    try {
      //过滤订单
      checkQuery(context);
    } catch (PreTranslateException e) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "订单转化失败：{}", e.getMessage());
      context.setMode(OrderTranslatableMode.ERROR);
    }

    if (context.getMode() == OrderTranslatableMode.CREATE) {
      SalesOrder salesOrder = new SalesOrder();
      salesOrder.setSub(new SalesOrderSub());
      context.setSalesOrder(salesOrder);
    }
  }

  /**
   * 订单检查过滤器
   */
  private void checkQuery(OrderTranslatableContext context) throws PreTranslateException {
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    Store store = context.getStore();
    if (store == null || !store.isEnable()) {
      throw new PreTranslateException("店铺不存在或已禁用");
    }
    if (context.getOrderAuditStrategy() == null) {
      throw new PreTranslateException("店铺没有审单策略");
    }
    if (mallSalesOrderInfo.getDetails().isEmpty()) {
      throw new PreTranslateException("待转化订单明细为空");
    }
    if (mallSalesOrderInfo.getCreateType() == SalesOrderCreateType.DOWNLOAD
        && mallSalesOrderInfo.getPaidTime() != null) {
      //按审单策略拦截时间进行
      OrderAuditStrategy auditStrategy = context.getOrderAuditStrategy();
      if (Assert.isTrue(auditStrategy.isEnable()) && auditStrategy.getInterceptPoint() != null
          && mallSalesOrderInfo.getPaidTime().isBefore(auditStrategy.getInterceptPoint())) {
        throw new PreTranslateException(
            "支付时间：" + DateTimeUtil.format(mallSalesOrderInfo.getPaidTime())
                + " 在策略拦截时间：" + DateTimeUtil.format(auditStrategy.getInterceptPoint())
                + "之前，订单不进入系统");
      }
    }
    Long salesOrderId = salesOrderService.getSalesOrderIdByStoreIdAndTradeId(store.getStoreId(),
        mallSalesOrderInfo.getTradeId());
    if (salesOrderId == null) {
      //order为空则是新订单
      mallSalesOrderStatusFilter(mallSalesOrderInfo, context);
    } else {
      //旧订单，本地有记录
      SalesOrder order = salesOrderService.getFullInfo(salesOrderId);
      context.setSalesOrder(order);

      if (order.getStatus() == SalesOrderStatus.INVALID) {
        context.setMode(OrderTranslatableMode.ABANDON);
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "该订单已在系统中，并且已作废，转化切为放弃模式");
      } else {
        if (mallSalesOrderInfo.getStepTradeStatus() == MallStepOrderStatus.DEPOSIT
            && Assert.isTrue(order.getSub().isPrepay())
            && order.getPayStatus() == SalesOrderPayStatus.PREPAY) {
          throw new PreTranslateException("首付款预付订单重复转化");
        } else if (mallSalesOrderInfo.getStepTradeStatus() == MallStepOrderStatus.RETAINAGE
            && Assert.isTrue(order.getSub().isPrepay())
            && order.getPayStatus() == SalesOrderPayStatus.PAID) {
          throw new PreTranslateException("付尾款预付订单重复转化");
        }

        context.setMode(OrderTranslatableMode.MODIFY);
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "该订单已在系统中,转化切为修改模式");

        //salesOrder的修改时间比mall的订单修改时间还晚，说明这条转化记录早被改过
        if (order.getSub().getMallModifiedTime().isAfter(mallSalesOrderInfo.getModifiedTime())) {
          throw new PreTranslateException("本地订单版本比转化订单高，不做修改");
        }
      }
    }
  }

  /**
   * 平台订单状态过滤.
   * 如果是系统下载订单，只有已付款订单可以进入系统
   */
  private void mallSalesOrderStatusFilter(MallSalesOrderInfo mallSalesOrderInfo,
      OrderTranslatableContext context) throws PreTranslateException {
    if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.UNKNOWN) {
      throw new PreTranslateException("未知状态订单不进入系统");
    } else if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.TRADE_NO_CREATE_PAY) {
      throw new PreTranslateException("未创建交易订单不进入系统");
    } else if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.WAIT_BUYER_PAY
        || mallSalesOrderInfo.getPaidTime() == null) {
      //未付款订单，且不是预付款和货到付款不能进入
      if (mallSalesOrderInfo.getStepTradeStatus() == null && !mallSalesOrderInfo.isCod()) {
        throw new PreTranslateException("订单未付款");
      } else if (mallSalesOrderInfo.isCod()) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mallSalesOrderInfo.getTradeId(),
            "未付款订单为到付");
      } else if (mallSalesOrderInfo.getStepTradeStatus() != null) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mallSalesOrderInfo.getTradeId(),
            "预付款订单:{}", mallSalesOrderInfo.getStepTradeStatus().caption());
        if (!Assert.isTrue(context.getStore().getSetting().isEnablePrepayOrder())
            && mallSalesOrderInfo.getStepTradeStatus() == MallStepOrderStatus.DEPOSIT) {
          throw new PreTranslateException("店铺未启用预付款订单，不下载付首款订单");
        }
      }
    } else if (mallSalesOrderInfo.getCreateType() == SalesOrderCreateType.DOWNLOAD) {
      if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.TRADE_BUYER_SIGNED) {
        throw new PreTranslateException("订单已签收但OMS无记录");
      } else if (mallSalesOrderInfo.getStatus()
          == MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS) {
        throw new PreTranslateException("订单已发货但OMS无记录");
      } else if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.TRADE_FINISHED) {
        throw new PreTranslateException("订单已完成但OMS无记录");
      } else if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.TRADE_CLOSE) {
        throw new PreTranslateException("订单关闭但OMS无记录");
      } else {
        LOGGER.debug(context.getSerialNo(), context.getStore(), mallSalesOrderInfo.getTradeId(),
            "已付款订单");
      }
    }
  }
}
