package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.base.Warehouse;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * createOrder date:2017/8/8
 * remark:
 */
public abstract class AbstractWms {

  /**
   * 获取默认请求地址
   */
  protected abstract String getDefaultUrl();

  /**
   * 获取服务URL，如果接口没有配置，使用默认的
   */
  public String getUrl(Warehouse warehouse) {
    return Assert.isEmpty(warehouse.getWmsApp().getServiceUrl()) ? getDefaultUrl()
        : warehouse.getWmsApp().getServiceUrl();
  }
}
