package com.greatonce.oms.bo.vip;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/27
 */
public class VipDispatchCancelBO extends VersionBO {
    private Long vipDispatchId;
    private String reason;

    public Long getVipDispatchId() {
        return vipDispatchId;
    }

    public void setVipDispatchId(Long vipDispatchId) {
        this.vipDispatchId = vipDispatchId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
