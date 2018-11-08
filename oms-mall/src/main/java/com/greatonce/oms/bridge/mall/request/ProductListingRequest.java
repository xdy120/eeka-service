package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;

/**
 * SKU数量上传信息.
 *
 * @author ginta
 */
public class ProductListingRequest extends MallRequest {

  private String mallProductId;
  private String mallSkuId;
  private String mallProductOutCode;
  private String mallSkuOutCode;

  public ProductListingRequest(Store store) {
    super(store);
  }

  public String getMallProductId() {
    return mallProductId;
  }

  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId;
  }

  public String getMallSkuId() {
    return mallSkuId;
  }

  public void setMallSkuId(String mallSkuId) {
    this.mallSkuId = mallSkuId;
  }

  public String getMallProductOutCode() {
    return mallProductOutCode;
  }

  public void setMallProductOutCode(String mallProductOutCode) {
    this.mallProductOutCode = mallProductOutCode;
  }

  public String getMallSkuOutCode() {
    return mallSkuOutCode;
  }

  public void setMallSkuOutCode(String mallSkuOutCode) {
    this.mallSkuOutCode = mallSkuOutCode;
  }
}
