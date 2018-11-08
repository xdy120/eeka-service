package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;

/**
 * DeliveryOrderCreateResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class ReturnOrderCreateResponse extends WmsResponse<ReturnOrderCreateRequest> {

  private String wmsCode;

  public ReturnOrderCreateResponse(ReturnOrderCreateRequest request, String wmsCode) {
    super(request);
    this.wmsCode = wmsCode;
  }

  public ReturnOrderCreateResponse(ReturnOrderCreateRequest request, boolean success,
      String message) {
    super(request, success, message);
  }

  public String getWmsCode() {
    return wmsCode;
  }

  public void setWmsCode(String wmsCode) {
    this.wmsCode = wmsCode;
  }
}
