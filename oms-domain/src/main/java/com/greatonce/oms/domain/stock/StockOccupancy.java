package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存占用.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockOccupancy extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 占用明细id.
   */
  private Long detailId;
  /**
   * 占用主表id.
   */
  private Long mainId;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 排序时间.
   */
  private LocalDateTime sortTime;
  /**
   * 占用状态.
   */
  private StockOccupancyStatus status;
  /**
   * 占用id.
   */
  private Long stockOccupancyId;
  /**
   * 占用类型.
   */
  private StockOccupancyType stockOccupancyType;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;
  /**
   * 实体仓id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.stockOccupancyId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockOccupancyId;
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
   * 占用明细id.
   */
  public void setDetailId(Long detailId) {
    this.detailId = detailId;
  }

  /**
   * 占用明细id.
   */
  public Long getDetailId() {
    return this.detailId;
  }

  /**
   * 占用主表id.
   */
  public void setMainId(Long mainId) {
    this.mainId = mainId;
  }

  /**
   * 占用主表id.
   */
  public Long getMainId() {
    return this.mainId;
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
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
  }

  /**
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
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
   * 排序时间.
   */
  public void setSortTime(LocalDateTime sortTime) {
    this.sortTime = sortTime;
  }

  /**
   * 排序时间.
   */
  public LocalDateTime getSortTime() {
    return this.sortTime;
  }

  /**
   * 占用状态.
   */
  public void setStatus(StockOccupancyStatus status) {
    this.status = status;
  }

  /**
   * 占用状态.
   */
  public StockOccupancyStatus getStatus() {
    return this.status;
  }

  /**
   * 占用id.
   */
  public void setStockOccupancyId(Long stockOccupancyId) {
    this.stockOccupancyId = stockOccupancyId;
  }

  /**
   * 占用id.
   */
  public Long getStockOccupancyId() {
    return this.stockOccupancyId;
  }

  /**
   * 占用类型.
   */
  public void setStockOccupancyType(StockOccupancyType stockOccupancyType) {
    this.stockOccupancyType = stockOccupancyType;
  }

  /**
   * 占用类型.
   */
  public StockOccupancyType getStockOccupancyType() {
    return this.stockOccupancyType;
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
   * 实体仓id.
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 实体仓id.
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