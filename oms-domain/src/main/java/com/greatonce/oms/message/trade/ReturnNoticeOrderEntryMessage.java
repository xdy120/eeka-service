package com.greatonce.oms.message.trade;

/**
 * 退货通知单入库消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class ReturnNoticeOrderEntryMessage extends ReturnNoticeOrderMessage {

  public ReturnNoticeOrderEntryMessage(Long returnNoticeOrderId) {
    super(returnNoticeOrderId, "entry");
  }
}
