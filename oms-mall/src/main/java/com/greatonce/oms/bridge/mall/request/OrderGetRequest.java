package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/19
 * remark:
 */
public class OrderGetRequest extends MallRequest {

  private String tradeId;
  private StoreDownloadConfig storeDownloadConfig;

  public StoreDownloadConfig getStoreDownloadConfig() {
    return storeDownloadConfig;
  }

  public void setStoreDownloadConfig(StoreDownloadConfig storeDownloadConfig) {
    this.storeDownloadConfig = storeDownloadConfig;
  }

  public OrderGetRequest(Store store) {
    super(store);
  }

  public OrderGetRequest(Store store, String tradeId) {
    super(store);
    this.tradeId = tradeId;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }
}
