package com.greatonce.oms.message.trade;

/**
 * 销售单已配货消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class SalesOrderDispatchedMessage extends SalesOrderMessage {

  private final boolean partDispatched;
  private final boolean isManualDispatch;

  public SalesOrderDispatchedMessage(Long salesOrderId, boolean partDispatched,
      boolean isManualDispatch) {
    super(salesOrderId, "dispatched");
    this.partDispatched = partDispatched;
    this.isManualDispatch = isManualDispatch;
  }

  public boolean isPartDispatched() {
    return partDispatched;
  }

  public boolean isManualDispatch() {
    return isManualDispatch;
  }
}
