package com.greatonce.oms.message.trade;

/**
 * 配货单创建消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class DispatchOrderCreateMessage extends DispatchOrderMessage {

  public DispatchOrderCreateMessage(Long dispatchOrderId) {
    super(dispatchOrderId, "create");
  }

}
