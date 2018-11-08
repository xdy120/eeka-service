package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库区域.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class WarehouseRegion extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 级别.
   */
  private Integer level;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 区域id.
   */
  private Long regionId;
  /**
   * 区域名称.
   */
  private String regionName;
  /**
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;
  /**
   * 仓库区域id.
   */
  private Long warehouseRegionId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.warehouseRegionId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.warehouseRegionId;
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
   * 级别.
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * 级别.
   */
  public Integer getLevel() {
    return this.level;
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
   * 区域id.
   */
  public void setRegionId(Long regionId) {
    this.regionId = regionId;
  }

  /**
   * 区域id.
   */
  public Long getRegionId() {
    return this.regionId;
  }

  /**
   * 区域名称.
   */
  public void setRegionName(String regionName) {
    this.regionName = regionName == null ? null : regionName.trim();
  }

  /**
   * 区域名称.
   */
  public String getRegionName() {
    return this.regionName;
  }

  /**
   * 仓库id.
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 仓库id.
   */
  public Long getWarehouseId() {
    return this.warehouseId;
  }

  /**
   * 仓库名称.
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 仓库名称.
   */
  public String getWarehouseName() {
    return this.warehouseName;
  }

  /**
   * 仓库区域id.
   */
  public void setWarehouseRegionId(Long warehouseRegionId) {
    this.warehouseRegionId = warehouseRegionId;
  }

  /**
   * 仓库区域id.
   */
  public Long getWarehouseRegionId() {
    return this.warehouseRegionId;
  }
}