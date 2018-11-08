package com.greatonce.oms.message.trade;

/**
 * 销售订单消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class SalesOrderDeliveryMessage extends SalesOrderMessage {

  private boolean expressNoUpdated;

  public SalesOrderDeliveryMessage(Long salesOrderId) {
    this(salesOrderId, false);
  }

  public SalesOrderDeliveryMessage(Long salesOrderId, boolean expressNoUpdated) {
    super(salesOrderId, "wms.delivery");
    this.expressNoUpdated = expressNoUpdated;
  }

  public boolean isExpressNoUpdated() {
    return expressNoUpdated;
  }

  public void setExpressNoUpdated(boolean expressNoUpdated) {
    this.expressNoUpdated = expressNoUpdated;
  }
}
