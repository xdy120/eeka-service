package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/26
 * remark:
 */
public class RefundGetResponse extends MallResponse<RefundGetRequest> {

  private MallRefundOrderInfo mallRefundOrder;

  public RefundGetResponse(RefundGetRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public RefundGetResponse(RefundGetRequest request, MallRefundOrderInfo mallRefundOrder) {
    super(request);
    this.mallRefundOrder = mallRefundOrder;
  }

  public MallRefundOrderInfo getMallRefundOrder() {
    return mallRefundOrder;
  }

  public void setMallRefundOrder(MallRefundOrderInfo mallRefundOrder) {
    this.mallRefundOrder = mallRefundOrder;
  }
}
