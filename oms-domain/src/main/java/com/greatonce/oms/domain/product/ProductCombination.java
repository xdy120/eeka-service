package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 组合商品明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ProductCombination extends BaseDO {
  /**
   * 明细id.
   */
  private Long combinationDetailId;
  /**
   * 套装id.
   */
  private Long combinationId;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 是否主商品.
   */
  private Boolean mainSku;
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
   * 数量.
   */
  private Integer quantity;
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
    this.combinationDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.combinationDetailId;
  }


  /**
   * 明细id.
   */
  public void setCombinationDetailId(Long combinationDetailId) {
    this.combinationDetailId = combinationDetailId;
  }

  /**
   * 明细id.
   */
  public Long getCombinationDetailId() {
    return this.combinationDetailId;
  }

  /**
   * 套装id.
   */
  public void setCombinationId(Long combinationId) {
    this.combinationId = combinationId;
  }

  /**
   * 套装id.
   */
  public Long getCombinationId() {
    return this.combinationId;
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
   * 是否主商品.
   */
  public void setMainSku(Boolean mainSku) {
    this.mainSku = mainSku;
  }

  /**
   * 是否主商品.
   */
  public Boolean isMainSku() {
    return this.mainSku;
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