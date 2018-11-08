package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.ReturnOrderFilter;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
@Component
public class ReturnOrderFilterImpl implements ReturnOrderFilter {

  @Override
  public void execute(ReturnOrderCreateRequest request, ReturnNoticeOrder returnNoticeOrder) {

  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
