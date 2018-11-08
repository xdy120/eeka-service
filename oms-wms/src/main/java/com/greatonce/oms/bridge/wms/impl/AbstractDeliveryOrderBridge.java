package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.DeliveryOrderBridge;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderQueryRequest;
import com.greatonce.oms.bridge.wms.request.OrderProcessQueryRequest;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.OrderProcessQueryResponse;

/**
 * AbstractDeliveryOrderBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class AbstractDeliveryOrderBridge implements DeliveryOrderBridge {

  @Override
  public DeliveryOrderCreateResponse createOrder(DeliveryOrderCreateRequest request) {
    return doCreateOrder(request);
  }

  @Override
  public DeliveryOrderCancelResponse cancelOrder(DeliveryOrderCancelRequest request) {
    return doCancelOrder(request);
  }

  @Override
  public DeliveryOrderQueryResponse queryOrder(DeliveryOrderQueryRequest request) {
    return doQueryOrder(request);
  }

  @Override
  public OrderProcessQueryResponse queryOrderProcess(OrderProcessQueryRequest request) {
    return doQueryOrderProcess(request);
  }

  protected abstract DeliveryOrderQueryResponse doQueryOrder(DeliveryOrderQueryRequest request);

  protected abstract DeliveryOrderCreateResponse doCreateOrder(DeliveryOrderCreateRequest request);

  protected abstract DeliveryOrderCancelResponse doCancelOrder(DeliveryOrderCancelRequest request);

  protected abstract OrderProcessQueryResponse doQueryOrderProcess(
      OrderProcessQueryRequest request);
}
