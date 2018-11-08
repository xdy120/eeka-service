package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDateTime;

/**
 * 出库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockOutBatchDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 出库金额.
   */
  private Double outAmount;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
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
   * 出库批次明细id.
   */
  private Long stockOutBatchDetailId;
  /**
   * 出库批次id.
   */
  private Long stockOutBatchId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.stockOutBatchDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockOutBatchDetailId;
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
   * 出库金额.
   */
  public void setOutAmount(Double outAmount) {
    this.outAmount = outAmount;
  }

  /**
   * 出库金额.
   */
  public Double getOutAmount() {
    return this.outAmount;
  }

  /**
   * 出库数量.
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   */
  public Integer getOutQuantity() {
    return this.outQuantity;
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
   * 出库批次明细id.
   */
  public void setStockOutBatchDetailId(Long stockOutBatchDetailId) {
    this.stockOutBatchDetailId = stockOutBatchDetailId;
  }

  /**
   * 出库批次明细id.
   */
  public Long getStockOutBatchDetailId() {
    return this.stockOutBatchDetailId;
  }

  /**
   * 出库批次id.
   */
  public void setStockOutBatchId(Long stockOutBatchId) {
    this.stockOutBatchId = stockOutBatchId;
  }

  /**
   * 出库批次id.
   */
  public Long getStockOutBatchId() {
    return this.stockOutBatchId;
  }
}