package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryCreateRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

/**
 * 唯品出仓单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipDeliveryCreateResponse extends MallResponse<VipDeliveryCreateRequest> {

  public VipDeliveryCreateResponse(VipDeliveryCreateRequest request, String deliveryNo) {
    super(request);
    this.deliveryNo = deliveryNo;
  }

  public VipDeliveryCreateResponse(VipDeliveryCreateRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  /**
   * 出仓单编号.
   */
  private String deliveryNo;

  public String getDeliveryNo() {
    return deliveryNo;
  }

  public void setDeliveryNo(String deliveryNo) {
    this.deliveryNo = deliveryNo;
  }
}
