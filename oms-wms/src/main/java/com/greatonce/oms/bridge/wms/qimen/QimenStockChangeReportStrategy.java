package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsStockChangeReportRequest;
import com.qimen.api.response.StockchangeReportResponse;

/**
 * 库存异动适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenStockChangeReportStrategy extends QimenStrategy {

  StockchangeReportResponse report(OmsStockChangeReportRequest request);
}
