package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaProductSkuQueryRequest extends OmsQimenCustomRequest {

  private String skuCode;

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  @Override
  public String toString() {
    return "QimenProductSkuQueryRequest{" +
        "skuCode='" + skuCode + '\'' +
        '}';
  }
}
