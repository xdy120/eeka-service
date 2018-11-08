package com.greatonce.oms.message.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class ReturnOrderMessage extends Message {

  private final Long returnOrderId;
  private final String key;

  public ReturnOrderMessage(Long returnOrderId) {
    this(returnOrderId, null);
  }

  public ReturnOrderMessage(Long returnOrderId, String subKey) {
    this.returnOrderId = returnOrderId;
    this.key =
        "oms.trade.return.order" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
  }

  public Long getReturnOrderId() {
    return returnOrderId;
  }

  @Override
  public String routingKey() {
    return key;
  }
}
