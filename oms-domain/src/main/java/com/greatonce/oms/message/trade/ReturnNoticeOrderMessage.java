package com.greatonce.oms.message.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class ReturnNoticeOrderMessage extends Message {

  private final Long returnNoticeOrderId;
  private final String key;

  public ReturnNoticeOrderMessage(Long returnNoticeOrderId, String subKey) {
    this.returnNoticeOrderId = returnNoticeOrderId;
    this.key =
        "oms.trade.return.notice.order" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
  }

  public Long getReturnNoticeOrderId() {
    return returnNoticeOrderId;
  }

  @Override
  public String routingKey() {
    return this.key;
  }
}
