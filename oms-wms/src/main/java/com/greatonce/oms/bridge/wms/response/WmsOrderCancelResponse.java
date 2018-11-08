package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.WmsOrderCancelRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
public class WmsOrderCancelResponse extends WmsResponse<WmsOrderCancelRequest> {


  public WmsOrderCancelResponse(WmsOrderCancelRequest request) {
    super(request);
  }

  public WmsOrderCancelResponse(WmsOrderCancelRequest request, boolean success, String message) {
    super(request, success, message);
  }
}
