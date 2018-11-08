package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest;


/**
 * @author ginta
 */
public class OrderEventUpdateResponse extends MallResponse<OrderEventUpdateRequest>{


    public OrderEventUpdateResponse(OrderEventUpdateRequest request) {
        super(request);
    }

    public OrderEventUpdateResponse(OrderEventUpdateRequest request, boolean isSuccess,
        String result) {
        super(request, isSuccess, result);
    }
}
