package com.greatonce.oms.message.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 * 销售订单消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class SalesOrderMessage extends Message {

  public enum SalesOrderEvent {
    AUDITED,
    CREATED,
    DISPATCHED,
    DELIVERED
  }

  private final Long salesOrderId;
  private final String key;

  public SalesOrderMessage(Long salesOrderId) {
    this(salesOrderId, null);
  }

  public SalesOrderMessage(Long salesOrderId, String subKey) {
    this.salesOrderId = salesOrderId;
    this.key =
        "oms.trade.sales.order" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
  }

  public Long getSalesOrderId() {
    return salesOrderId;
  }

  @Override
  public String routingKey() {
    return this.key;
  }
}
