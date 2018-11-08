package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;

/**
 * DeliveryOrderCreateResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class StockInOrderCreateResponse extends WmsResponse<StockInOrderCreateRequest> {

  private String wmsCode;

  public StockInOrderCreateResponse(StockInOrderCreateRequest request, String wmsCode) {
    super(request);
    this.wmsCode = wmsCode;
  }

  public StockInOrderCreateResponse(StockInOrderCreateRequest request, boolean success,
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
