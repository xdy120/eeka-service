package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;

/**
 * RefundAgreeResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class RefundAgreeResponse extends MallResponse<RefundAgreeRequest> {

  public RefundAgreeResponse(RefundAgreeRequest request) {
    super(request);
  }

  public RefundAgreeResponse(RefundAgreeRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
