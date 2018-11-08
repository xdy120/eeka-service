package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.ExchangeAgreeRequest;

/**
 * RefundAgreeResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class ExchangeAgreeResponse extends MallResponse<ExchangeAgreeRequest> {

  public ExchangeAgreeResponse(ExchangeAgreeRequest request) {
    super(request);
  }

  public ExchangeAgreeResponse(ExchangeAgreeRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
