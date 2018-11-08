package com.greatonce.oms.bo.stock;

import com.greatonce.oms.bo.VersionBO;

/**
 * 核销BO.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-31
 */
public class StockLoanOutVerificationBO extends VersionBO {

  private Long stockLoanOutDetailId;
  private String verificationReason;

  public Long getStockLoanOutDetailId() {
    return stockLoanOutDetailId;
  }

  public void setStockLoanOutDetailId(Long stockLoanOutDetailId) {
    this.stockLoanOutDetailId = stockLoanOutDetailId;
  }

  public String getVerificationReason() {
    return verificationReason;
  }

  public void setVerificationReason(String verificationReason) {
    this.verificationReason = verificationReason;
  }
}
