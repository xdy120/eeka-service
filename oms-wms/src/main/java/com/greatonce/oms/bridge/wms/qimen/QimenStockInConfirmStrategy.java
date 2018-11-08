package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.qimen.api.response.ReturnorderConfirmResponse;

/**
 * 入库确认适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenStockInConfirmStrategy extends QimenStrategy {

  ReturnorderConfirmResponse confirm(OmsEntryOrderConfirmRequest request);
}
