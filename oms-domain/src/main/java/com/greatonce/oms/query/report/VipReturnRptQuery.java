package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;
import java.util.List;

public class VipReturnRptQuery extends Query {

  private List<Long> storeIds;
  private LocalDateTime unpackedTimeBegin;
  private LocalDateTime unpackedTimeEnd;
  private String productCode;
  private String skuCode;
  private LocalDateTime lastInTimeBegin;
  private LocalDateTime lastInTimeEnd;

  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  public LocalDateTime getUnpackedTimeBegin() {
    return unpackedTimeBegin;
  }

  public void setUnpackedTimeBegin(LocalDateTime unpackedTimeBegin) {
    this.unpackedTimeBegin = unpackedTimeBegin;
  }

  public LocalDateTime getUnpackedTimeEnd() {
    return unpackedTimeEnd;
  }

  public void setUnpackedTimeEnd(LocalDateTime unpackedTimeEnd) {
    this.unpackedTimeEnd = unpackedTimeEnd;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public LocalDateTime getLastInTimeBegin() {
    return lastInTimeBegin;
  }

  public void setLastInTimeBegin(LocalDateTime lastInTimeBegin) {
    this.lastInTimeBegin = lastInTimeBegin;
  }

  public LocalDateTime getLastInTimeEnd() {
    return lastInTimeEnd;
  }

  public void setLastInTimeEnd(LocalDateTime lastInTimeEnd) {
    this.lastInTimeEnd = lastInTimeEnd;
  }
}
