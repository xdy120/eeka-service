package com.greatonce.oms.message.trade;

/**
 * @author Lcc
 * @version 2018/7/4 14:03
 */
public class SalesOrderHoldExpiredMessage extends SalesOrderMessage {

  public SalesOrderHoldExpiredMessage(Long salesOrderId) {
    super(salesOrderId, "hold.expired");
  }
}
