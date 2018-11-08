package com.greatonce.oms.bo.stock;

import com.greatonce.oms.domain.vip.VipDispatch;
import java.time.LocalDate;

public class StockLoanOutBO extends VipDispatch {

  private String loanUser;
  private int needReturn;
  private int overdue;
  private LocalDate expectReturnDate;
  private Integer noticeQuantity;
  private Integer planQuantity;
  private String returnQuantity;
  private String surplusQuantity;
  private String productCode;
  private String productName;
  private String skuCode;
  private String skuName;

  public String getLoanUser() {
    return loanUser;
  }

  public void setLoanUser(String loanUser) {
    this.loanUser = loanUser;
  }

  public int getNeedReturn() {
    return needReturn;
  }

  public void setNeedReturn(int needReturn) {
    this.needReturn = needReturn;
  }

  public int getOverdue() {
    return overdue;
  }

  public void setOverdue(int overdue) {
    this.overdue = overdue;
  }

  public LocalDate getExpectReturnDate() {
    return expectReturnDate;
  }

  public void setExpectReturnDate(LocalDate expectReturnDate) {
    this.expectReturnDate = expectReturnDate;
  }

  @Override
  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  @Override
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  public Integer getPlanQuantity() {
    return planQuantity;
  }

  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  public String getReturnQuantity() {
    return returnQuantity;
  }

  public void setReturnQuantity(String returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  public String getSurplusQuantity() {
    return surplusQuantity;
  }

  public void setSurplusQuantity(String surplusQuantity) {
    this.surplusQuantity = surplusQuantity;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }
}
