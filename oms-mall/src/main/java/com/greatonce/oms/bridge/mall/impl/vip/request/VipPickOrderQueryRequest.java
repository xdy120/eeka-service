package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.MallRequest;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import java.time.LocalDateTime;

/**
 * @author buer
 * @version 2017-08-24 15:02
 */
public class VipPickOrderQueryRequest extends MallRequest {

  private int page;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private StoreDownloadConfig storeDownloadConfig;
  private String pickNos;

  public VipPickOrderQueryRequest(Store store) {
    super(store);
    this.page = 1;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
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

  public String getPickNos() {
    return pickNos;
  }

  public void setPickNos(String pickNos) {
    this.pickNos = pickNos;
  }

  public void setStoreDownloadConfig(StoreDownloadConfig storeDownloadConfig) {
    this.storeDownloadConfig = storeDownloadConfig;
  }
}
