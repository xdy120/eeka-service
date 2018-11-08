package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;

public class SalesOrderReturnQuery extends Query {

  private Long storeId;
  private LocalDateTime unpackedTimeBegin;
  private LocalDateTime unpackedTimeEnd;
  private LocalDateTime appliedTimeBegin;
  private LocalDateTime appliedTimeEnd;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
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

  public LocalDateTime getAppliedTimeBegin() {
    return appliedTimeBegin;
  }

  public void setAppliedTimeBegin(LocalDateTime appliedTimeBegin) {
    this.appliedTimeBegin = appliedTimeBegin;
  }

  public LocalDateTime getAppliedTimeEnd() {
    return appliedTimeEnd;
  }

  public void setAppliedTimeEnd(LocalDateTime appliedTimeEnd) {
    this.appliedTimeEnd = appliedTimeEnd;
  }
}
