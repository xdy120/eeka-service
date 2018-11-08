package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;

import java.time.LocalDateTime;

/**
 * RefundQueryRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class ExchangeQueryRequest extends MallRequest {

  private int page;
  private String tag;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private StoreDownloadConfig storeDownloadConfig;

  public ExchangeQueryRequest(Store store) {
    super(store);
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public LocalDateTime getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(LocalDateTime beginTime) {
    this.beginTime = beginTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public StoreDownloadConfig getStoreDownloadConfig() {
    return storeDownloadConfig;
  }

  public void setStoreDownloadConfig(StoreDownloadConfig storeDownloadConfig) {
    this.storeDownloadConfig = storeDownloadConfig;
  }
}
