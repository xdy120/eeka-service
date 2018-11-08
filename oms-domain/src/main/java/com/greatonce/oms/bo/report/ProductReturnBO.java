package com.greatonce.oms.bo.report;

import java.time.LocalDateTime;

public class ProductReturnBO {

  private LocalDateTime unpackedTime;
  private String productCode;
  private String productName;
  private String skuCode;
  private String skuName;
  private Integer quantity;
  private Double refundAmount;

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

  public Double getRefundAmount() {
    return refundAmount;
  }

  public void setRefundAmount(Double refundAmount) {
    this.refundAmount = refundAmount;
  }

  public LocalDateTime getUnpackedTime() {
    return unpackedTime;
  }

  public void setUnpackedTime(LocalDateTime unpackedTime) {
    this.unpackedTime = unpackedTime;
  }
}
