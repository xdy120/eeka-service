package com.greatonce.oms.bo.stock;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author yiyang
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/14
 */
public class StockLoanInCancelBO extends VersionBO {

  private Long stockLoanInId;
  private String reason;

  public Long getStockLoanInId() {
    return stockLoanInId;
  }

  public void setStockLoanInId(Long stockLoanInId) {
    this.stockLoanInId = stockLoanInId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
