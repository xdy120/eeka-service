package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockOutOrderCancelRequest;

/**
 * DeliveryOrderCancelResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class StockOutOrderCancelResponse extends WmsResponse<StockOutOrderCancelRequest> {

  public StockOutOrderCancelResponse(StockOutOrderCancelRequest request) {
    super(request);
  }

  public StockOutOrderCancelResponse(StockOutOrderCancelRequest request, boolean success,
      String message) {
    super(request, success, message);
  }
}
