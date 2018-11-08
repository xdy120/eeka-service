package com.greatonce.oms.bo.vip;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/27
 */
public class VipReturnNoticeCancelBO extends VersionBO {
    private Long vipReturnNoticeId;
    private String reason;

    public Long getVipReturnNoticeId() {
        return vipReturnNoticeId;
    }

    public void setVipReturnNoticeId(Long vipReturnNoticeId) {
        this.vipReturnNoticeId = vipReturnNoticeId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
