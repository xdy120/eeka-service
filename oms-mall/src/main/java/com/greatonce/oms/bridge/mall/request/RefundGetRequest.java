package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;

/**
 * RefundGetRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class RefundGetRequest extends MallRequest {

  private String refundId;

  public RefundGetRequest(Store store, String refundId) {
    super(store);
    this.refundId = refundId;
  }

  public String getRefundId() {
    return refundId;
  }

  public void setRefundId(String refundId) {
    this.refundId = refundId;
  }
}
