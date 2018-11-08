package com.greatonce.oms.bo.stock;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author yiyang
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/14
 */
public class StockLoanOutCancelBO extends VersionBO {

  private Long stockLoanOutId;
  private String reason;

  public Long getStockLoanOutId() {
    return stockLoanOutId;
  }

  public void setStockLoanOutId(Long stockLoanOutId) {
    this.stockLoanOutId = stockLoanOutId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
