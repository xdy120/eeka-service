package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.finance.AlipayRecordType;
import java.time.LocalDateTime;

/**
 * 支付宝账单查询请求
 */
public class AlipayRecordQueryRequest extends MallRequest {

  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private AlipayRecordType type;
  private int page;

  public AlipayRecordQueryRequest(Store store) {
    super(store);
    this.page = 1;
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

  public AlipayRecordType getType() {
    return type;
  }

  public void setType(AlipayRecordType type) {
    this.type = type;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

}
