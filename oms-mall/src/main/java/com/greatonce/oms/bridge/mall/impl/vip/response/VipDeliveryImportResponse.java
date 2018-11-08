package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryImportRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipDeliveryImportResponse extends MallResponse<VipDeliveryImportRequest> {

  public VipDeliveryImportResponse(VipDeliveryImportRequest request) {
    super(request);
  }

  public VipDeliveryImportResponse(VipDeliveryImportRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }
}
