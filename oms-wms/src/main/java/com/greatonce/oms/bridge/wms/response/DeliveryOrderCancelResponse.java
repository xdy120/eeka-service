package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;

/**
 * DeliveryOrderCancelResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class DeliveryOrderCancelResponse extends WmsResponse<DeliveryOrderCancelRequest> {

  /**
   * 是否异步取消.
   */
  private boolean async;

  public DeliveryOrderCancelResponse(DeliveryOrderCancelRequest request) {
    super(request);
  }

  public DeliveryOrderCancelResponse(
      DeliveryOrderCancelRequest request, boolean async) {
    super(request);
    this.async = async;
  }

  public DeliveryOrderCancelResponse(DeliveryOrderCancelRequest request, boolean success,
      String message) {
    super(request, success, message);
  }

  public boolean isAsync() {
    return async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }
}
