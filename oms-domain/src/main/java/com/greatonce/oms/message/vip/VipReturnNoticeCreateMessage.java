package com.greatonce.oms.message.vip;

/**
 * 唯品退货通知单入库消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class VipReturnNoticeCreateMessage extends VipReturnNoticeMessage {

    public VipReturnNoticeCreateMessage(Long vipReturnNoticeId) {
        super(vipReturnNoticeId,"create");
    }
}
