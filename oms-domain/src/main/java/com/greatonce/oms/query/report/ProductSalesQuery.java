package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;
import java.util.List;

public class ProductSalesQuery extends Query {

  private List<String> productCodes;
  private List<String> skuCodes;
  private LocalDateTime mallPaidTimeBegin;
  private LocalDateTime mallPaidTimeEnd;
  private Boolean deliveryOnly;
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

  public List<Long> getStoreIds() {
    return storeIds;
  }

  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  public Boolean isDeliveryOnly() {
    return deliveryOnly;
  }

  public void setDeliveryOnly(Boolean deliveryOnly) {
    this.deliveryOnly = deliveryOnly;
  }
}
