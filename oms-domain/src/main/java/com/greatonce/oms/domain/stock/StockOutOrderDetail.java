package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 出库单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockOutOrderDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 通知数量.
   */
  private Integer noticeQuantity;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * 单价.
   */
  private Double price;
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
   * 出库单明细id.
   */
  private Long stockOutOrderDetailId;
  /**
   * 出库单id.
   */
  private Long stockOutOrderId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.stockOutOrderDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockOutOrderDetailId;
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
   * 通知数量.
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   */
  public Integer getNoticeQuantity() {
    return this.noticeQuantity;
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
   * 计划数量.
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   */
  public Integer getPlanQuantity() {
    return this.planQuantity;
  }

  /**
   * 单价.
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * 单价.
   */
  public Double getPrice() {
    return this.price;
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
   * 出库单明细id.
   */
  public void setStockOutOrderDetailId(Long stockOutOrderDetailId) {
    this.stockOutOrderDetailId = stockOutOrderDetailId;
  }

  /**
   * 出库单明细id.
   */
  public Long getStockOutOrderDetailId() {
    return this.stockOutOrderDetailId;
  }

  /**
   * 出库单id.
   */
  public void setStockOutOrderId(Long stockOutOrderId) {
    this.stockOutOrderId = stockOutOrderId;
  }

  /**
   * 出库单id.
   */
  public Long getStockOutOrderId() {
    return this.stockOutOrderId;
  }
}