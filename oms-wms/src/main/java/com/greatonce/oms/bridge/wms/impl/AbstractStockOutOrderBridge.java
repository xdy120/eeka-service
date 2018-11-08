package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.StockOutOrderBridge;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse;

/**
 * AbstractStockOutOrderBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class AbstractStockOutOrderBridge implements StockOutOrderBridge {

  @Override
  public StockOutOrderCreateResponse createOrder(StockOutOrderCreateRequest request) {
    return doCreateOrder(request);
  }

  @Override
  public StockOutOrderCancelResponse cancelOrder(StockOutOrderCancelRequest request) {
    return doCancelOrder(request);
  }

  @Override
  public StockOutOrderQueryResponse queryOrder(StockOutOrderQueryRequest request) {
    return doQueryOrder(request);
  }

  protected abstract StockOutOrderQueryResponse doQueryOrder(StockOutOrderQueryRequest request);

  protected abstract StockOutOrderCreateResponse doCreateOrder(StockOutOrderCreateRequest request);

  protected abstract StockOutOrderCancelResponse doCancelOrder(StockOutOrderCancelRequest request);
}
