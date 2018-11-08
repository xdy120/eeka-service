package com.greatonce.oms.bo.stock;

import com.greatonce.oms.domain.enums.OutStatus;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/10/17
 */
public class StockOutExportBO {

  private String stockOutOrderCode;

  private LocalDateTime createdTime;

  private String stockOutOrderType;

  private OutStatus outStatus;

  private String remark;

  private String creator;

  private Integer outQuantity;

  private String skuCode;

  private String skuName;

  private String productCode;

  private String productName;


  public String getStockOutOrderCode() {
    return stockOutOrderCode;
  }

  public void setStockOutOrderCode(String stockOutOrderCode) {
    this.stockOutOrderCode = stockOutOrderCode;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public String getStockOutOrderType() {
    return stockOutOrderType;
  }

  public void setStockOutOrderType(String stockOutOrderType) {
    this.stockOutOrderType = stockOutOrderType;
  }

  public OutStatus getOutStatus() {
    return outStatus;
  }

  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Integer getOutQuantity() {
    return outQuantity;
  }

  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
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

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }
}
