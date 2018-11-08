package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bo.mall.MallExchangeOrderInfo;
import com.greatonce.oms.bridge.mall.request.ExchangeQueryRequest;

import java.util.List;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/23
 * remark:
 */
public class ExchangeQueryResponse extends MallResponse<ExchangeQueryRequest> {

  private List<MallExchangeOrderInfo> orders;
  private boolean hasNext;
  private int page;

  public ExchangeQueryResponse(ExchangeQueryRequest request) {
    super(request);
  }

  public ExchangeQueryResponse(ExchangeQueryRequest request, List<MallExchangeOrderInfo> orders) {
    super(request);
    this.orders = orders;
  }

  public ExchangeQueryResponse(ExchangeQueryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public List<MallExchangeOrderInfo> getOrders() {
    return orders;
  }

  public void setOrders(List<MallExchangeOrderInfo> orders) {
    this.orders = orders;
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public Integer getCount() {
    return orders == null ? 0 : orders.size();
  }
}
