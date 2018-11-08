package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.domain.enums.WmsType;

/**
 * 奇门适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenStrategy {

  WmsType[] supports();
}
