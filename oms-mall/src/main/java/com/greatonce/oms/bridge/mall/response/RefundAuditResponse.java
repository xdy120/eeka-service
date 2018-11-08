package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;

/**
 * RefundAuditResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class RefundAuditResponse extends MallResponse<RefundAuditRequest> {

  public RefundAuditResponse(RefundAuditRequest request) {
    super(request);
  }

  public RefundAuditResponse(RefundAuditRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
