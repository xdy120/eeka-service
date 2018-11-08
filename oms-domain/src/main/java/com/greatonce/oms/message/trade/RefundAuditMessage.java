package com.greatonce.oms.message.trade;

import com.greatonce.oms.message.Message;

/**
 * @author Shenzhen cca
 * @version 2018/9/19
 */
public class RefundAuditMessage extends Message {

  private Long refundApplyOrderId;

  public Long getRefundApplyOrderId() {
    return refundApplyOrderId;
  }

  public void setRefundApplyOrderId(Long refundApplyOrderId) {
    this.refundApplyOrderId = refundApplyOrderId;
  }

  public RefundAuditMessage(Long refundApplyOrderId) {
    this.refundApplyOrderId = refundApplyOrderId;
  }

  @Override
  public String routingKey() {
    return "oms.trade.return.order.scan";
  }
}
