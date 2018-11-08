package com.greatonce.oms.message.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 * 唯品退货通知单入库消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public abstract class VipReturnNoticeMessage extends Message {
    private final Long vipReturnNoticeId;
    private final String key;

    public VipReturnNoticeMessage(Long vipReturnNoticeId) {
        this(vipReturnNoticeId, null);
    }

    public VipReturnNoticeMessage(Long vipReturnNoticeId, String subKey) {
        this.vipReturnNoticeId = vipReturnNoticeId;
        this.key =
                "oms.vip.return.notice" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
    }

    public Long getVipReturnNoticeId() {
        return vipReturnNoticeId;
    }

    @Override
    public String routingKey() {
        return this.key;
    }
}
