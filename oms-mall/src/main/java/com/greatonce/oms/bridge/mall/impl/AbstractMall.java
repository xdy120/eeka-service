package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.base.Store;

/**
 * 商城业务抽象类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/3
 */
public abstract class AbstractMall {


  /**
   * 获取默认请求地址.
   */
  protected abstract String getDefaultUrl();

  /**
   * 获取服务URL，如果接口没有配置，使用默认的.
   */
  public String getUrl(Store store) {
    if (Assert.isEmpty(store.getMallApp().getServiceUrl())) {
      return getDefaultUrl();
    }
    return store.getMallApp().getServiceUrl();
  }

}
