package com.greatonce.oms.bridge.wms.impl.qimen.custom.eeka;

import com.greatonce.oms.bridge.wms.impl.qimen.QimenProductBridge;
import com.greatonce.oms.domain.enums.WmsType;
import org.springframework.stereotype.Component;

/**
 * 赢家商品接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class EekaProductBridge extends QimenProductBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }
}
