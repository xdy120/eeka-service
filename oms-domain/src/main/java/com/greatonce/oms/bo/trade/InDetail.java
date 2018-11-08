package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.product.ProductType;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-05-14
 * Time: 16:40
 * Description:
 * 退换货单的 退入商品
 */
public class InDetail {

  private Double actualAmount;
  private String productCode;
  private Long productId;
  private String productName;
  private Integer quantity;
  private Double refundableAmount;
  private String returnReasonType;
  private Long salesOrderId;
  private String skuCode;
  private Long skuId;
  private String skuName;
  private ProductType productType;
  private Integer applyQuantity;
  private Boolean isSkuAbnormal;

  public InDetail() {
    this.isSkuAbnormal = false;
  }

  public Double getActualAmount() {
    return actualAmount;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

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

  public Double getRefundableAmount() {
    return refundableAmount;
  }

  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  public String getReturnReasonType() {
    return returnReasonType;
  }

  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType;
  }

  public Long getSalesOrderId() {
    return salesOrderId;
  }

  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
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

  public Integer getApplyQuantity() {
    return applyQuantity;
  }

  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  public Boolean getSkuAbnormal() {
    return isSkuAbnormal;
  }

  public void setSkuAbnormal(Boolean skuAbnormal) {
    isSkuAbnormal = skuAbnormal;
  }
}
