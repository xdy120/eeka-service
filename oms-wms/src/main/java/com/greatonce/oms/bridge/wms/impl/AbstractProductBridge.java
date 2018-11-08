package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.ProductBridge;
import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockQueryRequest;
import com.greatonce.oms.bridge.wms.response.SkuCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockQueryResponse;

/**
 * 商品接口抽象类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class AbstractProductBridge implements ProductBridge {

  @Override
  public StockQueryResponse queryStock(StockQueryRequest request) {
    return doQueryStock(request);
  }

  @Override
  public SkuCreateResponse createSku(SkuCreateRequest request) {
    return doCreateSku(request);
  }

  public abstract StockQueryResponse doQueryStock(StockQueryRequest request);

  public abstract SkuCreateResponse doCreateSku(SkuCreateRequest request);
}
