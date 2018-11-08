package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockInOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse;

/**
 * 入库单.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-06-27
 */
public interface StockInOrderBridge extends WmsBridge {

  /**
   * 创建单据.
   */
  StockInOrderCreateResponse createOrder(StockInOrderCreateRequest request);

  /**
   * 取消单据.
   */
  StockInOrderCancelResponse cancelOrder(StockInOrderCancelRequest request);

  /**
   * 查询单据.
   */
  StockInOrderQueryResponse queryOrder(StockInOrderQueryRequest request);
}
