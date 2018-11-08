package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
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
public class StockTransitQuery extends Query {
  /**
   * 到货日期开始.
   */
  private LocalDate arrivalDateBegin;
  /**
   * 到货日期结束.
   */
  private LocalDate arrivalDateEnd;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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


  /**
   * 到货日期开始.
   * @param arrivalDateBegin 开始.
   */
  public void setArrivalDateBegin(LocalDate arrivalDateBegin) {
    this.arrivalDateBegin = arrivalDateBegin;
  }

  /**
   * 到货日期开始.
   * @return 到货日期开始
   */
  public LocalDate getArrivalDateBegin() {
    return this.arrivalDateBegin;
  }

  /**
   * 到货日期结束.
   * @param arrivalDateEnd 结束
   */
  public void setArrivalDateEnd(LocalDate arrivalDateEnd) {
    this.arrivalDateEnd = arrivalDateEnd;
  }

  /**
   * 到货日期结束.
   * @return 到货日期结束
   */
  public LocalDate getArrivalDateEnd() {
    return this.arrivalDateEnd;
  }

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
   * 在途id.
   * @param stockTransitId 在途id
   */
  public void setStockTransitId(Long stockTransitId) {
    this.stockTransitId = stockTransitId;
  }

  /**
   * 在途id.
   * @return 在途id
   */
  public Long getStockTransitId() {
      return this.stockTransitId;
  }

  /**
   * 在途数量.
   * @param transitQuantity 在途数量
   */
  public void setTransitQuantity(Integer transitQuantity) {
    this.transitQuantity = transitQuantity;
  }

  /**
   * 在途数量.
   * @return 在途数量
   */
  public Integer getTransitQuantity() {
      return this.transitQuantity;
  }
}