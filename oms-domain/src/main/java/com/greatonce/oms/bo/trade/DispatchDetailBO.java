package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.time.LocalDateTime;

/**
 * @author buer
 * @version 2018-01-06 10:31
 */
public class DispatchDetailBO extends SalesOrderDetail {

  private String tradeId;
  private String salesOrderCode;
  private LocalDateTime paidTime;

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getSalesOrderCode() {
    return salesOrderCode;
  }

  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode;
  }

  public LocalDateTime getPaidTime() {
    return paidTime;
  }

  public void setPaidTime(LocalDateTime paidTime) {
    this.paidTime = paidTime;
  }
}
