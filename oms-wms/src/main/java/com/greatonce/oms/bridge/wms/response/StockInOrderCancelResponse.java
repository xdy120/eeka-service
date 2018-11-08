package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;

/**
 * DeliveryOrderCancelResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class StockInOrderCancelResponse extends WmsResponse<StockInOrderCancelRequest> {


  public StockInOrderCancelResponse(StockInOrderCancelRequest request) {
    super(request);
  }

  public StockInOrderCancelResponse(StockInOrderCancelRequest request, boolean success,
      String message) {
    super(request, success, message);
  }
}
