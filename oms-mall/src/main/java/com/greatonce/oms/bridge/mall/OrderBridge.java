package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderEventUpdateResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;

/**
 * 订单接口.
 * OrderBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public interface OrderBridge extends MallBridge {

  /**
   * 查询订单列表.
   *
   * @param request 查询请求
   */
  OrderQueryResponse queryOrder(OrderQueryRequest request);

  /**
   * 获取单个订单.
   *
   * @param request 单个请求
   */
  OrderGetResponse getOrder(OrderGetRequest request);

  /**
   * 订单发货.
   *
   * @param request 发货请求
   */
  OrderDeliveryResponse orderDelivery(OrderDeliveryRequest request);

  /**
   * 订单快递修改.
   *
   * @param request 发货请求
   */
  OrderDeliveryResponse orderResetExpress(OrderDeliveryRequest request);

  /**
   * 当订单被处理时，用于通知奇门系统.
   *
   * @param request 事件通知请求
   */
  OrderEventUpdateResponse noticeOrderEvent(OrderEventUpdateRequest request);

  /**
   * 订单收货人信息修改
   *
   * @param request 订单收货人信息修改请求
   */
  OrderReceiverInfoUpdateResponse updateOrderReceiverInfo(OrderReceiverInfoUpdateRequest request);

}
