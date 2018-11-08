package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.ReturnOrderBridge;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse;

/**
 * AbstractReturnOrderBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class AbstractReturnOrderBridge implements ReturnOrderBridge {

  @Override
  public ReturnOrderCreateResponse createOrder(ReturnOrderCreateRequest request) {
    return doCreateOrder(request);
  }

  @Override
  public ReturnOrderCancelResponse cancelOrder(ReturnOrderCancelRequest request) {
    return doCancelOrder(request);
  }

  @Override
  public ReturnOrderQueryResponse queryOrder(ReturnOrderQueryRequest request) {
    return doQueryOrder(request);
  }

  protected abstract ReturnOrderQueryResponse doQueryOrder(ReturnOrderQueryRequest request);

  protected abstract ReturnOrderCreateResponse doCreateOrder(ReturnOrderCreateRequest request);

  protected abstract ReturnOrderCancelResponse doCancelOrder(ReturnOrderCancelRequest request);
}
