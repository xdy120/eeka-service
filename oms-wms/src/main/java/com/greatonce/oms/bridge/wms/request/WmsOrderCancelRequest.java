package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;

/**
 * WmsOrderCancelRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class WmsOrderCancelRequest extends WmsOrderRequest {

  /**
   * 取消原因
   */
  private String reason;

  public WmsOrderCancelRequest(Warehouse warehouse) {
    super(warehouse);
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
