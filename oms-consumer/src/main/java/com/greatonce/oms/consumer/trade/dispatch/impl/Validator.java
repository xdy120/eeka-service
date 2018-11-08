package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.Validatable;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.DispatchLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDate;
import java.util.Iterator;
import org.springframework.stereotype.Component;

/**
 * 订单配货校验器
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
@Component
@DispatchOrderCondition
public class Validator implements Validatable {

  private static final DispatchLogger LOGGER = OmsLoggerFactory.getDispatchLogger();
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.SALES_ORDER);

  /**
   * 订单非否配货校验.
   * <p/>
   * 逻辑：
   * 1.校验前先剔除不能配的明细（非未配货状态、非退款的明细、非可配的预售明细）。
   * 2.手工处理、全部配货、全部发货、全部退款、整单作废的订单校验结果为失败。
   * 3.最后如果没有可配明细，也校验失败。
   *
   * @param salesOrder 订单
   * @param context 配货上下文
   */
  @Override
  public boolean validate(SalesOrder salesOrder, DispatchContext context) {

    //订单校验
    if (Assert.isTrue(salesOrder.isManual())) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}被标记为手工处理",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单被标记为手工处理");
      return false;
    }
    if (Assert.isTrue(salesOrder.isProductAbnormal())) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}有异常商品",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单有异常商品");
      return false;
    }
    if (SalesOrderStatus.INVALID == salesOrder.getStatus()) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}已作废",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单已作废");
      return false;
    }
    if (salesOrder.getPayStatus() != SalesOrderPayStatus.PAID) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}非已付款",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单非已付款");
      return false;
    }
    if (DispatchStatus.ALL == salesOrder.getDispatchStatus()) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}已全部配货",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单已全部配货");
      return false;
    }
    if (DeliveryStatus.ALL == salesOrder.getDeliveryStatus()) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}已全部发货",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单已全部发货");
      return false;
    }
    if (RefundStatus.ALL == salesOrder.getRefundStatus()) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}已全部退款",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单已全部退款");
      return false;
    }
    if (Assert.isTrue(salesOrder.isHold()) && LocalDate.now().isBefore(salesOrder.getHoldDate())) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}未到留单日期：{}",
          context.getMainSalesOrder().getSalesOrderCode(), salesOrder.getHoldDate());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单未到留单日期：{}",
          salesOrder.getHoldDate());
      return false;
    }
    if (salesOrder.getDetails().stream()
        .anyMatch(x -> x.getSalesOrderDetailRefundStatus() == SalesOrderDetailRefundStatus.APPLY)) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}明细中有申请退款的明细",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "订单中有申请退款的明细");
      return false;
    }
    if (Assert.isTrue(salesOrder.isPrerefund()) && LocalDate.now()
        .isBefore(LocalDate.of(2018, 11, 13))) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，{}为预退款订单且时间在11月13日前",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "11月13日前预退款订单不配货");
      return false;
    }

    //剔除不可配明细
    Iterator<SalesOrderDetail> iterator = salesOrder.getDetails().iterator();
    while (iterator.hasNext()) {
      SalesOrderDetail detail = iterator.next();
      //非未退款明细
      if (detail.getSalesOrderDetailRefundStatus() == SalesOrderDetailRefundStatus.REFUND) {
        iterator.remove();
        continue;
      }
      //非可配的预售明细
      if (detail.getPresellDeliveryDate() != null &&
          detail.getPresellDeliveryDate().isAfter(LocalDate.now())) {
        if (!Assert.isTrue(detail.isPresellPriority())) {
          iterator.remove();
        }
      }
    }

    if (Assert.isEmpty(salesOrder.getDetails())) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}无可配明细",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单无可配明细");
      return false;
    } else if (salesOrder.getDetails().stream().allMatch(detail -> Assert.isTrue(detail.isGift()))
        && !Assert.isTrue(context.getStore().getSetting().isOnlyGiftShipping())) {
      LOGGER.info(context.getSerialNo(), context.getStore(), salesOrder.getSalesOrderCode(),
          salesOrder.getTradeId(), "配货校验失败，订单{}只剩赠品，不配货",
          context.getMainSalesOrder().getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "自动配货校验", "配货校验失败，订单只剩赠品，不配货");
      return false;
    }
    return true;
  }
}
