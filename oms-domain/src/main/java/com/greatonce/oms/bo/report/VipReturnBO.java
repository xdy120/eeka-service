package com.greatonce.oms.bo.report;

import java.time.LocalDateTime;

public class VipReturnBO {

  private String storeName;
  private String productCode;
  private String productName;
  private String skuCode;
  private String skuName;
  private Integer quantity;
  private LocalDateTime lastInTime;
  private Double refundableAmount;
  private String vipReturnNoticeCode;
  private Integer noticeQuantity;
  private Integer inQuantity;

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public LocalDateTime getLastInTime() {
    return lastInTime;
  }

  public void setLastInTime(LocalDateTime lastInTime) {
    this.lastInTime = lastInTime;
  }

  public Double getRefundableAmount() {
    return refundableAmount;
  }

  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  public String getVipReturnNoticeCode() {
    return vipReturnNoticeCode;
  }

  public void setVipReturnNoticeCode(String vipReturnNoticeCode) {
    this.vipReturnNoticeCode = vipReturnNoticeCode;
  }

  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  public Integer getInQuantity() {
    return inQuantity;
  }

  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }
}
