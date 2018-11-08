package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInvoiceInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Component;

/**
 * company:Shenzhen Greatonce Co Ltd
 * createOrder date:2017/5/26
 * remark:发票信息转化器
 *
 * @author:buer
 */
@Component("orderInvoiceTranslator")
@TranslatorOrderCondition
public class InvoiceTranslator implements OrderTranslatable {

  private static final String NEED_INVOICE_REGEX = "(?<!不)开发票";
  private static final String NO_NEED_INVOICE_REGEX = "不需要开具发票";
  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();

  @Override
  public void save(OrderTranslatableContext context) {
  }

  @Override
  public void rollback(OrderTranslatableContext context) {
  }

  /**
   * 发票信息转化逻辑：
   * 只处理新建订单，如果买家留言、卖家留言、mall订单中有发票信息则转化发票信息
   */
  @Override
  public void translate(OrderTranslatableContext context) {
    switch (context.getMode()) {
      case CREATE:
        if (context.getSalesOrder().getPayStatus() == SalesOrderPayStatus.PAID) {
          parseInvoice(context);
        }
        break;
      case MODIFY:
        if (context.isPrepayFinalPaid()) {
          parseInvoice(context);
        }
        break;
      default:
    }
  }

  /**
   * 解析发票.
   */
  private void parseInvoice(OrderTranslatableContext context) {
    if (context.getSalesOrder().getInvoices() == null) {
      context.getSalesOrder().setInvoices(new ArrayList<>(1));
    }
    SalesOrderSub orderSub = context.getSalesOrder().getSub();
    orderSub.setHasInvoice(checkNeedInvoice(context));
    if (!orderSub.isHasInvoice()) {
      return;
    }

    MallSalesOrderInvoiceInfo invoiceInfo = context.getMallSalesOrderInfo().getInvoice();
    if (invoiceInfo != null) {
      SalesOrderInvoice invoice = new SalesOrderInvoice();
      invoice.setInvoiceTitle(invoiceInfo.getTitle() != null ? invoiceInfo.getTitle() : "");
      invoice.setInvoiceContent(invoiceInfo.getContent() != null ? invoiceInfo.getContent() : "");
      invoice.setInvoiceType(invoiceInfo.getInvoiceType());
      invoice.setGmfAddress(invoiceInfo.getGmfAddress());
      invoice.setGmfBankName(invoiceInfo.getGmfBankName());
      invoice.setGmfBankNo(invoiceInfo.getGmfBankNo());
      invoice.setGmfMobile(invoiceInfo.getGmfMobile());
      invoice
          .setTaxpayerId(invoiceInfo.getTaxpayerId() != null ? invoiceInfo.getTaxpayerId() : "");
      invoice.setActualAmount(invoiceInfo.getAmount());
      if (Assert.isEmpty(invoice.getInvoiceTitle())) {
        invoice.setInvoiceTitle(context.getSalesOrder().getContact());
      }
      context.getSalesOrder().setInvoices(Collections.singletonList(invoice));
    }
  }

  /**
   * 校验是否开发票
   */
  private boolean checkNeedInvoice(OrderTranslatableContext context) {
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    if (!Assert.isEmpty(mallSalesOrderInfo.getBuyerMemo()) && mallSalesOrderInfo.getBuyerMemo()
        .matches(NEED_INVOICE_REGEX)) {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "匹配发票，买家备注：{}", mallSalesOrderInfo.getBuyerMemo());
      return true;
    }
    if (!Assert.isEmpty(mallSalesOrderInfo.getSellerMemo()) && mallSalesOrderInfo.getSellerMemo()
        .matches(NEED_INVOICE_REGEX)) {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "匹配发票，卖家备注：{}", mallSalesOrderInfo.getSellerMemo());
      return true;
    }
    MallSalesOrderInvoiceInfo invoice = mallSalesOrderInfo.getInvoice();
    if (invoice != null) {
      //非不用开票=需要开票
      boolean checkTitle = !Assert.isEmpty(invoice.getTitle()) && !invoice.getTitle()
          .matches(NO_NEED_INVOICE_REGEX);
      boolean checkContent = !Assert.isEmpty(invoice.getContent()) && !invoice.getContent()
          .matches(NO_NEED_INVOICE_REGEX);
      return checkTitle || checkContent;
    }
    return false;
  }
}
