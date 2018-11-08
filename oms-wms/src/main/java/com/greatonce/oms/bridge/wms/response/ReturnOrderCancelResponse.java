package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.ReturnOrderCancelRequest;

/**
 * DeliveryOrderCancelResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class ReturnOrderCancelResponse extends WmsResponse<ReturnOrderCancelRequest> {


  public ReturnOrderCancelResponse(ReturnOrderCancelRequest request) {
    super(request);
  }

  public ReturnOrderCancelResponse(ReturnOrderCancelRequest request, boolean success,
      String message) {
    super(request, success, message);
  }
}
