package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.ExchangeRefuseRequest;

/**
 * RefundAgreeResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class ExchangeRefuseResponse extends MallResponse<ExchangeRefuseRequest> {

  public ExchangeRefuseResponse(ExchangeRefuseRequest request) {
    super(request);
  }

  public ExchangeRefuseResponse(ExchangeRefuseRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
