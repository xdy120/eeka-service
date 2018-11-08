package com.greatonce.oms.message.vip;

/**
 * 唯品配货单发货消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class VipDispatchBindMessage extends VipDispatchMessage {

  public VipDispatchBindMessage(Long vipDispatchId) {
    super(vipDispatchId, "binding");
  }
}
