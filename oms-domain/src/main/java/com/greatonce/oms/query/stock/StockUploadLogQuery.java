package com.greatonce.oms.query.stock;


import com.greatonce.core.database.Query;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/9/12
 */
public class StockUploadLogQuery extends Query {

  private String storeId;
  private String batchId;
  private String productCode;
  private String skuCode;
  private String status;
  private LocalDateTime beginUploadTime;
  private LocalDateTime endUploadTime;


  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getBeginUploadTime() {
    return beginUploadTime;
  }

  public void setBeginUploadTime(LocalDateTime beginUploadTime) {
    this.beginUploadTime = beginUploadTime;
  }

  public LocalDateTime getEndUploadTime() {
    return endUploadTime;
  }

  public void setEndUploadTime(LocalDateTime endUploadTime) {
    this.endUploadTime = endUploadTime;
  }
}
