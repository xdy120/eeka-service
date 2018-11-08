package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;

/**
 * @author Shenzhen cca
 * @version 2018/8/30
 */
public class OrderProcessQueryRequest extends WmsOrderRequest {

  public OrderProcessQueryRequest(Warehouse warehouse) {
    super(warehouse);
  }
}
