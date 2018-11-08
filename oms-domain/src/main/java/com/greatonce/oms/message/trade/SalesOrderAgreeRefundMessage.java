package com.greatonce.oms.message.trade;

/**
 * @author Shenzhen cca
 * @version 2018/10/8
 */
public class SalesOrderAgreeRefundMessage extends SalesOrderMessage {

  public SalesOrderAgreeRefundMessage(Long salesOrderId) {
    super(salesOrderId, "refund.agree");
  }
}
