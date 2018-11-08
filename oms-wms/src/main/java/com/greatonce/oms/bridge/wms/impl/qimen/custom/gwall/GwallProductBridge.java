package com.greatonce.oms.bridge.wms.impl.qimen.custom.gwall;

import com.greatonce.oms.bridge.wms.impl.qimen.QimenProductBridge;
import com.greatonce.oms.domain.enums.WmsType;
import org.springframework.stereotype.Component;

/**
 * 巨沃商品接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class GwallProductBridge extends QimenProductBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_GWALL};
  }
}
