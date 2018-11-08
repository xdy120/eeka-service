package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDateTime;

/**
 * 入库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockInBatchDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 入库金额.
   */
  private Double inAmount;
  /**
   * 入库正品数量.
   */
  private Integer inQuantity;
  /**
   * 入库次品数量.
   */
  private Integer inSubstandardQuantity;
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
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 入库批次明细id.
   */
  private Long stockInBatchDetailId;
  /**
   * 入库批次id.
   */
  private Long stockInBatchId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.stockInBatchDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockInBatchDetailId;
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
   * 入库金额.
   */
  public void setInAmount(Double inAmount) {
    this.inAmount = inAmount;
  }

  /**
   * 入库金额.
   */
  public Double getInAmount() {
    return this.inAmount;
  }

  /**
   * 入库正品数量.
   */
  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  /**
   * 入库正品数量.
   */
  public Integer getInQuantity() {
    return this.inQuantity;
  }

  /**
   * 入库次品数量.
   */
  public void setInSubstandardQuantity(Integer inSubstandardQuantity) {
    this.inSubstandardQuantity = inSubstandardQuantity;
  }

  /**
   * 入库次品数量.
   */
  public Integer getInSubstandardQuantity() {
    return this.inSubstandardQuantity;
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
   * 入库批次明细id.
   */
  public void setStockInBatchDetailId(Long stockInBatchDetailId) {
    this.stockInBatchDetailId = stockInBatchDetailId;
  }

  /**
   * 入库批次明细id.
   */
  public Long getStockInBatchDetailId() {
    return this.stockInBatchDetailId;
  }

  /**
   * 入库批次id.
   */
  public void setStockInBatchId(Long stockInBatchId) {
    this.stockInBatchId = stockInBatchId;
  }

  /**
   * 入库批次id.
   */
  public Long getStockInBatchId() {
    return this.stockInBatchId;
  }
}