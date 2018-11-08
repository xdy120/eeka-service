package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;

/**
 * DeliveryOrderCreateResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class StockOutOrderCreateResponse extends WmsResponse<StockOutOrderCreateRequest> {

  private String wmsCode;

  public StockOutOrderCreateResponse(StockOutOrderCreateRequest request, String wmsCode) {
    super(request);
    this.wmsCode = wmsCode;
  }

  public StockOutOrderCreateResponse(StockOutOrderCreateRequest request, boolean success,
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
