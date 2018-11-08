package com.greatonce.oms.message.trade;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class ReturnOrderCreateMessage extends ReturnOrderMessage {

  public ReturnOrderCreateMessage(Long returnOrderId) {
    super(returnOrderId, "create");
  }
}
