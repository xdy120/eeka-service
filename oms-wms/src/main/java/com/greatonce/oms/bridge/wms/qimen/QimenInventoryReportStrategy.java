package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsInventoryReportRequest;
import com.qimen.api.response.InventoryReportResponse;

/**
 * 盘点适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenInventoryReportStrategy extends QimenStrategy {

  InventoryReportResponse report(OmsInventoryReportRequest request);
}
