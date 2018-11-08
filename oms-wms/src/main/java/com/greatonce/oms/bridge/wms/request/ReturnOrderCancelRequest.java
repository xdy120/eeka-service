package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;

/**
 * 发货单取消
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
public class ReturnOrderCancelRequest extends WmsOrderCancelRequest {

  public ReturnOrderCancelRequest(Warehouse warehouse) {
    super(warehouse);
  }
}
