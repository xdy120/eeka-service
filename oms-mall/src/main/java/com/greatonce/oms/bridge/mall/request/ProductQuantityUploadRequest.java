package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import java.util.List;

/**
 * 商品数量上传信息.
 *
 * @author ginta
 */
public class ProductQuantityUploadRequest extends MallRequest {

  private List<SkuQuantityUploadRequest> skus;
  private boolean listing;

  public ProductQuantityUploadRequest(Store store) {
    super(store);
  }

  public List<SkuQuantityUploadRequest> getSkus() {
    return skus;
  }

  public void setSkus(List<SkuQuantityUploadRequest> skus) {
    this.skus = skus;
  }

  public boolean isListing() {
    return listing;
  }

  public void setListing(boolean listing) {
    this.listing = listing;
  }
}
