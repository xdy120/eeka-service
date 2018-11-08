package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;

/**
 * RefundGetRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class ExchangeGetRequest extends MallRequest {

  private String exchangeId;

  public ExchangeGetRequest(Store store, String exchangeId) {
    super(store);
    this.exchangeId = exchangeId;
  }

  public String getExchangeId() {
    return exchangeId;
  }

  public void setExchangeId(String exchangeId) {
    this.exchangeId = exchangeId;
  }
}
