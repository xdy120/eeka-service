package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;

/**
 * @author ginta
 */
public class OrderGetResponse extends MallResponse<OrderGetRequest> {

  private MallSalesOrderInfo order;

  public OrderGetResponse(OrderGetRequest request, MallSalesOrderInfo order) {
    super(request);
    this.order = order;
  }

  public OrderGetResponse(OrderGetRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public MallSalesOrderInfo getOrder() {
    return order;
  }

  public void setOrder(MallSalesOrderInfo order) {
    this.order = order;
  }
}
