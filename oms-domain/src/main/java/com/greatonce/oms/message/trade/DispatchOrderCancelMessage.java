package com.greatonce.oms.message.trade;

/**
 * 配货单创建消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class DispatchOrderCancelMessage extends DispatchOrderMessage {


  public DispatchOrderCancelMessage(Long dispatchOrderId) {
    super(dispatchOrderId, "cancel");
  }
}
