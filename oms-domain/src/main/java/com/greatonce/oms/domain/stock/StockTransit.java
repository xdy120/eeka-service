package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 在途库存.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockTransit extends BaseDO {
  /**
   * 到货日期.
   */
  private LocalDate arrivalDate;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 在途id.
   */
  private Long stockTransitId;
  /**
   * 在途数量.
   */
  private Integer transitQuantity;


  @Override
  public void setPrimaryKey(Long pk) {
    this.stockTransitId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockTransitId;
  }


  /**
   * 到货日期.
   */
  public void setArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  /**
   * 到货日期.
   */
  public LocalDate getArrivalDate() {
    return this.arrivalDate;
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
   * 在途id.
   */
  public void setStockTransitId(Long stockTransitId) {
    this.stockTransitId = stockTransitId;
  }

  /**
   * 在途id.
   */
  public Long getStockTransitId() {
    return this.stockTransitId;
  }

  /**
   * 在途数量.
   */
  public void setTransitQuantity(Integer transitQuantity) {
    this.transitQuantity = transitQuantity;
  }

  /**
   * 在途数量.
   */
  public Integer getTransitQuantity() {
    return this.transitQuantity;
  }
}