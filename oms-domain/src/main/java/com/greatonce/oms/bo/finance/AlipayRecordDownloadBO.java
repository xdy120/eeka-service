package com.greatonce.oms.bo.finance;

import com.greatonce.oms.domain.base.Store;
import java.time.LocalDateTime;

public class AlipayRecordDownloadBO {

  /**
   * 店铺ID.
   */
  private Long storeId;
  /**
   * 店铺.
   */
  private Store store;
  /**
   * 开始时间.
   */
  private LocalDateTime beginTime;
  /**
   * 结束时间.
   */
  private LocalDateTime endTime;
  /**
   * 操作人
   */
  private String operator;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
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

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

}
