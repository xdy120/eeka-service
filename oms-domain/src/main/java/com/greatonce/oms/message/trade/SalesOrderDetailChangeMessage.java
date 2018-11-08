package com.greatonce.oms.message.trade;

/**
 * @author skp
 * 订单索引更新（带明细）
 */
public class SalesOrderDetailChangeMessage extends SalesOrderMessage {

  public SalesOrderDetailChangeMessage(Long salesOrderId) {
    super(salesOrderId, "detail.change");
  }

}