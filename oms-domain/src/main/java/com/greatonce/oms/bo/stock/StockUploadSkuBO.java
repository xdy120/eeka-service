package com.greatonce.oms.bo.stock;


/**
 * 库存上传BO.
 */
public class StockUploadSkuBO {

  private Long skuId;
  private String productCode;
  private String skuCode;
  private Integer quantity;
  private boolean autoCalcQuantity;

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public boolean isAutoCalcQuantity() {
    return autoCalcQuantity;
  }

  public void setAutoCalcQuantity(boolean autoCalcQuantity) {
    this.autoCalcQuantity = autoCalcQuantity;
  }
}
