package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaVipReturnQueryRequest extends OmsQimenCustomRequest {

  private String boxNumber;

  private String outerCode;

  public String getBoxNumber() {
    return boxNumber;
  }

  public void setBoxNumber(String boxNumber) {
    this.boxNumber = boxNumber;
  }

  public String getOuterCode() {
    return outerCode;
  }

  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode;
  }
}
