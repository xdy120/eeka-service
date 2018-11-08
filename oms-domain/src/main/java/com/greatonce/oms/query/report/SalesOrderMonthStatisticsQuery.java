package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shenzhen cca
 * @version 2018/7/23
 */
public class SalesOrderMonthStatisticsQuery extends Query {

  private List<Long> storeIds;
  private LocalDateTime createTimeBegin;
  private LocalDateTime createTimeEnd;
  private String statisticsType;


  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  public LocalDateTime getCreateTimeBegin() {
    return createTimeBegin;
  }

  public void setCreateTimeBegin(LocalDateTime createTimeBegin) {
    this.createTimeBegin = createTimeBegin;
  }

  public LocalDateTime getCreateTimeEnd() {
    return createTimeEnd;
  }

  public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
    this.createTimeEnd = createTimeEnd;
  }

  public String getStatisticsType() {
    return statisticsType;
  }

  public void setStatisticsType(String statisticsType) {
    this.statisticsType = statisticsType;
  }
}
