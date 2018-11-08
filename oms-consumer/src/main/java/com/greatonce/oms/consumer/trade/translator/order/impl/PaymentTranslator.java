package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.trade.SalesOrderDiscountService;
import com.greatonce.oms.biz.trade.SalesOrderPaymentService;
import com.greatonce.oms.bo.mall.MallSalesOrderDiscountInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDiscount;
import com.greatonce.oms.domain.trade.SalesOrderPayment;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * company:Shenzhen Greatonce Co Ltd
 * createOrder date:2017/6/22
 * remark:支付信息转化器
 * <p>
 * 订单优惠明细和支付明细转化
 *
 * @author:buer
 */
@Component("orderPaymentTranslator")
@TranslatorOrderCondition
public class PaymentTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  @Autowired
  private SalesOrderPaymentService salesOrderPaymentService;
  @Autowired
  private SalesOrderDiscountService salesOrderDiscountService;

  @Override
  public void save(OrderTranslatableContext context) {
    switch (context.getMode()) {
      case MODIFY:
        if (context.isPrepayFinalPaid()) {
          salesOrderPaymentService.batchModify(context.getSalesOrder().getPayments());
          salesOrderDiscountService.batchCreate(context.getSalesOrder().getDiscounts());
        }
        break;
      default:
    }
  }

  @Override
  public void rollback(OrderTranslatableContext context) {
  }

  @Override
  public void translate(OrderTranslatableContext context) {
    SalesOrder salesOrder = context.getSalesOrder();
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    switch (context.getMode()) {
      case CREATE:
        //换货订单不管
        if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE) {
          LOGGER.debug(context.getSerialNo(), context.getStore(),
              context.getMallSalesOrder().getTradeId(), "该订单为换货订单，支付和优惠不转化");
          return;
        }
        //支付信息
        List<SalesOrderPayment> paymentList =
            new ArrayList<>(mallSalesOrderInfo.getPayments().size());
        for (MallSalesOrderPaymentInfo mallSalesOrderPaymentInfo : mallSalesOrderInfo
            .getPayments()) {
          SalesOrderPayment payment = new SalesOrderPayment();
          payment.setPaidTime(mallSalesOrderPaymentInfo.getPaidTime());
          payment.setActualAmount(mallSalesOrderPaymentInfo.getActualAmount());
          payment.setPayType(mallSalesOrderPaymentInfo.getPayType());
          paymentList.add(payment);
        }
        LOGGER.debug(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "支付信息数量:{}",
            mallSalesOrderInfo.getPayments().size());
        salesOrder.setPayments(paymentList);
        //优惠信息
        if (!Assert.isEmpty(mallSalesOrderInfo.getDiscounts()) &&
            salesOrder.getPayStatus() == SalesOrderPayStatus.PAID) {
          addDiscountsInfo(context, mallSalesOrderInfo, salesOrder);
        }
        break;
      case MODIFY:
        if (context.isPrepayFinalPaid()) {
          SalesOrderPayment payment = salesOrder.getPayments().get(0);
          MallSalesOrderPaymentInfo paymentInfo = mallSalesOrderInfo.getPayments().get(0);
          payment.setActualAmount(paymentInfo.getActualAmount());
          payment.setPaidTime(paymentInfo.getPaidTime());
          payment.setPayType(paymentInfo.getPayType());
          //优惠信息
          if (!Assert.isEmpty(mallSalesOrderInfo.getDiscounts()) &&
              salesOrder.getPayStatus() == SalesOrderPayStatus.PAID) {
            addDiscountsInfo(context, mallSalesOrderInfo, salesOrder);
          }
        }
        break;
      default:
    }
  }

  private void addDiscountsInfo(OrderTranslatableContext context,
      MallSalesOrderInfo mallSalesOrderInfo, SalesOrder salesOrder) {
    List<SalesOrderDiscount> discountList = new ArrayList<>(
        mallSalesOrderInfo.getDiscounts().size());
    for (MallSalesOrderDiscountInfo mallSalesOrderDiscountInfo : mallSalesOrderInfo
        .getDiscounts()) {
      SalesOrderDiscount discount = new SalesOrderDiscount();
      discount.setMemberCardNo(mallSalesOrderDiscountInfo.getCardNo());
      discount.setDiscountAmount(mallSalesOrderDiscountInfo.getAmount());
      discount.setDiscountType(mallSalesOrderDiscountInfo.getType());
      discount.setDiscountName(mallSalesOrderDiscountInfo.getDiscountName());
      discount.setMallPaidTime(mallSalesOrderDiscountInfo.getMallPaidTime());
      discountList.add(discount);
    }
    LOGGER.debug(context.getSerialNo(), context.getStore(),
        context.getMallSalesOrder().getTradeId(), "优惠信息数量:{}",
        mallSalesOrderInfo.getDiscounts().size());
    salesOrder.setDiscounts(discountList);
  }
}
