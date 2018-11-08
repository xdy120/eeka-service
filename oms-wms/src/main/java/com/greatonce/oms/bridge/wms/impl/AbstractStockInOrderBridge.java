package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.StockInOrderBridge;
import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse;

/**
 * 入库单接口抽象实现.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class AbstractStockInOrderBridge implements StockInOrderBridge {

  @Override
  public StockInOrderCreateResponse createOrder(StockInOrderCreateRequest request) {
    return doCreateOrder(request);
  }

  @Override
  public StockInOrderCancelResponse cancelOrder(StockInOrderCancelRequest request) {
    return doCancelOrder(request);
  }

  @Override
  public StockInOrderQueryResponse queryOrder(StockInOrderQueryRequest request) {
    return doQueryOrder(request);
  }

  protected abstract StockInOrderQueryResponse doQueryOrder(StockInOrderQueryRequest request);

  protected abstract StockInOrderCreateResponse doCreateOrder(StockInOrderCreateRequest request);

  protected abstract StockInOrderCancelResponse doCancelOrder(StockInOrderCancelRequest request);
}
