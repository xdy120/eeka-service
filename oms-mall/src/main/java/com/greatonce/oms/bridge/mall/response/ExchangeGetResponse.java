package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bo.mall.MallExchangeOrderInfo;
import com.greatonce.oms.bridge.mall.request.ExchangeGetRequest;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/26
 * r
 */
public class ExchangeGetResponse extends MallResponse<ExchangeGetRequest> {

  private MallExchangeOrderInfo mallExchangeOrder;

  public ExchangeGetResponse(ExchangeGetRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public ExchangeGetResponse(ExchangeGetRequest request, MallExchangeOrderInfo mallExchangeOrder) {
    super(request);
    this.mallExchangeOrder = mallExchangeOrder;
  }

  public MallExchangeOrderInfo getMallExchangeOrder() {
    return mallExchangeOrder;
  }

  public void setMallExchangeOrder(MallExchangeOrderInfo mallExchangeOrder) {
    this.mallExchangeOrder = mallExchangeOrder;
  }
}
