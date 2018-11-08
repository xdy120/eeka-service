package com.greatonce.oms.bo.stock;

import java.util.List;

/**
 * 同步上传BO.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/15
 */
public class SyncStockUploadBO extends StockUploadBO {

  /**
   * 是否要进行强制上架.
   */
  private boolean listing;
  /**
   * 店铺ID.
   */
  private Long storeId;
  /**
   * 要上传的SKU.
   */
  private List<SyncStockUploadUploadSkuBO> skus;

  public boolean isListing() {
    return listing;
  }

  public void setListing(boolean listing) {
    this.listing = listing;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public List<SyncStockUploadUploadSkuBO> getSkus() {
    return skus;
  }

  public void setSkus(List<SyncStockUploadUploadSkuBO> skus) {
    this.skus = skus;
  }
}
