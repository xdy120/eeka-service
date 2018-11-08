package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class WarehouseRelationQuery extends Query {
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
   * 父仓库id.
   * @param parentWarehouseId 父仓库id
   */
  public void setParentWarehouseId(Long parentWarehouseId) {
    this.parentWarehouseId = parentWarehouseId;
  }

  /**
   * 父仓库id.
   * @return 父仓库id
   */
  public Long getParentWarehouseId() {
      return this.parentWarehouseId;
  }

  /**
   * 父仓库名称.
   * @param parentWarehouseName 父仓库名称
   */
  public void setParentWarehouseName(String parentWarehouseName) {
    this.parentWarehouseName = parentWarehouseName == null ? null : parentWarehouseName.trim();
  }

  /**
   * 父仓库名称.
   * @return 父仓库名称
   */
  public String getParentWarehouseName() {
      return this.parentWarehouseName;
  }

  /**
   * 仓库id.
   * @param warehouseId 仓库id
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 仓库id.
   * @return 仓库id
   */
  public Long getWarehouseId() {
      return this.warehouseId;
  }

  /**
   * 仓库名称.
   * @param warehouseName 仓库名称
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 仓库名称.
   * @return 仓库名称
   */
  public String getWarehouseName() {
      return this.warehouseName;
  }

  /**
   * 仓库关系id.
   * @param warehouseRelationId 仓库关系id
   */
  public void setWarehouseRelationId(Long warehouseRelationId) {
    this.warehouseRelationId = warehouseRelationId;
  }

  /**
   * 仓库关系id.
   * @return 仓库关系id
   */
  public Long getWarehouseRelationId() {
      return this.warehouseRelationId;
  }
}