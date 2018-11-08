package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;

/**
 * 发货单取消
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
public class StockInOrderCancelRequest extends WmsOrderCancelRequest {

  public StockInOrderCancelRequest(Warehouse warehouse) {
    super(warehouse);
  }
}
