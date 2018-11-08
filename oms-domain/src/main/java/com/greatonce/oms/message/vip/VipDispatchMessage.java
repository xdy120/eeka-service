package com.greatonce.oms.message.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class VipDispatchMessage extends Message {

  private final Long vipDispatchId;
  private final String key;

  public VipDispatchMessage(Long vipDispatchId, String subKey) {
    this.vipDispatchId = vipDispatchId;
    this.key =
        "oms.vip.dispatch" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
  }

  public Long getVipDispatchId() {
    return vipDispatchId;
  }

  @Override
  public String routingKey() {
    return this.key;
  }
}
