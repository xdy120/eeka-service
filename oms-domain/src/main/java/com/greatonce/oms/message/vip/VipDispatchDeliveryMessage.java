package com.greatonce.oms.message.vip;

import com.greatonce.oms.domain.enums.OutStatus;

/**
 * 唯品配货单发货消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class VipDispatchDeliveryMessage extends VipDispatchMessage {

  private OutStatus outStatus;
  private Long vipDeliveryId;

  /**
   * 构造方法.
   */
  public VipDispatchDeliveryMessage(Long vipDispatchId, Long vipDeliveryId,
      OutStatus outStatus) {
    super(vipDispatchId, "delivery");
    this.outStatus = outStatus;
    this.vipDeliveryId = vipDeliveryId;
  }

  public OutStatus getOutStatus() {
    return outStatus;
  }

  public Long getVipDeliveryId() {
    return vipDeliveryId;
  }
}
