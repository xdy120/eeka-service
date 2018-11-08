package com.greatonce.oms.message.trade;

/**
 * 订单发起通知自动配货消息.
 *
 * @author Lcc
 * @version 2018/7/4 14:12
 */
public class SalesOrderAutoDispatchMessage extends SalesOrderMessage {

  public SalesOrderAutoDispatchMessage(Long salesOrderId) {
    super(salesOrderId,"notice.auto.dispatch");
  }
}
