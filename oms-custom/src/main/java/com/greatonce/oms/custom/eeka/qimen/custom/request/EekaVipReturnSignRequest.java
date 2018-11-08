package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaVipReturnSignRequest extends OmsQimenCustomRequest {

  private Long vipReturnId;

  private Integer version;

  private String vipReturnType;

  private String vipSignStatus;

  private String status;

  private Long virtualWarehouseId;

  private String virtualWarehouseName;

  private String remark;

  private String operator;

  public Long getVipReturnId() {
    return vipReturnId;
  }

  public void setVipReturnId(Long vipReturnId) {
    this.vipReturnId = vipReturnId;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getVipReturnType() {
    return vipReturnType;
  }

  public void setVipReturnType(String vipReturnType) {
    this.vipReturnType = vipReturnType;
  }

  public String getVipSignStatus() {
    return vipSignStatus;
  }

  public void setVipSignStatus(String vipSignStatus) {
    this.vipSignStatus = vipSignStatus;
  }

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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
