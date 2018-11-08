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
public class ExchangeRefuseRequest extends MallRequest {

  private Long disputeId;
  private Long reasonId;
  private String reason;
  private RefundApplyOrder refundApplyOrder;

  public ExchangeRefuseRequest(Store store) {
    super(store);
  }

  public Long getDisputeId() {
    return disputeId;
  }

  public void setDisputeId(Long disputeId) {
    this.disputeId = disputeId;
  }

  public Long getReasonId() {
    return reasonId;
  }

  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
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
