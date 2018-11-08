package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.domain.trade.DispatchOrder;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface DeliveryOrderFilter extends WmsFilter {

  void execute(DeliveryOrderCreateRequest request, DispatchOrder dispatchOrder);
}
