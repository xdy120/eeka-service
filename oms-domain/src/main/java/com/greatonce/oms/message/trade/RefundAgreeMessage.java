package com.greatonce.oms.message.trade;

import com.greatonce.oms.message.Message;

/**
 * 售后同意消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/10
 */
public class RefundAgreeMessage extends Message {

  private Long refundApplyOrderId;
  private String refundVersion;

  public RefundAgreeMessage(Long refundApplyOrderId, String refundVersion) {
    this.refundApplyOrderId = refundApplyOrderId;
    this.refundVersion = refundVersion;
  }

  public Long getRefundApplyOrderId() {
    return refundApplyOrderId;
  }

  public void setRefundApplyOrderId(Long refundApplyOrderId) {
    this.refundApplyOrderId = refundApplyOrderId;
  }

  public String getRefundVersion() {
    return refundVersion;
  }

  public void setRefundVersion(String refundVersion) {
    this.refundVersion = refundVersion;
  }

  @Override
  public String routingKey() {
    return "oms.trade.mall.refund.agree";
  }
}
