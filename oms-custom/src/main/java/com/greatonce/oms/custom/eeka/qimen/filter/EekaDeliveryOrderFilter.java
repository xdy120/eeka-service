package com.greatonce.oms.custom.eeka.qimen.filter;

import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.bridge.wms.DeliveryOrderFilter;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
@Component
public class EekaDeliveryOrderFilter implements DeliveryOrderFilter {

  @Autowired
  private RegionService regionService;

  @Override
  public void execute(DeliveryOrderCreateRequest request, DispatchOrder dispatchOrder) {
    Region province = regionService.getByKey(dispatchOrder.getProvinceId());
    request.setReceiverProvinceCode(province.getRegionCode());
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }
}
