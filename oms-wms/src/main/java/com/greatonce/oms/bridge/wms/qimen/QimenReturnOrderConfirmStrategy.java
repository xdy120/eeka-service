package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsReturnOrderConfirmRequest;
import com.qimen.api.response.ReturnorderConfirmResponse;

/**
 * 退货单确认接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenReturnOrderConfirmStrategy extends QimenStrategy {
  ReturnorderConfirmResponse confirm(OmsReturnOrderConfirmRequest request);
}
