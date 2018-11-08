package com.greatonce.oms.message.trade;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/7/18
 */
public class SalesOrderOOSRedispatchMessage extends SalesOrderMessage {

  public SalesOrderOOSRedispatchMessage(Long salesOrderId) {
    super(salesOrderId,"oos.redispatch");
  }
}
