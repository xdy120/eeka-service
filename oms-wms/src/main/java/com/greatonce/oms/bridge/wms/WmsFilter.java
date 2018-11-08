package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.domain.enums.WmsType;

/**
 * WMS过滤器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface WmsFilter {

  WmsType[] supports();
}
