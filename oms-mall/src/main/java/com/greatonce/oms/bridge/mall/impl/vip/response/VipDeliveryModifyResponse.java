package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryModifyRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipDeliveryModifyResponse extends MallResponse<VipDeliveryModifyRequest> {

  public VipDeliveryModifyResponse(VipDeliveryModifyRequest request) {
    super(request);
  }

  public VipDeliveryModifyResponse(VipDeliveryModifyRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }
}
