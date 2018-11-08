package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;

import java.time.LocalDateTime;
import java.util.List;

public class SalesQuery extends Query {

  private List<Long> storeIds;
  private LocalDateTime mallPaidTimeBegin;
  private LocalDateTime mallPaidTimeEnd;
  private Boolean oos;
  private DispatchStatus dispatchStatus;
  private DeliveryStatus deliveryStatus;
  private PresellType presellType;
  private List<String> skuCodes;
  private List<String> productCodes;
  private LocalDateTime deliveryTimeBegin;
  private LocalDateTime deliveryTimeEnd;
  private LocalDateTime createdTimeBegin;
  private LocalDateTime createdTimeEnd;
  private SalesOrderType salesOrderType;

  public LocalDateTime getMallPaidTimeBegin() {
    return mallPaidTimeBegin;
  }

  public void setMallPaidTimeBegin(LocalDateTime mallPaidTimeBegin) {
    this.mallPaidTimeBegin = mallPaidTimeBegin;
  }

  public LocalDateTime getMallPaidTimeEnd() {
    return mallPaidTimeEnd;
  }

  public void setMallPaidTimeEnd(LocalDateTime mallPaidTimeEnd) {
    this.mallPaidTimeEnd = mallPaidTimeEnd;
  }

  public Boolean getOos() {
    return oos;
  }

  public void setOos(Boolean oos) {
    this.oos = oos;
  }

  public DispatchStatus getDispatchStatus() {
    return dispatchStatus;
  }

  public void setDispatchStatus(DispatchStatus dispatchStatus) {
    this.dispatchStatus = dispatchStatus;
  }

  public DeliveryStatus getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public PresellType getPresellType() {
    return presellType;
  }

  public void setPresellType(PresellType presellType) {
    this.presellType = presellType;
  }

  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  public List<String> getSkuCodes() {
    return skuCodes;
  }

  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  public List<String> getProductCodes() {
    return productCodes;
  }

  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
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

  public LocalDateTime getCreatedTimeBegin() {
    return createdTimeBegin;
  }

  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  public LocalDateTime getCreatedTimeEnd() {
    return createdTimeEnd;
  }

  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  public SalesOrderType getSalesOrderType() {
    return salesOrderType;
  }

  public void setSalesOrderType(SalesOrderType salesOrderType) {
    this.salesOrderType = salesOrderType;
  }

}
