package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.ExchangeAgreeRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeAuditRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeGetRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeQueryRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeRefuseRequest;
import com.greatonce.oms.bridge.mall.response.ExchangeAgreeResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeAuditResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeGetResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeQueryResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeRefuseResponse;

/**
 * 换货单.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public interface ExchangeBridge extends MallBridge {

  ExchangeQueryResponse queryExchange(ExchangeQueryRequest request);

  ExchangeGetResponse getExchange(ExchangeGetRequest request);

  ExchangeAuditResponse audit(ExchangeAuditRequest request);

  ExchangeAgreeResponse agree(ExchangeAgreeRequest request);

  ExchangeRefuseResponse refuse(ExchangeRefuseRequest request);
}
