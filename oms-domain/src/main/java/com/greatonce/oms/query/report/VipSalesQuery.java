package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;
import java.util.List;

public class VipSalesQuery extends Query {

  private List<Long> storeIds;
  private LocalDateTime deliveryTimeBegin;
  private LocalDateTime deliveryTimeEnd;
  private String productCode;
  private String skuCode;

  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  public LocalDateTime getDeliveryTimeBegin() {
    return deliveryTimeBegin;
  }

  public void setDeliveryTimeBegin(LocalDateTime deliveryTimeBegin) {
    this.deliveryTimeBegin = deliveryTimeBegin;
  }

  public LocalDateTime getDeliveryTimeEnd() {
    return deliveryTimeEnd;
  }

  public void setDeliveryTimeEnd(LocalDateTime deliveryTimeEnd) {
    this.deliveryTimeEnd = deliveryTimeEnd;
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
}
