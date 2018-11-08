package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;
import java.util.List;

public class ProductReturnQuery extends Query {

  private List<String> productCodes;
  private List<String> skuCodes;
  private LocalDateTime unpackedTimeBegin;
  private LocalDateTime unpackedTimeEnd;
  private List<Long> storeIds;

  public List<String> getProductCodes() {
    return productCodes;
  }

  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  public List<String> getSkuCodes() {
    return skuCodes;
  }

  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  public LocalDateTime getUnpackedTimeBegin() {
    return unpackedTimeBegin;
  }

  public void setUnpackedTimeBegin(LocalDateTime unpackedTimeBegin) {
    this.unpackedTimeBegin = unpackedTimeBegin;
  }

  public LocalDateTime getUnpackedTimeEnd() {
    return unpackedTimeEnd;
  }

  public void setUnpackedTimeEnd(LocalDateTime unpackedTimeEnd) {
    this.unpackedTimeEnd = unpackedTimeEnd;
  }

  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }
}
