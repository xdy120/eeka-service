package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.domain.enums.WmsType;

/**
 * WMS接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
public interface WmsBridge {

  WmsType[] supports();
}
