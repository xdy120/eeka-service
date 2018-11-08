package com.greatonce.oms.bridge.wms.impl.qimen.custom.gwall;

import com.greatonce.oms.bridge.wms.impl.qimen.QimenReturnOrderBridge;
import com.greatonce.oms.domain.enums.WmsType;
import org.springframework.stereotype.Component;

/**
 * 巨沃退货单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class GwallReturnOrderBridge extends QimenReturnOrderBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_GWALL};
  }
}
