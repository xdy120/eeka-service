package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shenzhen cca
 * @version 2018/7/23
 */
public class VipSalesMonthStatisticsQuery extends Query {

  private List<Long> storeIds;
  private LocalDateTime timeBegin;
  private LocalDateTime timeEnd;
  private String statisticsType;

  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  public LocalDateTime getTimeBegin() {
    return timeBegin;
  }

  public void setTimeBegin(LocalDateTime timeBegin) {
    this.timeBegin = timeBegin;
  }

  public LocalDateTime getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(LocalDateTime timeEnd) {
    this.timeEnd = timeEnd;
  }

  public String getStatisticsType() {
    return statisticsType;
  }

  public void setStatisticsType(String statisticsType) {
    this.statisticsType = statisticsType;
  }
}


