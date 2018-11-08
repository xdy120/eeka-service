package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.impl.trade.SalesOrderUtil;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.trade.MallFinishBO;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.ExpressStrategyRule.StrategyExpress;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting.SalesMessageDeleteRule;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting.StopUpdateSalesMessageTime;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.mall.MallStepOrderStatus;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OrderInfoTranslator
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/26
 * 主订单转化器
 */
@Component("orderInfoTranslator")
@TranslatorOrderCondition
public class OrderInfoTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.SALES_ORDER);
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private ExpressService expressService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;

  @Override
  public void save(OrderTranslatableContext context) {
    SalesOrder salesOrder = context.getSalesOrder();
    LOGGER
        .debug(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
            "保存转化信息，转化模式:{}", context.getMode());
    switch (context.getMode()) {
      case CREATE:
        if (context.isNeedDelivery()) {
          SalesOrderUtil.recountStatus(salesOrder, salesOrder.getDetails());
          if (salesOrder.getDispatchStatus() != DispatchStatus.ALL) {
            if (context.isPassAudited()) {
              salesOrder.setStatus(SalesOrderStatus.AUDITED);
            } else {
              salesOrder.setStatus(SalesOrderStatus.AUDITED_ABNORMAL);
            }
          }
        }
        salesOrderService.autoCreate(salesOrder);
        break;
      case MODIFY:
        if (context.isPrepayFinalPaid()) {
          salesOrderDetailService.batchModify(salesOrder.getDetails());
          List<SalesOrderDetail> orderDetails = salesOrderDetailService
              .listBySalesOrderId(salesOrder.getSalesOrderId());
          orderDetails.forEach(x -> salesOrder.getDetails().removeIf(z ->
              z.getSalesOrderDetailId() != null &&
                  z.getSalesOrderDetailId().equals(x.getSalesOrderDetailId())));
          salesOrderDetailService.batchCreate(salesOrder.getDetails());
          salesOrderService.finalPaidUpdateOrder(salesOrder);
        }
        break;
      case CLOSE:
        VersionBO bo = new VersionBO();
        bo.setVersion(salesOrder.getVersion());
        salesOrderService.invalid(salesOrder, bo);
        break;
      case FINISH:
        MallFinishBO finishBO = new MallFinishBO();
        finishBO.setVersion(salesOrder.getVersion());
        finishBO.setFinishTime(salesOrder.getSub().getMallFinishedTime());
        salesOrderService.mallFinish(salesOrder, finishBO);
        break;
      default:
        break;
    }
    if (!context.getLogs().isEmpty()) {
      for (String log : context.getLogs()) {
        BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "转化", log);
      }
    }
  }

  @Override
  public void rollback(OrderTranslatableContext context) {

  }

  /**
   * 主订单信息的转化逻辑：
   *
   * 包括不同转化模式下的处理
   * 新建：创建新的订单，并封装SalesOrder和SalesOrderSub的信息
   * 修改：找到已存在的订单，在OrderInfoTranslator中只处理买家和卖家留言
   * 完成：找到已存在的订单，添加平台完成时间，停止转化
   * 关闭：停止转化
   */
  @Override
  public void translate(OrderTranslatableContext context) {
    //判断订单转化模式
    LOGGER
        .debug(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
            "主订单转化，转化模式：{}", context.getMode());
    if (context.getMode() == OrderTranslatableMode.CREATE) {
      createOrder(context);
    } else if (context.getMode() == OrderTranslatableMode.MODIFY) {
      switch (context.getMallSalesOrderInfo().getStatus()) {
        case TRADE_FINISHED:
          finishOrder(context);
          break;
        case TRADE_CLOSE:
          closeOrder(context);
          break;
        default:
          modifyOrder(context);
      }
    }
  }

  /**
   * 创建订单.
   * <p/>
   * 逻辑：
   * 推荐仓库从库存配货策略中拿
   */
  private void createOrder(OrderTranslatableContext context) {
    Store store = context.getStore();
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    SalesOrder salesOrder = context.getSalesOrder();
    SalesOrderSub orderSub = salesOrder.getSub();

    StockDispatchStrategy dispatchStrategy = context.getStockDispatchStrategy();
    StockDispatchWarehouse warehouse = dispatchStrategy.getRule().getWarehouses().get(0);
    //初始化推荐仓库
    salesOrder.setSuggestWarehouseId(warehouse.getWarehouseId());
    salesOrder.setSuggestWarehouseName(warehouse.getWarehouseName());
    salesOrder.setSuggestVirtualWarehouseId(warehouse.getVirtualWarehouseId());
    salesOrder.setSuggestVirtualWarehouseName(warehouse.getVirtualWarehouseName());
    Express express = expressService.getEffectiveByCode(mallSalesOrderInfo.getExpressCode());
    if (!Assert.isEmpty(mallSalesOrderInfo.getExpressCode()) && express != null) {
      List<StrategyExpress> expresses = stockDispatchStrategyService
          .listExpresses(dispatchStrategy.getStockDispatchStrategyId());
      if (expresses.stream().anyMatch(x -> x.getExpressId().equals(express.getExpressId()))) {
        salesOrder.setSuggestExpressId(express.getExpressId());
        salesOrder.setSuggestExpressName(express.getExpressName());
        salesOrder.setSuggestExpressNo(mallSalesOrderInfo.getExpressNo());
      }
    } else {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "推荐快递不存在或推荐快递不在店铺库存配货策略中,不设置");
    }
    //封装SalesOrderSub信息
    orderSub.setMallType(store.getMallType());
    orderSub.setCreateType(mallSalesOrderInfo.getCreateType());
    orderSub.setMallCreatedTime(mallSalesOrderInfo.getCreatedTime());
    orderSub.setMallModifiedTime(mallSalesOrderInfo.getModifiedTime());
    orderSub.setMallPaidTime(mallSalesOrderInfo.getPaidTime());
    orderSub.setMallFinishedTime(mallSalesOrderInfo.getFinishedTime());
    orderSub.setExpressFee(mallSalesOrderInfo.getExpressFee());
    orderSub.setFreightRiskFee(mallSalesOrderInfo.getFreightRiskFee());
    orderSub.setCurrencyCode(mallSalesOrderInfo.getCurrencyCode() != null ?
        mallSalesOrderInfo.getCurrencyCode() : "RMB");
    orderSub.setCreateType(
        !Assert.isNull(mallSalesOrderInfo.getCreateType()) ? mallSalesOrderInfo.getCreateType()
            : SalesOrderCreateType.DOWNLOAD);
    orderSub.setSourceType(mallSalesOrderInfo.getSourceType());
    orderSub.setSalesOrderType(mallSalesOrderInfo.getOrderType());
    orderSub.setShoppingGuide(mallSalesOrderInfo.getShoppingGuide());
    orderSub.setBuyerEmail(mallSalesOrderInfo.getBuyerEmail());
    orderSub.setBuyerMemo(mallSalesOrderInfo.getBuyerMemo());
    if (!Assert.isEmpty(mallSalesOrderInfo.getSellerMemo())) {
      orderSub.setSellerMemo(mallSalesOrderInfo.getSellerMemo().length() < 500
          ? mallSalesOrderInfo.getSellerMemo()
          : mallSalesOrderInfo.getSellerMemo().substring(0, 450).concat("，注意：备注过长被OMS截取！！"));
    }
    orderSub.setHasInvoice(mallSalesOrderInfo.isHasInvoice());
    orderSub.setCod(mallSalesOrderInfo.isCod());
    orderSub.setPrepay(false);
    orderSub.setBlacklist(false);
    orderSub.setThirdDelivery(false);
    orderSub.setPresellType(PresellType.NONE);
    orderSub.setWeight(0D);
    orderSub.setNewMember(false);

    //封装SalesOrder信息
    salesOrder.setSalesOrderId(salesOrderService.getIdGenerator().next());
    salesOrder.setTradeId(mallSalesOrderInfo.getTradeId());
    salesOrder.setDistributionTradeId(mallSalesOrderInfo.getDistributionTradeId());
    salesOrder.setPayStatus(mallSalesOrderInfo.getStepTradeStatus() == null
        || mallSalesOrderInfo.getStepTradeStatus() == MallStepOrderStatus.RETAINAGE ?
        SalesOrderPayStatus.PAID : SalesOrderPayStatus.PREPAY);

    if (salesOrder.getPayStatus() == SalesOrderPayStatus.PAID) {
      salesOrder.setDeliveryStatus(
          mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS ?
              DeliveryStatus.NONE : DeliveryStatus.ALL);
    } else {
      salesOrder.setDeliveryStatus(DeliveryStatus.NONE);
    }

    salesOrder.setDispatchStatus(salesOrder.getDeliveryStatus() == DeliveryStatus.ALL ?
        DispatchStatus.ALL : DispatchStatus.NONE);
    salesOrder.setStatus(salesOrder.getDeliveryStatus() == DeliveryStatus.ALL ?
        SalesOrderStatus.DELIVERED : SalesOrderStatus.CREATED);
    salesOrder.setStoreId(store.getStoreId());
    salesOrder.setStoreName(store.getStoreName());
    salesOrder.setMallSalesOrderStatus(mallSalesOrderInfo.getStatus());
    salesOrder.setForceSplit(false);
    salesOrder.setHold(false);
    salesOrder.setManual(false);
    salesOrder.setOos(false);
    salesOrder.setUrgent(false);
    salesOrder.setRefundStatus(RefundStatus.NONE);
    salesOrder.setQuantity(0);
    //主订单金额信息
    setMoney(salesOrder, mallSalesOrderInfo);

    if (mallSalesOrderInfo.getStepTradeStatus() != null) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "订单为预付款订单");
      orderSub.setPrepay(true);
    }
    if (SalesOrderType.EXPENSE.equals(orderSub.getSalesOrderType())) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "订单为费用订单，发货状态：全部发货");
      salesOrder.setDeliveryStatus(DeliveryStatus.ALL);
    }
    if (mallSalesOrderInfo.isThirdDelivery()) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "订单为第三方订单，配货状态：全部配货，发货状态：全部发货");
      salesOrder.setDispatchStatus(DispatchStatus.ALL);
      salesOrder.setDeliveryStatus(DeliveryStatus.ALL);
      orderSub.setThirdDelivery(true);
    }
    context.getLogs().add("订单转入系统");
  }

  /**
   * 完成订单
   */
  private void finishOrder(OrderTranslatableContext context) {
    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "订单在平台已完成，转化切为完成模式");
    context.getSalesOrder().getSub()
        .setMallFinishedTime(context.getMallSalesOrderInfo().getFinishedTime());
    context.setMode(OrderTranslatableMode.FINISH);
  }

  /**
   * 关闭订单
   */
  private void closeOrder(OrderTranslatableContext context) {
    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "订单在平台已关闭，转化切为关闭模式");
    context.setMode(OrderTranslatableMode.CLOSE);
  }

  /**
   * 修改订单.
   */
  private void modifyOrder(OrderTranslatableContext context) {
    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "订单已存在，进行订单修改");
    SalesOrder salesOrder = context.getSalesOrder();
    SalesOrderSetting salesOrderSetting = context.getSalesOrderSetting();
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    SalesOrderSub orderSub = salesOrder.getSub();
    orderSub.setMallModifiedTime(mallSalesOrderInfo.getModifiedTime());

    //付尾款的预付款订单判断
    context.setPrepayFinalPaid(Assert.isTrue(salesOrder.getSub().isPrepay())
        && mallSalesOrderInfo.getStepTradeStatus() == MallStepOrderStatus.RETAINAGE);
    if (context.isPrepayFinalPaid()) {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "预付款订单付尾款");
      setMoney(salesOrder, mallSalesOrderInfo);
      salesOrder.getSub().setMallPaidTime(mallSalesOrderInfo.getPaidTime());
      salesOrder.setMallSalesOrderStatus(mallSalesOrderInfo.getStatus());
      salesOrder.setPayStatus(SalesOrderPayStatus.PAID);
      salesOrder.getDetails()
          .stream().filter(x -> !Assert.isEmpty(x.getMallDetailId())).forEach(x ->
          mallSalesOrderInfo.getDetails().forEach(z -> {
            if (x.getMallDetailId().equals(z.getOid())) {
              x.setMallSalesOrderDetailStatus(z.getStatus());
            }
          }));
    }

    if (!Assert.isEmpty(mallSalesOrderInfo.getBuyerMemo()) && !mallSalesOrderInfo.getBuyerMemo()
        .equalsIgnoreCase(orderSub.getBuyerMemo())) {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "订单有买家留言:{}", mallSalesOrderInfo.getBuyerMemo());
      orderSub.setBuyerMemo(mallSalesOrderInfo.getBuyerMemo());
      context.getLogs().add("修改买家备注");
    }
    //卖家留言根据订单配置判断
    if (salesOrderSetting.getStopUpdateSalesMessageTime() == StopUpdateSalesMessageTime.CREATED) {
      return;
    } else if (
        salesOrderSetting.getStopUpdateSalesMessageTime() == StopUpdateSalesMessageTime.PREPARED
            && SalesOrderUtil.inStatus(salesOrder, SalesOrderUtil.BEFORE_DISPATCH_STATUS)) {
      return;
    } else if (
        salesOrderSetting.getStopUpdateSalesMessageTime() == StopUpdateSalesMessageTime.DELIVERED
            && SalesOrderUtil.inStatus(salesOrder, SalesOrderUtil.BEFORE_DELIVERY_STATUS)) {
      return;
    }
    if (!Assert.isEmpty(mallSalesOrderInfo.getSellerMemo())) {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "订单有卖家留言:{}", mallSalesOrderInfo.getSellerMemo());
      if (salesOrderSetting.getSalesMessageDeleteRule() == SalesMessageDeleteRule.REPLACE) {
        orderSub.setSellerMemo(mallSalesOrderInfo.getSellerMemo());
      } else {
        if (Assert.isEmpty(orderSub.getSellerMemo())) {
          orderSub.setSellerMemo(mallSalesOrderInfo.getSellerMemo());
        } else if (!orderSub.getSellerMemo().equals(mallSalesOrderInfo.getSellerMemo())) {
          orderSub.setSellerMemo(StringUtil.format(
              "{0} {1} {2}", orderSub.getSellerMemo(), mallSalesOrderInfo.getSellerMemo(),
              DateTimeUtil.format(LocalDateTime.now())));
        }
      }
      context.getLogs().add("修改卖家备注");
    }
  }

  /**
   * 设置金额信息.
   */
  private void setMoney(SalesOrder salesOrder, MallSalesOrderInfo mallSalesOrderInfo) {
    salesOrder.setActualAmount(mallSalesOrderInfo.getActualAmount());
    salesOrder.setCodAmount(mallSalesOrderInfo.getCodAmount());
    salesOrder.setSettlementAmount(mallSalesOrderInfo.getSettlementAmount());
    salesOrder.setSellingAmount(mallSalesOrderInfo.getSellingAmount());
    salesOrder.setDiscountAmount(salesOrder.getSellingAmount() - salesOrder.getSettlementAmount());
    salesOrder.setDistributionAmount(mallSalesOrderInfo.getDistributionAmount() != null ?
        mallSalesOrderInfo.getDistributionAmount() : 0);
  }
}
