package com.greatonce.oms.bo.report;

import java.time.LocalDateTime;

public class VipSalesBO {

  private String storeName;
  private String productCode;
  private String productName;
  private String skuCode;
  private String skuName;
  private Integer quantity;
  private Double sellingAmount;
  private LocalDateTime lastOutTime;

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

  public Double getSellingAmount() {
    return sellingAmount;
  }

  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  public LocalDateTime getLastOutTime() {
    return lastOutTime;
  }

  public void setLastOutTime(LocalDateTime lastOutTime) {
    this.lastOutTime = lastOutTime;
  }
}
