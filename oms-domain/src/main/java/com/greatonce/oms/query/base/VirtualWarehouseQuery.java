package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.VirtualWarehouseType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 虚拟仓库.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VirtualWarehouseQuery extends Query {
  /**
   * .
   */
  private List<VirtualWarehouseType> virtualWarehouseTypes;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 虚拟仓编码.
   */
  private String virtualWarehouseCode;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;
  /**
   * 虚拟仓类型.
   */
  private VirtualWarehouseType virtualWarehouseType;
  /**
   * 物理仓id.
   */
  private Long warehouseId;
  /**
   * .
   */
  private List<Long> warehouseIds;
  /**
   * 仓库名称.
   */
  private String warehouseName;


  /**
   * .
   * @param virtualWarehouseTypes 
   */
  public void setVirtualWarehouseTypes(List<VirtualWarehouseType> virtualWarehouseTypes) {
    this.virtualWarehouseTypes = virtualWarehouseTypes;
  }

  /**
   * .
   * @return 
   */
  public List<VirtualWarehouseType> getVirtualWarehouseTypes() {
      return this.virtualWarehouseTypes;
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
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
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
   * 虚拟仓编码.
   * @param virtualWarehouseCode 虚拟仓编码
   */
  public void setVirtualWarehouseCode(String virtualWarehouseCode) {
    this.virtualWarehouseCode = virtualWarehouseCode == null ? null : virtualWarehouseCode.trim();
  }

  /**
   * 虚拟仓编码.
   * @return 虚拟仓编码
   */
  public String getVirtualWarehouseCode() {
      return this.virtualWarehouseCode;
  }

  /**
   * 虚拟仓id.
   * @param virtualWarehouseId 虚拟仓id
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   * @return 虚拟仓id
   */
  public Long getVirtualWarehouseId() {
      return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   * @param virtualWarehouseName 虚拟仓名称
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   * @return 虚拟仓名称
   */
  public String getVirtualWarehouseName() {
      return this.virtualWarehouseName;
  }

  /**
   * 虚拟仓类型.
   * @param virtualWarehouseType 虚拟仓类型
   */
  public void setVirtualWarehouseType(VirtualWarehouseType virtualWarehouseType) {
    this.virtualWarehouseType = virtualWarehouseType;
  }

  /**
   * 虚拟仓类型.
   * @return 虚拟仓类型
   */
  public VirtualWarehouseType getVirtualWarehouseType() {
      return this.virtualWarehouseType;
  }

  /**
   * 物理仓id.
   * @param warehouseId 物理仓id
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 物理仓id.
   * @return 物理仓id
   */
  public Long getWarehouseId() {
      return this.warehouseId;
  }

  /**
   * .
   * @param warehouseIds 
   */
  public void setWarehouseIds(List<Long> warehouseIds) {
    this.warehouseIds = warehouseIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getWarehouseIds() {
      return this.warehouseIds;
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
}