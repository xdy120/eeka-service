package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/19
 */
public class OrderDeliveryResponse extends MallResponse<OrderDeliveryRequest> {

  public OrderDeliveryResponse(OrderDeliveryRequest request) {
    super(request);
  }

  public OrderDeliveryResponse(OrderDeliveryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
