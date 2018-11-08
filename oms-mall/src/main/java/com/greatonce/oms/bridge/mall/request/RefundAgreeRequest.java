package com.greatonce.oms.bridge.mall.request;


import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.RefundApplyOrder;

/**
 * RefundAgreeRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class RefundAgreeRequest extends MallRequest {

  private String operator;
  private String reason;
  private RefundApplyOrder refundApplyOrder;

  public RefundAgreeRequest(Store store) {
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
