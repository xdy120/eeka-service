package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;

/**
 * 出库单处理请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
public interface StockOutHandler extends OrderHandler {

  /**
   * 处理出库确认请求.
   *
   * @param request 出库确认请求
   */
  void confirm(OmsStockOutConfirmRequest request);
}
