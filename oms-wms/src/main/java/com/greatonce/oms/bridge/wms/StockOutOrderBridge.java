package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.StockOutOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse;

/**
 * 出库单接口抽象实现.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public interface StockOutOrderBridge extends WmsBridge {


  /**
   * 创建出库单.
   */
  StockOutOrderCreateResponse createOrder(StockOutOrderCreateRequest request);

  /**
   * 取消单据.
   */
  StockOutOrderCancelResponse cancelOrder(StockOutOrderCancelRequest request);

  /**
   * 查询单据.
   */
  StockOutOrderQueryResponse queryOrder(StockOutOrderQueryRequest request);
}
