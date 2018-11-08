package com.greatonce.oms.message.trade;

/**
 * 配货单发货消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class DispatchOrderDeliveryMessage extends DispatchOrderMessage {

  public DispatchOrderDeliveryMessage(Long dispatchOrderId) {
    super(dispatchOrderId, "delivery");
  }
}