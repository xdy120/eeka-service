package com.greatonce.oms.message.trade;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class ReturnNoticeOrderCreateMessage extends ReturnNoticeOrderMessage {
    public ReturnNoticeOrderCreateMessage(Long returnNoticeOrderId) {
        super(returnNoticeOrderId,"create");
    }
}
