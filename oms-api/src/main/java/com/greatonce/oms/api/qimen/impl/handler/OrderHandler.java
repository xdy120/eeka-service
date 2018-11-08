package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.domain.enums.OrderType;

/**
 * 单据处理接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */
public interface OrderHandler {
  /**
   * 支持的单据类型.
   */
  OrderType[] supports();

  /**
   * 通知单据处理状态.
   */
  void reportOrderProcess(OmsOrderProcessReportRequest request);
}
