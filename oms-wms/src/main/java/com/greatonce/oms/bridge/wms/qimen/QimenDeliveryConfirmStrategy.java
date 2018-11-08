package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest;
import com.qimen.api.response.DeliveryorderConfirmResponse;

/**
 * 发货确认适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenDeliveryConfirmStrategy extends QimenStrategy {

  DeliveryorderConfirmResponse confirm(OmsDeliveryOrderConfirmRequest request);
}
