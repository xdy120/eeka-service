package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.product.ProductType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退换货单换出商品.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ReturnOrderOutDetail extends BaseDO {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 换货单id.
   */
  private String exchangeId;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * 商品名称.
   */
  private String productName;
  /**
   * 商品类型.
   */
  private ProductType productType;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 退换货单明细id.
   */
  private Long returnOrderDetailId;
  /**
   * 退换货单id.
   */
  private Long returnOrderId;
  /**
   * 主键Id.
   */
  private Long returnOrderOutDetailId;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 商品规格名称.
   */
  private String skuName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.returnOrderOutDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.returnOrderOutDetailId;
  }


  /**
   * 实际金额.
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 实际金额.
   */
  public Double getActualAmount() {
    return this.actualAmount;
  }

  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 换货单id.
   */
  public void setExchangeId(String exchangeId) {
    this.exchangeId = exchangeId == null ? null : exchangeId.trim();
  }

  /**
   * 换货单id.
   */
  public String getExchangeId() {
    return this.exchangeId;
  }

  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 商品编码.
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   */
  public String getProductCode() {
    return this.productCode;
  }

  /**
   * 商品id.
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   */
  public Long getProductId() {
    return this.productId;
  }

  /**
   * 商品名称.
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * 商品类型.
   */
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  /**
   * 商品类型.
   */
  public ProductType getProductType() {
    return this.productType;
  }

  /**
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
  }

  /**
   * 退换货单明细id.
   */
  public void setReturnOrderDetailId(Long returnOrderDetailId) {
    this.returnOrderDetailId = returnOrderDetailId;
  }

  /**
   * 退换货单明细id.
   */
  public Long getReturnOrderDetailId() {
    return this.returnOrderDetailId;
  }

  /**
   * 退换货单id.
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   */
  public Long getReturnOrderId() {
    return this.returnOrderId;
  }


  /**
   * 主键Id.
   */
  public void setReturnOrderOutDetailId(Long returnOrderOutDetailId) {
    this.returnOrderOutDetailId = returnOrderOutDetailId;
  }

  /**
   * 主键Id.
   */
  public Long getReturnOrderOutDetailId() {
    return this.returnOrderOutDetailId;
  }

  /**
   * 商品规格编码.
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   */
  public String getSkuCode() {
    return this.skuCode;
  }

  /**
   * 商品规格id.
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   */
  public Long getSkuId() {
    return this.skuId;
  }

  /**
   * 商品规格名称.
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   */
  public String getSkuName() {
    return this.skuName;
  }
}