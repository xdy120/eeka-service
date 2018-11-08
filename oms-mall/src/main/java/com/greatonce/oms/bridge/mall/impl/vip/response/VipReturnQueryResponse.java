package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipReturnQueryRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;
import java.util.List;
import vipapis.vreturn.ReturnDeliveryInfo;

/**
 * 退供单查询结果.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/21
 */
public class VipReturnQueryResponse extends MallResponse<VipReturnQueryRequest> {

  private boolean hasNext;
  private List<ReturnDeliveryInfo> deliveries;

  public VipReturnQueryResponse(VipReturnQueryRequest request) {
    super(request);
  }

  public VipReturnQueryResponse(VipReturnQueryRequest request,
      List<ReturnDeliveryInfo> deliveries) {
    super(request);
    this.deliveries = deliveries;
  }

  public VipReturnQueryResponse(VipReturnQueryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<ReturnDeliveryInfo> getDeliveries() {
    return deliveries;
  }

  public void setDeliveries(List<ReturnDeliveryInfo> deliveries) {
    this.deliveries = deliveries;
  }

  public Integer getCount() {
    return deliveries == null ? 0 : deliveries.size();
  }
}
