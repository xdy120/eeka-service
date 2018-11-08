package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipOrderInPickQueryRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

import java.util.List;

/**
 * @author buer
 * @version 2017-08-24 16:08
 */
public class VipOrderInPickQueryResponse extends MallResponse<VipOrderInPickQueryRequest> {

  private boolean hasNext;
  private List<String> orderNos;

  public VipOrderInPickQueryResponse(VipOrderInPickQueryRequest request) {
    super(request);
  }

  public VipOrderInPickQueryResponse(VipOrderInPickQueryRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<String> getOrderNos() {
    return orderNos;
  }

  public void setOrderNos(List<String> orderNos) {
    this.orderNos = orderNos;
  }
}
