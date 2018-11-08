package com.greatonce.oms.bo.trade;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-05-14
 * Time: 16:45
 * Description:
 */
public class OutDetail {

  private String productCode;
  private Long productId;
  private String productName;
  private Integer quantity;
  private String skuCode;
  private Long skuId;
  private String skuName;
  private Long sourceOrderId;
  private String sourceSkuCode;

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public Long getSourceOrderId() {
    return sourceOrderId;
  }

  public void setSourceOrderId(Long sourceOrderId) {
    this.sourceOrderId = sourceOrderId;
  }

  public String getSourceSkuCode() {
    return sourceSkuCode;
  }

  public void setSourceSkuCode(String sourceSkuCode) {
    this.sourceSkuCode = sourceSkuCode;
  }
}
