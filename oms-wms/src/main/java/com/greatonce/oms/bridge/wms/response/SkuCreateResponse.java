package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;

/**
 * SKU修改响应.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-14
 */
public class SkuCreateResponse extends WmsResponse<SkuCreateRequest> {


  public SkuCreateResponse(SkuCreateRequest request) {
    super(request);
  }

  public SkuCreateResponse(SkuCreateRequest request, boolean success, String message) {
    super(request, success, message);
  }
}
