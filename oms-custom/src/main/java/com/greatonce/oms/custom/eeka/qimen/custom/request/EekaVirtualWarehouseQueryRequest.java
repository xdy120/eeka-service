package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaVirtualWarehouseQueryRequest extends OmsQimenCustomRequest {

  private String loginName;

  private String virtualWarehouseType;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getVirtualWarehouseType() {
    return virtualWarehouseType;
  }

  public void setVirtualWarehouseType(String virtualWarehouseType) {
    this.virtualWarehouseType = virtualWarehouseType;
  }

  @Override
  public String toString() {
    return "QimenVirtualWarehouseQueryRequest{" +
        "loginName='" + loginName + '\'' +
        "virtualWarehouseType='" + virtualWarehouseType + '\'' +
        '}';
  }
}
