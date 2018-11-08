package com.greatonce.oms.query.product;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.WmsType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品仓库映射.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ProductWmsMappingQuery extends Query {
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
   * 商品id.
   */
  private Long productId;
  /**
   * 商品仓库映射id.
   */
  private Long productWmsMappingId;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 仓库商品id.
   */
  private String wmsProductId;
  /**
   * 仓库规格id.
   */
  private String wmsSkuId;
  /**
   * 物流类型.
   */
  private WmsType wmsType;


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
   * 商品仓库映射id.
   * @param productWmsMappingId 商品仓库映射id
   */
  public void setProductWmsMappingId(Long productWmsMappingId) {
    this.productWmsMappingId = productWmsMappingId;
  }

  /**
   * 商品仓库映射id.
   * @return 商品仓库映射id
   */
  public Long getProductWmsMappingId() {
      return this.productWmsMappingId;
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
   * 仓库商品id.
   * @param wmsProductId 仓库商品id
   */
  public void setWmsProductId(String wmsProductId) {
    this.wmsProductId = wmsProductId == null ? null : wmsProductId.trim();
  }

  /**
   * 仓库商品id.
   * @return 仓库商品id
   */
  public String getWmsProductId() {
      return this.wmsProductId;
  }

  /**
   * 仓库规格id.
   * @param wmsSkuId 仓库规格id
   */
  public void setWmsSkuId(String wmsSkuId) {
    this.wmsSkuId = wmsSkuId == null ? null : wmsSkuId.trim();
  }

  /**
   * 仓库规格id.
   * @return 仓库规格id
   */
  public String getWmsSkuId() {
      return this.wmsSkuId;
  }

  /**
   * 物流类型.
   * @param wmsType 物流类型
   */
  public void setWmsType(WmsType wmsType) {
    this.wmsType = wmsType;
  }

  /**
   * 物流类型.
   * @return 物流类型
   */
  public WmsType getWmsType() {
      return this.wmsType;
  }
}