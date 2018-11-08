package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderQueryRequest;
import com.greatonce.oms.bridge.wms.request.OrderProcessQueryRequest;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.OrderProcessQueryResponse;

/**
 * B2C发货单接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public interface DeliveryOrderBridge extends WmsBridge {


  /**
   * 创建单据.
   */
  DeliveryOrderCreateResponse createOrder(DeliveryOrderCreateRequest request);

  /**
   * 取消单据.
   */
  DeliveryOrderCancelResponse cancelOrder(DeliveryOrderCancelRequest request);

  /**
   * 查询单据.
   */
  DeliveryOrderQueryResponse queryOrder(DeliveryOrderQueryRequest request);

  OrderProcessQueryResponse queryOrderProcess(OrderProcessQueryRequest request);

}
