package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售单明细的销售统计查询
 * @author Administrator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/17
 */
public class SalesDetailSellStatisticsQuery extends Query {

  private List<Long> storeIds;
  private LocalDateTime createTimeBegin;
  private LocalDateTime createTimeEnd;

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
}
