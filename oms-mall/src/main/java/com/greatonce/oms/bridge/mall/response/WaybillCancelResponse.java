package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.WaybillCancelRequest;
import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;

/**
 * 电子面单获取响应.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
public class WaybillCancelResponse extends MallResponse<WaybillCancelRequest> {

  public WaybillCancelResponse(WaybillCancelRequest request) {
    super(request);
  }

  public WaybillCancelResponse(WaybillCancelRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
