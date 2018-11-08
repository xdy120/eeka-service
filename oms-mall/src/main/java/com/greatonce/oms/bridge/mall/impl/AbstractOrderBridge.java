package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.oms.bo.mall.MallSalesOrderDiscountInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInvoiceInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.OrderBridge;
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
import com.greatonce.oms.domain.enums.trade.DiscountType;
import com.greatonce.oms.domain.enums.trade.PayType;
import java.util.Collections;
import java.util.List;

/**
 * 订单抽象类.
 *
 * @author ginta
 */
public abstract class AbstractOrderBridge extends AbstractBridge implements OrderBridge {

  @Override
  public OrderQueryResponse queryOrder(OrderQueryRequest request) {
    return doQueryOrder(request);
  }

  @Override
  public OrderGetResponse getOrder(OrderGetRequest request) {
    return doGetOrder(request);
  }

  protected abstract OrderQueryResponse doQueryOrder(OrderQueryRequest request);

  protected abstract OrderGetResponse doGetOrder(OrderGetRequest request);

  protected abstract OrderDeliveryResponse doOrderDelivery(OrderDeliveryRequest request);

  protected abstract OrderDeliveryResponse doOrderResetExpress(OrderDeliveryRequest request);

  @Override
  public OrderDeliveryResponse orderDelivery(OrderDeliveryRequest request) {
    if (isTesting()) {
      return new OrderDeliveryResponse(request);
    } else {
      return doOrderDelivery(request);
    }
  }

  @Override
  public OrderDeliveryResponse orderResetExpress(OrderDeliveryRequest request) {
    if (isTesting()) {
      return new OrderDeliveryResponse(request);
    } else {
      return doOrderResetExpress(request);
    }
  }

  @Override
  public OrderEventUpdateResponse noticeOrderEvent(OrderEventUpdateRequest request) {
    if (isTesting()) {
      return new OrderEventUpdateResponse(request);
    } else {
      return doNoticeOrderEvent(request);
    }
  }

  /**
   * 通过列表查询获取单个订单
   */
  protected OrderGetResponse getOrderFromQuery(OrderGetRequest request) {
    try {
      OrderQueryRequest queryRequest = new OrderQueryRequest(request.getStore(),
          request.getTradeId());
      OrderQueryResponse response = doQueryOrder(queryRequest);
      if (response.isSuccess() && response.getOrders() != null && response.getOrders().size() > 0) {
        return new OrderGetResponse(request, response.getOrders().get(0));
      }
      return new OrderGetResponse(request, false, "未找到订单！");
    } catch (MallException e) {
      return new OrderGetResponse(request, false, e.getMessage());
    }
  }


  protected List<MallSalesOrderPaymentInfo> createSinglePayment(PayType payType,
      MallSalesOrderInfo order) {
    return Collections.singletonList(
        new MallSalesOrderPaymentInfo(order.getActualAmount(), payType, order.getPaidTime()));
  }

  protected List<MallSalesOrderDiscountInfo> createSingleDiscount(DiscountType discountType,
      Double amount) {
    return Collections.singletonList(new MallSalesOrderDiscountInfo(amount, discountType));
  }

  protected List<MallSalesOrderInvoiceInfo> createSingleInvoice(String title, String content,
      Double amount) {
    return Collections.singletonList(new MallSalesOrderInvoiceInfo(title, content, amount));
  }

  protected OrderEventUpdateResponse doNoticeOrderEvent(OrderEventUpdateRequest request) {
    return new OrderEventUpdateResponse(request);
  }

  @Override
  public OrderReceiverInfoUpdateResponse updateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    return doUpdateOrderReceiverInfo(request);
  }

  protected abstract OrderReceiverInfoUpdateResponse doUpdateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request);
}
