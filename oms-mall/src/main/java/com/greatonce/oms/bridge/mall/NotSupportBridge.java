package com.greatonce.oms.bridge.mall;

import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.bridge.mall.request.AuthorizeRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeAgreeRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeAuditRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeGetRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeQueryRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeRefuseRequest;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.request.ProductQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;
import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.AuthorizeResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeAgreeResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeAuditResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeGetResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeQueryResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeRefuseResponse;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderEventUpdateResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;
import com.greatonce.oms.bridge.mall.response.ProductQuantityUploadResponse;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.bridge.mall.response.RefundAgreeResponse;
import com.greatonce.oms.bridge.mall.response.RefundAuditResponse;
import com.greatonce.oms.bridge.mall.response.RefundGetResponse;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 不支持接口实现.
 * NotSupportBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-06-27
 */
@Component
public class NotSupportBridge implements ProductBridge, OrderBridge, SecurityBridge, RefundBridge,
    AuthorizeBridge, ExchangeBridge {

  static final String NOT_SUPPORT_MSG = "平台暂不支持！";

  @Override
  public OrderQueryResponse queryOrder(OrderQueryRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public OrderGetResponse getOrder(OrderGetRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public OrderDeliveryResponse orderDelivery(OrderDeliveryRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public OrderDeliveryResponse orderResetExpress(OrderDeliveryRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public SkuQuantityUploadResponse uploadQuantity(SkuQuantityUploadRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public OrderEventUpdateResponse noticeOrderEvent(OrderEventUpdateRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public OrderReceiverInfoUpdateResponse updateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public ProductQuantityUploadResponse uploadQuantity(ProductQuantityUploadRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public ProductQueryResponse queryProduct(ProductQueryRequest request) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public ProductQueryResponse queryProductById(ProductQueryRequest request) {
    return new ProductQueryResponse(request, false, NOT_SUPPORT_MSG);
  }

  @Override
  public ProductQueryResponse queryProductByCode(ProductQueryRequest request) {
    return new ProductQueryResponse(request, false, NOT_SUPPORT_MSG);
  }

  @Override
  public boolean isSupportMultiStatus() {
    return false;
  }

  @Override
  public String getMallProductUrl(String mallProductId) {
    throw new MallException(NOT_SUPPORT_MSG);
  }

  @Override
  public String decrypt(Store store, String value, DataType dataType) {
    return value;
  }

  @Override
  public Map<String, String> decrypt(Store store, List<String> values, DataType dataType) {
    return CollectionUtil.listToMap(values, x -> x);
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.UNDEFINE};
  }

  @Override
  public String authorizeUrl(AuthorizeRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public AuthorizeResponse accessToken(AuthorizeRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public AuthorizeResponse refreshToken(AuthorizeRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public ExchangeQueryResponse queryExchange(ExchangeQueryRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public ExchangeGetResponse getExchange(ExchangeGetRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public ExchangeAuditResponse audit(ExchangeAuditRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public ExchangeAgreeResponse agree(ExchangeAgreeRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public ExchangeRefuseResponse refuse(ExchangeRefuseRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public RefundQueryResponse queryRefund(RefundQueryRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public RefundGetResponse getRefund(RefundGetRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public RefundAuditResponse audit(RefundAuditRequest request) {
    throw new MallException("此平台不支持");
  }

  @Override
  public RefundAgreeResponse agree(RefundAgreeRequest request) {
    throw new MallException("此平台不支持");
  }
}
