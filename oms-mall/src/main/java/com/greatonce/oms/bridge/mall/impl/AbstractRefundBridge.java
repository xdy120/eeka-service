package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.mall.RefundBridge;
import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;
import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.response.RefundAgreeResponse;
import com.greatonce.oms.bridge.mall.response.RefundAuditResponse;
import com.greatonce.oms.bridge.mall.response.RefundGetResponse;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;
import com.greatonce.oms.domain.enums.mall.MallGoodsStatus;
import com.greatonce.oms.domain.enums.mall.MallRefundPhase;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;

/**
 * 退款接口.
 * AbstractRefundBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-27
 */
public abstract class AbstractRefundBridge extends AbstractBridge implements RefundBridge {

  @Override
  public RefundQueryResponse queryRefund(RefundQueryRequest request) {
    if (isTesting()) {
      return new RefundQueryResponse(request);
    } else {
      return doQueryRefund(request);
    }
  }

  @Override
  public RefundGetResponse getRefund(RefundGetRequest request) {
    return doGetRefund(request);
  }

  @Override
  public RefundAuditResponse audit(RefundAuditRequest request) {
    if (isTesting()) {
      return new RefundAuditResponse(request);
    } else {
      return doAudit(request);
    }
  }

  @Override
  public RefundAgreeResponse agree(RefundAgreeRequest request) {
    if (isTesting()) {
      return new RefundAgreeResponse(request);
    } else {
      return doAgree(request);
    }
  }

  protected abstract RefundQueryResponse doQueryRefund(RefundQueryRequest request);

  protected abstract RefundGetResponse doGetRefund(RefundGetRequest request);


  protected RefundAuditResponse doAudit(RefundAuditRequest request) {
    return new RefundAuditResponse(request);
  }

  protected RefundAgreeResponse doAgree(RefundAgreeRequest request) {
    return new RefundAgreeResponse(request);
  }


  protected MallGoodsStatus toOmsGoodsStatus(String status) {
    if (Assert.isEmpty(status)) {
      return MallGoodsStatus.BUYER_NOT_RECEIVED;
    }
    switch (status) {
      case "BUYER_NOT_RECEIVED":
        return MallGoodsStatus.BUYER_NOT_RECEIVED;
      case "BUYER_RECEIVED":
        return MallGoodsStatus.BUYER_RECEIVED;
      case "BUYER_RETURNED_GOODS":
        return MallGoodsStatus.BUYER_RETURNED_GOODS;
      default:
        return MallGoodsStatus.BUYER_NOT_RECEIVED;
    }
  }

  protected MallRefundPhase toOmsRefundPhase(String status) {
    switch (status) {
      case "ON_SALE":
        return MallRefundPhase.ON_SALE;
      case "AFTER_SALE":
        return MallRefundPhase.AFTER_SALE;
      default:
        return MallRefundPhase.ON_SALE;
    }
  }

  protected MallSalesOrderStatus toOmsTradeStatus(String status) {
    if (status == null) {
      return MallSalesOrderStatus.UNKNOWN;
    }
    switch (status.toUpperCase()) {
      case "WAIT_SELLER_SEND_GOODS":
        return MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS;
      case "WAIT_BUYER_CONFIRM_GOODS":
        return MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS;
      case "TRADE_FINISHED":
        return MallSalesOrderStatus.TRADE_FINISHED;
      case "TRADE_NO_CREATE_PAY":
        return MallSalesOrderStatus.TRADE_NO_CREATE_PAY;
      case "WAIT_BUYER_PAY":
        return MallSalesOrderStatus.WAIT_BUYER_PAY;
      case "TRADE_BUYER_SIGNED":
        return MallSalesOrderStatus.TRADE_BUYER_SIGNED;
      case "TRADE_CLOSED":
        return MallSalesOrderStatus.TRADE_CLOSE;
      case "TRADE_CLOSED_BY_TAOBAO":
        return MallSalesOrderStatus.TRADE_CLOSED_BY_TAOBAO;
      default:
        return MallSalesOrderStatus.UNKNOWN;
    }
  }
}
