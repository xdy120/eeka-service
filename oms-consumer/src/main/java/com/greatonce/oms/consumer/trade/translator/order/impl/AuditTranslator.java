package com.greatonce.oms.consumer.trade.translator.order.impl;


import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.base.setting.OrderAuditStrategy;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * company:Shenzhen Greatonce Co Ltd
 * remark:默认审核类
 *
 * @author lcc
 */
@Component("orderAuditTranslator")
@TranslatorOrderCondition
public class AuditTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  private static final Long AUDIT_DATA_DICT_ID = 10203L;
  @Autowired
  private DataDictItemService dataDictItemService;

  @Override
  public void translate(OrderTranslatableContext context) {
    if (!context.isNeedDelivery()) {
      return;
    }
    if (context.getMode() != OrderTranslatableMode.CREATE) {
      return;
    }
    if (context.getSalesOrder().getDeliveryStatus() == DeliveryStatus.ALL) {
      context.getSalesOrder().setStatus(SalesOrderStatus.DELIVERED);
      return;
    }
    SalesOrder order = context.getSalesOrder();
    //有效性检查
    validityCheck(context, order);
    //审核策略检查
    strategyCheck(context, order);
    if (order.getStatus() == SalesOrderStatus.CREATED && context.isPassAudited()) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(),
          "订单{}自动审核通过", context.getSalesOrder().getTradeId());
      order.setAuditedTime(LocalDateTime.now());
      order.setAuditor(BizContext.getNickname());
      context.getLogs().add("系统自动审核");
    }
  }

  @Override
  public void save(OrderTranslatableContext context) {

  }

  @Override
  public void rollback(OrderTranslatableContext context) {

  }

  private void abnormal(OrderTranslatableContext context, String log) {
    context.setPassAudited(false);
    context.getLogs().add(log);
    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "审核失败：{}", log);
  }

  /**
   * 有效性检查
   */
  private void validityCheck(OrderTranslatableContext context, SalesOrder order) {
    SalesOrderSub orderSub = order.getSub();
    if (order.getDetails() == null || order.getDetails().isEmpty()) {
      abnormal(context, "订单明细为空");
    }
    if (order.getPayments() == null || order.getPayments().isEmpty()) {
      abnormal(context, "订单支付信息为空");
    }
    if (order.getRefundStatus() == RefundStatus.ALL) {
      abnormal(context, "订单全部退款");
    } else if (order.getRefundStatus() == RefundStatus.PART) {
      abnormal(context, "订单部分退款");
    }
    if (order.getDetails().stream().anyMatch(x -> x.getActualAmount() < 0)) {
      abnormal(context, "订单明细结算金额异常");
    }
    if (Assert.isEmpty(orderSub.getMemberName())) {
      abnormal(context, "会员信息为空");
    }
    if (Assert.isEmpty(order.getAddress())) {
      abnormal(context, "收货地址为空");
    }
    if (Assert.isEmpty(order.getContact())) {
      abnormal(context, "收货人为空");
    }
    if (Assert.isEmpty(order.getMobile()) && Assert.isEmpty(order.getTelephone())) {
      abnormal(context, "收货人联系方式为空");
    }
    if (Assert.isTrue(order.isProductAbnormal())) {
      abnormal(context, "明细有商品匹配异常");
    }
  }

  /**
   * 审核策略检查
   */
  private void strategyCheck(OrderTranslatableContext context, SalesOrder order) {
    SalesOrderSub orderSub = order.getSub();
    OrderAuditStrategy strategy = context.getOrderAuditStrategy();
    if (!Assert.isTrue(strategy.isEnable())) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "审单策略未启用，默认审核通过");
      return;
    }
    //如果店铺设定拦截黑名单用户，且订单用户在黑名单里
    if (Assert.isTrue(strategy.isBlackListMember()) && Assert.isTrue(orderSub.isBlacklist())) {
      abnormal(context, "黑名单用户需人工审核");
    }
    //订单金额大于店铺设定的金额，则标记为人工审核
    if (!Assert.isNull(strategy.getMaxAmount()) && order.getActualAmount() > strategy
        .getMaxAmount()) {
      abnormal(context, "订单金额过大需人工审核");
    }
    if (!Assert.isNull(strategy.getMinAmount()) && order.getActualAmount() < strategy
        .getMinAmount()) {
      abnormal(context, "订单金额过少需人工审核");
    }
    if (!Assert.isNull(strategy.getMaxQuantity()) && order.getQuantity() > strategy
        .getMaxQuantity()) {
      abnormal(context, "商品数量过多需人工审核");
    }
    if (!Assert.isNull(strategy.getMinQuantity()) && order.getQuantity() < strategy
        .getMinQuantity()) {
      abnormal(context, "商品数量过少需人工审核");
    }
    if (Assert.isTrue(strategy.isCod()) && Assert.isTrue(order.getSub().isCod())) {
      abnormal(context, "到付订单需人工审核");
    }
    if (Assert.isTrue(strategy.isBuyerMessage()) && !Assert.isEmpty(orderSub.getBuyerMemo())) {
      abnormal(context, "订单有买家备注");
    }
    if (Assert.isTrue(strategy.isSellerMessage()) && !Assert.isEmpty(orderSub.getSellerMemo())) {
      Set<String> set = dataDictItemService.listSetByDictId(AUDIT_DATA_DICT_ID);
      if (!set.contains(orderSub.getSellerMemo().trim())) {
        abnormal(context, "订单有需拦截的卖家备注");
      }
    }
    if (Assert.isTrue(strategy.isInvoice()) && Assert.isTrue(order.getSub().isHasInvoice())) {
      abnormal(context, "发票需人工审核");
    }

    auditDateQuery(context, order, strategy);

    //拦截地址
    if (!Assert.isEmpty(context.getSalesOrder().getProvinceName())) {
      interceptAddress(context, context.getSalesOrder().getProvinceName());
    }
    if (!Assert.isEmpty(context.getSalesOrder().getCityName())) {
      interceptAddress(context, context.getSalesOrder().getCityName());
    }
    if (!Assert.isEmpty(context.getSalesOrder().getDistrictName())) {
      interceptAddress(context, context.getSalesOrder().getDistrictName());
    }

    //拦截明细
    order.getDetails().forEach(x -> {
      if (!Assert.isTrue(x.isGift())) {
        interceptProduct(context, x.getProductCode());
        interceptProduct(context, x.getSkuCode());
      }
    });
  }

  /**
   * 自动审单时间检查（不审某天或者某时前的单）
   */
  private void auditDateQuery(OrderTranslatableContext context, SalesOrder order,
      OrderAuditStrategy strategy) {
    LocalDateTime paidTime = order.getSub().getMallPaidTime();
    LocalDateTime beginDate = strategy.getInterceptBeginDate();
    LocalDateTime endDate = strategy.getInterceptEndDate();

    if (beginDate != null && endDate != null) {
      if ((paidTime.isEqual(beginDate) || paidTime.isAfter(beginDate)) && paidTime
          .isBefore(endDate)) {
        abnormal(context, "支付时间在审单拦截日期内需人工审单");
        return;
      }
    } else if (beginDate != null) {
      if (paidTime.isEqual(beginDate) || paidTime.isAfter(beginDate)) {
        abnormal(context, "支付时间在审单拦截日期内需人工审单");
        return;
      }
    } else if (endDate != null) {
      if (paidTime.isBefore(endDate)) {
        abnormal(context, "支付时间在审单拦截日期内需人工审单");
        return;
      }
    }
    LocalTime beginTime = strategy.getInterceptBeginTime();
    LocalTime endTime = strategy.getInterceptEndTime();

    final LocalTime time = paidTime.toLocalTime();
    if (beginTime != null && endTime != null) {
      //跨天
      if (endTime.isBefore(beginTime)) {
        if (time.isAfter(beginTime) || time.isBefore(endTime)) {
          abnormal(context, "支付时间在审单拦截时间内需人工审单");
        }
      } else {
        if (time.isAfter(beginTime) && time.isBefore(endTime)) {
          abnormal(context, "支付时间在审单拦截时间内需人工审单");
        }
      }
    } else if (beginTime != null) {
      if (time.isAfter(beginTime)) {
        abnormal(context, "支付时间在审单拦截时间内需人工审单");
      }
    } else if (endTime != null) {
      if (time.isBefore(endTime)) {
        abnormal(context, "支付时间在审单拦截时间内需人工审单");
      }
    }
  }

  /**
   * 拦截地址关键字
   */
  private void interceptAddress(OrderTranslatableContext context, String region) {
    List<String> interceptRegionKeys = context.getOrderAuditStrategy().getInterceptRegion();
    if (Assert.isEmpty(interceptRegionKeys)) {
      return;
    }
    for (String interceptRegionKey : interceptRegionKeys) {
      if (region.contains(interceptRegionKey)) {
        abnormal(context, "地址：" + region + "，拦截地址关键字：" + interceptRegionKey);
        break;
      }
    }
  }

  /**
   * 拦截商品
   */
  private void interceptProduct(OrderTranslatableContext context, String code) {
    List<String> interceptCodes = context.getOrderAuditStrategy().getInterceptProduct();
    if (Assert.isEmpty(interceptCodes)) {
      return;
    }
    for (String interceptCode : interceptCodes) {
      if (interceptCode.equals(code)) {
        abnormal(context, "拦截商品编码：" + interceptCode);
        break;
      }
    }
  }
}
