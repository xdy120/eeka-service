package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.domain.enums.MallType;

/**
 * 商城接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
public interface MallBridge {

  /**
   * 支持的商城类型
   */
  MallType[] supports();
}
