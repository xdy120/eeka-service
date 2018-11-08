package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.domain.trade.DispatchOrder;
import com.qimen.api.request.DeliveryorderCreateRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface QimenDeliveryOrderCreateStrategy extends QimenStrategy {

  void handler(DeliveryorderCreateRequest request, DispatchOrder dispatchOrder);
}
