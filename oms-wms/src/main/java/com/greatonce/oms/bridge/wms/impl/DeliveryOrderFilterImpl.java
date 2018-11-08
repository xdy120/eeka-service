package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.DeliveryOrderFilter;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
@Component
public class DeliveryOrderFilterImpl implements DeliveryOrderFilter {

  @Override
  public void execute(DeliveryOrderCreateRequest request, DispatchOrder dispatchOrder) {
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
