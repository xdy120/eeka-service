package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;

/**
 * CREATED by zhangqin on 2017/1/3.
 */
public class SkuQuantityUploadResponse extends MallResponse<SkuQuantityUploadRequest> {

  public SkuQuantityUploadResponse(SkuQuantityUploadRequest request) {
    super(request);
  }

  public SkuQuantityUploadResponse(SkuQuantityUploadRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  @Override
  public String toString() {
    return "SkuQuantityUploadResponse{" + "isSuccess=" + isSuccess + ", result='" + result + '\''
        + ", request=" + request + '}';
  }
}
