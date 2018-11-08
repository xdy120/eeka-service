package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;

import java.util.List;


/**
 * @author ginta
 */
public class OrderQueryResponse extends MallResponse<OrderQueryRequest> {

  private boolean hasNext;
  private String tag;
  private List<MallSalesOrderInfo> orders;

  public OrderQueryResponse(OrderQueryRequest request) {
    super(request);
  }

  public OrderQueryResponse(OrderQueryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public List<MallSalesOrderInfo> getOrders() {
    return orders;
  }

  public void setOrders(List<MallSalesOrderInfo> orders) {
    this.orders = orders;
  }

  public Integer getCount() {
    return orders == null ? 0 : orders.size();
  }
}
