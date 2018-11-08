package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.BaseDO;
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
public class ProductWmsMapping extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.productWmsMappingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.productWmsMappingId;
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
   * 商品仓库映射id.
   */
  public void setProductWmsMappingId(Long productWmsMappingId) {
    this.productWmsMappingId = productWmsMappingId;
  }

  /**
   * 商品仓库映射id.
   */
  public Long getProductWmsMappingId() {
    return this.productWmsMappingId;
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
   * 仓库商品id.
   */
  public void setWmsProductId(String wmsProductId) {
    this.wmsProductId = wmsProductId == null ? null : wmsProductId.trim();
  }

  /**
   * 仓库商品id.
   */
  public String getWmsProductId() {
    return this.wmsProductId;
  }

  /**
   * 仓库规格id.
   */
  public void setWmsSkuId(String wmsSkuId) {
    this.wmsSkuId = wmsSkuId == null ? null : wmsSkuId.trim();
  }

  /**
   * 仓库规格id.
   */
  public String getWmsSkuId() {
    return this.wmsSkuId;
  }

  /**
   * 物流类型.
   */
  public void setWmsType(WmsType wmsType) {
    this.wmsType = wmsType;
  }

  /**
   * 物流类型.
   */
  public WmsType getWmsType() {
    return this.wmsType;
  }
}