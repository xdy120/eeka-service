package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;

/**
 * 入库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockInBatchDetailQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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


  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 入库金额.
   * @param inAmount 入库金额
   */
  public void setInAmount(Double inAmount) {
    this.inAmount = inAmount;
  }

  /**
   * 入库金额.
   * @return 入库金额
   */
  public Double getInAmount() {
      return this.inAmount;
  }

  /**
   * 入库正品数量.
   * @param inQuantity 入库正品数量
   */
  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  /**
   * 入库正品数量.
   * @return 入库正品数量
   */
  public Integer getInQuantity() {
      return this.inQuantity;
  }

  /**
   * 入库次品数量.
   * @param inSubstandardQuantity 入库次品数量
   */
  public void setInSubstandardQuantity(Integer inSubstandardQuantity) {
    this.inSubstandardQuantity = inSubstandardQuantity;
  }

  /**
   * 入库次品数量.
   * @return 入库次品数量
   */
  public Integer getInSubstandardQuantity() {
      return this.inSubstandardQuantity;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 商品编码.
   * @param productCode 商品编码
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   * @return 商品编码
   */
  public String getProductCode() {
      return this.productCode;
  }

  /**
   * 商品id.
   * @param productId 商品id
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   * @return 商品id
   */
  public Long getProductId() {
      return this.productId;
  }

  /**
   * 商品规格编码.
   * @param skuCode 商品规格编码
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   * @return 商品规格编码
   */
  public String getSkuCode() {
      return this.skuCode;
  }

  /**
   * 商品规格id.
   * @param skuId 商品规格id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   * @return 商品规格id
   */
  public Long getSkuId() {
      return this.skuId;
  }

  /**
   * 入库批次明细id.
   * @param stockInBatchDetailId 入库批次明细id
   */
  public void setStockInBatchDetailId(Long stockInBatchDetailId) {
    this.stockInBatchDetailId = stockInBatchDetailId;
  }

  /**
   * 入库批次明细id.
   * @return 入库批次明细id
   */
  public Long getStockInBatchDetailId() {
      return this.stockInBatchDetailId;
  }

  /**
   * 入库批次id.
   * @param stockInBatchId 入库批次id
   */
  public void setStockInBatchId(Long stockInBatchId) {
    this.stockInBatchId = stockInBatchId;
  }

  /**
   * 入库批次id.
   * @return 入库批次id
   */
  public Long getStockInBatchId() {
      return this.stockInBatchId;
  }
}