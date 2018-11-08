package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.qimen.api.response.StockoutConfirmResponse;

/**
 * 出库确认适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenStockOutConfirmStrategy extends QimenStrategy {
  StockoutConfirmResponse confirm(OmsStockOutConfirmRequest request);
}
