package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.product.ProductMallMapping;

/**
 * SKU数量上传信息.
 *
 * @author ginta
 */
public class SkuQuantityUploadRequest extends MallRequest {

  private ProductMallMapping mapping;
  private StockUploadType uploadType;
  private StockUploadStrategy uploadConfig;
  private int quantity;

  public SkuQuantityUploadRequest(Store store) {
    super(store);
  }

  public ProductMallMapping getMapping() {
    return mapping;
  }

  public void setMapping(ProductMallMapping mapping) {
    this.mapping = mapping;
  }

  public StockUploadType getUploadType() {
    return uploadType;
  }

  public void setUploadType(StockUploadType uploadType) {
    this.uploadType = uploadType;
  }

  public StockUploadStrategy getUploadConfig() {
    return uploadConfig;
  }

  public void setUploadConfig(StockUploadStrategy uploadConfig) {
    this.uploadConfig = uploadConfig;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
