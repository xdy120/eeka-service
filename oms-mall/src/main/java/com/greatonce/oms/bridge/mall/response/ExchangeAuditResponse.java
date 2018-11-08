package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.ExchangeAuditRequest;

/**
 * RefundAuditResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class ExchangeAuditResponse extends MallResponse<ExchangeAuditRequest> {

  public ExchangeAuditResponse(ExchangeAuditRequest request) {
    super(request);
  }

  public ExchangeAuditResponse(ExchangeAuditRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
