package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaVipReturnNoticeCreatRequest extends OmsQimenCustomRequest {


  private String operator;

  private String outerCode;

  private String vipReturnId;

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getOuterCode() {
    return outerCode;
  }

  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode;
  }

  public String getVipReturnId() {
    return vipReturnId;
  }

  public void setVipReturnId(String vipReturnId) {
    this.vipReturnId = vipReturnId;
  }
}
