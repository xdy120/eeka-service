package com.greatonce.oms.message.trade;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/9/11
 */
public class SalesOrderDeliveryFailMessage extends SalesOrderDeliveryMessage {

  private final String key;

  public SalesOrderDeliveryFailMessage(Long salesOrderId, boolean expressNoUpdated) {
    super(salesOrderId, expressNoUpdated);
    this.key = "oms.trade.sales.order.delivery.retry";
  }

  @Override
  public String routingKey() {
    return key;
  }
}
