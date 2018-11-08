package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;
import com.greatonce.oms.domain.enums.ExpressType;
import com.greatonce.oms.domain.enums.ExpressUse;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaExpressQueryRequest extends OmsQimenCustomRequest {

  private ExpressUse expressUse;
  private ExpressType expressType;

  public ExpressUse getExpressUse() {
    return expressUse;
  }

  public void setExpressUse(ExpressUse expressUse) {
    this.expressUse = expressUse;
  }

  public ExpressType getExpressType() {
    return expressType;
  }

  public void setExpressType(ExpressType expressType) {
    this.expressType = expressType;
  }

  @Override
  public String toString() {
    return "QimenExpressQueryRequest{" +
        "expressUse='" + expressUse + '\'' +
        "expressType='" + expressType + '\'' +
        '}';
  }
}
