package com.greatonce.oms.bo.vip;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/27
 */
public class VipDispatchBindDeliveryBO extends VersionBO {
    private Long vipDeliveryId;
    private String vipDeliveryCode;

    public Long getVipDeliveryId() {
        return vipDeliveryId;
    }

    public void setVipDeliveryId(Long vipDeliveryId) {
        this.vipDeliveryId = vipDeliveryId;
    }

    public String getVipDeliveryCode() {
        return vipDeliveryCode;
    }

    public void setVipDeliveryCode(String vipDeliveryCode) {
        this.vipDeliveryCode = vipDeliveryCode;
    }
}
