package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.WaybillUpdateRequest;

/**
 * 电子面单获取响应.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
public class WaybillUpdateResponse extends MallResponse<WaybillUpdateRequest> {

  public WaybillUpdateResponse(WaybillUpdateRequest request) {
    super(request);
  }

  public WaybillUpdateResponse(WaybillUpdateRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
