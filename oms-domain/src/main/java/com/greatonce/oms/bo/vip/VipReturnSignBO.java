package com.greatonce.oms.bo.vip;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.enums.vip.VipSignStatus;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipReturnSignBO extends VersionBO {

    private Long virtualWarehouseId;
    private String virtualWarehouseName;
    private String vipReturnType;
    private String remark;
    private VipSignStatus vipSignStatus;
    private String operator;

    public Long getVirtualWarehouseId() {
        return virtualWarehouseId;
    }

    public void setVirtualWarehouseId(Long virtualWarehouseId) {
        this.virtualWarehouseId = virtualWarehouseId;
    }

    public String getVirtualWarehouseName() {
        return virtualWarehouseName;
    }

    public void setVirtualWarehouseName(String virtualWarehouseName) {
        this.virtualWarehouseName = virtualWarehouseName;
    }

    public String getVipReturnType() {
        return vipReturnType;
    }

    public void setVipReturnType(String vipReturnType) {
        this.vipReturnType = vipReturnType;
    }

    public VipSignStatus getVipSignStatus() {
        return vipSignStatus;
    }

    public void setVipSignStatus(VipSignStatus vipSignStatus) {
        this.vipSignStatus = vipSignStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
