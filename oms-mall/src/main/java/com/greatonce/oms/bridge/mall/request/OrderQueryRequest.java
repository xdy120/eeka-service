package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;

import java.time.LocalDateTime;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/15
 * remark:
 */
//订单查询请求类
public class OrderQueryRequest extends MallRequest {

  private int page;
  private String tradeId;
  private String tag;
  private MallSalesOrderStatus status;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private StoreDownloadConfig storeDownloadConfig;

  public OrderQueryRequest(Store store, String tradeId) {
    this(store);
    this.tradeId = tradeId;
  }

  public OrderQueryRequest(Store store) {
    super(store);
    this.page = 1;
    this.status = MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public MallSalesOrderStatus getStatus() {
    return status;
  }

  public void setStatus(MallSalesOrderStatus status) {
    this.status = status;
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
