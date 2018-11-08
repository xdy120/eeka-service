package com.greatonce.oms.bridge.mall.request;


import com.greatonce.oms.domain.base.Store;

/**
 * 店铺请求
 * CREATED by zhangqin on 2017/1/3.
 */
public abstract class MallRequest {

  private Store store;

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public MallRequest(Store store) {
    this.store = store;
  }
}
