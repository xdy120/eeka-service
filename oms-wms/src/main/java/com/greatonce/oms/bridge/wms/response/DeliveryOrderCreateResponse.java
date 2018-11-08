package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;

/**
 * DeliveryOrderCreateResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class DeliveryOrderCreateResponse extends WmsResponse<DeliveryOrderCreateRequest> {

  private String wmsCode;

  public DeliveryOrderCreateResponse(DeliveryOrderCreateRequest request, String wmsCode) {
    super(request);
    this.wmsCode = wmsCode;
  }

  public DeliveryOrderCreateResponse(DeliveryOrderCreateRequest request, boolean success,
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
