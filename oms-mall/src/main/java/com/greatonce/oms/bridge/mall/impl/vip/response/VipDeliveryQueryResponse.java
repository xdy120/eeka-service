package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryQueryRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;
import java.util.List;
import vipapis.delivery.DeliveryList;

/**
 * 唯品发货单查询结果.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-09
 */
public class VipDeliveryQueryResponse extends MallResponse<VipDeliveryQueryRequest> {

  private boolean hasNext;
  private List<DeliveryList> orders;

  public VipDeliveryQueryResponse(VipDeliveryQueryRequest request) {
    super(request);
  }

  public VipDeliveryQueryResponse(
      VipDeliveryQueryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<DeliveryList> getOrders() {
    return orders;
  }

  public void setOrders(List<DeliveryList> orders) {
    this.orders = orders;
  }
}
