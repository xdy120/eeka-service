package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.domain.base.Store;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/21
 */
public class VipReturnQueryRequest extends RefundQueryRequest {

  private String vipWarehouseCode;

  private String outerCode;

  public VipReturnQueryRequest(Store store) {
    super(store);
  }

  public String getVipWarehouseCode() {
    return vipWarehouseCode;
  }

  public void setVipWarehouseCode(String vipWarehouseCode) {
    this.vipWarehouseCode = vipWarehouseCode;
  }

  public String getOuterCode() {
    return outerCode;
  }

  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode;
  }
}
