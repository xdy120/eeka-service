package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.oms.bridge.mall.StoreBridge;
import com.greatonce.oms.bridge.mall.request.StoreAddressQueryRequest;
import com.greatonce.oms.bridge.mall.response.StoreAddressQueryResponse;

/**
 * 店铺接口抽象类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-27
 */
public abstract class AbstractStoreBridge extends AbstractBridge implements StoreBridge {

  @Override
  public StoreAddressQueryResponse queryAddress(StoreAddressQueryRequest request) {
    if (isTesting()) {
      return new StoreAddressQueryResponse(request);
    } else {
      return doQueryAddress(request);
    }
  }

  protected abstract StoreAddressQueryResponse doQueryAddress(StoreAddressQueryRequest request);
}
