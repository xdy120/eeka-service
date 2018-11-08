package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;

public class OrderReceiverInfoUpdateResponse extends MallResponse<OrderReceiverInfoUpdateRequest> {

  public OrderReceiverInfoUpdateResponse(OrderReceiverInfoUpdateRequest request) {
    super(request);
  }

  public OrderReceiverInfoUpdateResponse(OrderReceiverInfoUpdateRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }
}
