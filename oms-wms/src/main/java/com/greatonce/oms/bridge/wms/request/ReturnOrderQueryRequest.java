package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;

/**
 * 发货单查询请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-21
 */
public class ReturnOrderQueryRequest extends WmsOrderRequest {

  public ReturnOrderQueryRequest(Warehouse warehouse) {
    super(warehouse);
  }
}
