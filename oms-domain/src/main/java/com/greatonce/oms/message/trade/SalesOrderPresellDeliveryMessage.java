package com.greatonce.oms.message.trade;

/**
 * @author Lcc
 * @version 2018/7/4 11:54
 */
public class SalesOrderPresellDeliveryMessage extends SalesOrderMessage {

  public SalesOrderPresellDeliveryMessage(Long salesOrderId) {
    super(salesOrderId, "presell.delivery");
  }
}
