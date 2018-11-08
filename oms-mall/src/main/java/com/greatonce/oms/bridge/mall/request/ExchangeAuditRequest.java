package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.RefundApplyOrder;

public class ExchangeAuditRequest extends MallRequest {

  private String operator;
  private String reason;
  private RefundApplyOrder refundApplyOrder;

  public ExchangeAuditRequest(Store store) {
    super(store);
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public RefundApplyOrder getRefundApplyOrder() {
    return refundApplyOrder;
  }

  public void setRefundApplyOrder(RefundApplyOrder refundApplyOrder) {
    this.refundApplyOrder = refundApplyOrder;
  }
}
