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
public class ExchangeAgreeRequest extends MallRequest {

  private String operator;
  /**
   * 同意换货的 换货地址id  就是  买家把货退到商家的地址
   */
  private Long addressId;
  /**
   * 换货单Id
   */
  private Long disputeId;

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public Long getDisputeId() {
    return disputeId;
  }

  public void setDisputeId(Long disputeId) {
    this.disputeId = disputeId;
  }

  private RefundApplyOrder refundApplyOrder;

  public ExchangeAgreeRequest(Store store) {
    super(store);
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }


  public RefundApplyOrder getRefundApplyOrder() {
    return refundApplyOrder;
  }

  public void setRefundApplyOrder(RefundApplyOrder refundApplyOrder) {
    this.refundApplyOrder = refundApplyOrder;
  }
}
