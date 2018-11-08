package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;

import java.util.List;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/23
 * remark:
 */
public class RefundQueryResponse extends MallResponse<RefundQueryRequest> {

  private List<MallRefundOrderInfo> orders;
  private boolean hasNext;
  private int page;

  public RefundQueryResponse(RefundQueryRequest request) {
    super(request);
  }

  public RefundQueryResponse(RefundQueryRequest request, List<MallRefundOrderInfo> orders) {
    super(request);
    this.orders = orders;
  }

  public RefundQueryResponse(RefundQueryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public List<MallRefundOrderInfo> getOrders() {
    return orders;
  }

  public void setOrders(List<MallRefundOrderInfo> orders) {
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
