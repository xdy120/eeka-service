package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.ReturnOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse;

/**
 * 退货单
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public interface ReturnOrderBridge extends WmsBridge {

  /**
   * 创建单据.
   */
  ReturnOrderCreateResponse createOrder(ReturnOrderCreateRequest request);

  /**
   * 取消单据.
   */
  ReturnOrderCancelResponse cancelOrder(ReturnOrderCancelRequest request);

  /**
   * 查询单据.
   */
  ReturnOrderQueryResponse queryOrder(ReturnOrderQueryRequest request);
}
