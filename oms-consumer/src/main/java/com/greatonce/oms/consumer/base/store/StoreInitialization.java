package com.greatonce.oms.consumer.base.store;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;

/**
 * 店铺下载初始化.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
public interface StoreInitialization {

  /**
   * 支持的商城类型.
   */
  MallType[] supports();

  /**
   * 初始化店铺.
   */
  void init(Store store);

  /**
   * 删除卸载信息.
   */
  void destroy(Store store);
}
