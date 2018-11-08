package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.oms.bridge.mall.ExchangeBridge;
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
 * 换货单抽象类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-04
 */
public abstract class AbstractExchangeBridge extends AbstractBridge implements ExchangeBridge {

  @Override
  public ExchangeQueryResponse queryExchange(ExchangeQueryRequest request) {
    return doQueryExchange(request);
  }

  @Override
  public ExchangeGetResponse getExchange(ExchangeGetRequest request) {
    return doGetExchange(request);
  }

  @Override
  public ExchangeAuditResponse audit(ExchangeAuditRequest request) {
    if (isTesting()) {
      return new ExchangeAuditResponse(request);
    } else {
      return doAudit(request);
    }
  }

  @Override
  public ExchangeAgreeResponse agree(ExchangeAgreeRequest request) {
    if (isTesting()) {
      return new ExchangeAgreeResponse(request);
    } else {
      return doAgree(request);
    }
  }

  @Override
  public ExchangeRefuseResponse refuse(ExchangeRefuseRequest request) {
    if (isTesting()) {
      return new ExchangeRefuseResponse(request);
    } else {
      return doRefuse(request);
    }
  }

  protected abstract ExchangeRefuseResponse doRefuse(ExchangeRefuseRequest request);

  protected abstract ExchangeAgreeResponse doAgree(ExchangeAgreeRequest request);

  protected abstract ExchangeQueryResponse doQueryExchange(ExchangeQueryRequest request);

  protected abstract ExchangeGetResponse doGetExchange(ExchangeGetRequest request);

  protected ExchangeAuditResponse doAudit(ExchangeAuditRequest request) {
    return new ExchangeAuditResponse(request, false, "平台不支持");
  }
}
