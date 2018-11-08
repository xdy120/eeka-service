package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.MallRequest;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import java.time.LocalDateTime;

/**
 * 唯品发货单查询.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/21
 */
public class VipDeliveryQueryRequest extends MallRequest {

  private int page;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private StoreDownloadConfig storeDownloadConfig;

  public VipDeliveryQueryRequest(Store store) {
    super(store);
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

  public void setStoreDownloadConfig(StoreDownloadConfig storeDownloadConfig) {
    this.storeDownloadConfig = storeDownloadConfig;
  }
}
