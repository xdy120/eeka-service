package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
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
public class VirtualWarehouse extends EnableDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 仓库名称.
   */
  private String warehouseName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.virtualWarehouseId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.virtualWarehouseId;
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
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
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
   * 虚拟仓编码.
   */
  public void setVirtualWarehouseCode(String virtualWarehouseCode) {
    this.virtualWarehouseCode = virtualWarehouseCode == null ? null : virtualWarehouseCode.trim();
  }

  /**
   * 虚拟仓编码.
   */
  public String getVirtualWarehouseCode() {
    return this.virtualWarehouseCode;
  }

  /**
   * 虚拟仓id.
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   */
  public Long getVirtualWarehouseId() {
    return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   */
  public String getVirtualWarehouseName() {
    return this.virtualWarehouseName;
  }

  /**
   * 虚拟仓类型.
   */
  public void setVirtualWarehouseType(VirtualWarehouseType virtualWarehouseType) {
    this.virtualWarehouseType = virtualWarehouseType;
  }

  /**
   * 虚拟仓类型.
   */
  public VirtualWarehouseType getVirtualWarehouseType() {
    return this.virtualWarehouseType;
  }

  /**
   * 物理仓id.
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 物理仓id.
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
}