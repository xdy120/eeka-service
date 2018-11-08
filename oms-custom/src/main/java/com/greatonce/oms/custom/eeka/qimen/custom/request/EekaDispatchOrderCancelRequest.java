package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaDispatchOrderCancelRequest extends OmsQimenCustomRequest {

  private String dispatchOrderCode;

  public String getDispatchOrderCode() {
    return dispatchOrderCode;
  }

  public void setDispatchOrderCode(String dispatchOrderCode) {
    this.dispatchOrderCode = dispatchOrderCode;
  }

  @Override
  public String toString() {
    return "QimenDispatchOrderCancelRequest{" +
        "dispatchOrderCode='" + dispatchOrderCode + '\'' +
        '}';
  }
}
