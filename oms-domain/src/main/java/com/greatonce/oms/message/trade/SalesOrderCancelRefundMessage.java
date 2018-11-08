package com.greatonce.oms.message.trade;

public class SalesOrderCancelRefundMessage extends SalesOrderMessage {


  public SalesOrderCancelRefundMessage(Long salesOrderId) {
    super(salesOrderId, "refund.cancel");
  }
}
