package com.greatonce.oms.message.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 * 配货单消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class DispatchOrderMessage extends Message {

  private final Long dispatchOrderId;
  private final String routingKey;

  public DispatchOrderMessage(Long dispatchOrderId) {
    this(dispatchOrderId, null);
  }

  public DispatchOrderMessage(Long dispatchOrderId, String subKey) {
    this.dispatchOrderId = dispatchOrderId;
    this.routingKey = "oms.trade.dispatch.order" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
  }

  public Long getDispatchOrderId() {
    return dispatchOrderId;
  }

  @Override
  public String routingKey() {
    return routingKey;
  }
}
