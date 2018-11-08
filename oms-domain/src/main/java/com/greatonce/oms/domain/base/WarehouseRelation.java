package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库关系.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class WarehouseRelation extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 父仓库id.
   */
  private Long parentWarehouseId;
  /**
   * 父仓库名称.
   */
  private String parentWarehouseName;
  /**
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;
  /**
   * 仓库关系id.
   */
  private Long warehouseRelationId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.warehouseRelationId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.warehouseRelationId;
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
   * 父仓库id.
   */
  public void setParentWarehouseId(Long parentWarehouseId) {
    this.parentWarehouseId = parentWarehouseId;
  }

  /**
   * 父仓库id.
   */
  public Long getParentWarehouseId() {
    return this.parentWarehouseId;
  }

  /**
   * 父仓库名称.
   */
  public void setParentWarehouseName(String parentWarehouseName) {
    this.parentWarehouseName = parentWarehouseName == null ? null : parentWarehouseName.trim();
  }

  /**
   * 父仓库名称.
   */
  public String getParentWarehouseName() {
    return this.parentWarehouseName;
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
   * 仓库关系id.
   */
  public void setWarehouseRelationId(Long warehouseRelationId) {
    this.warehouseRelationId = warehouseRelationId;
  }

  /**
   * 仓库关系id.
   */
  public Long getWarehouseRelationId() {
    return this.warehouseRelationId;
  }
}