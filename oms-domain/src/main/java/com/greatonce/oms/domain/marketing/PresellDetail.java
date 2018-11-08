package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDateTime;

/**
 * 预售商品信息.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PresellDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 商城商品编码.
   */
  private String mallProductId;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 预售明细id.
   */
  private Long presellDetailId;
  /**
   * 预售计划id.
   */
  private Long presellId;
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

  /**
   * 预售.
   */
  private Presell presell;

  @Override
  public void setPrimaryKey(Long pk) {
    this.presellDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.presellDetailId;
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
   * 商城商品编码.
   */
  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId == null ? null : mallProductId.trim();
  }

  /**
   * 商城商品编码.
   */
  public String getMallProductId() {
    return this.mallProductId;
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
   * 预售明细id.
   */
  public void setPresellDetailId(Long presellDetailId) {
    this.presellDetailId = presellDetailId;
  }

  /**
   * 预售明细id.
   */
  public Long getPresellDetailId() {
    return this.presellDetailId;
  }

  /**
   * 预售计划id.
   */
  public void setPresellId(Long presellId) {
    this.presellId = presellId;
  }

  /**
   * 预售计划id.
   */
  public Long getPresellId() {
    return this.presellId;
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

  /**
   * 预售.
   */
  public void setPresell(Presell presell) {
    this.presell = presell;
  }

  /**
   * 预售.
   */
  public Presell getPresell() {
    return this.presell;
  }
}