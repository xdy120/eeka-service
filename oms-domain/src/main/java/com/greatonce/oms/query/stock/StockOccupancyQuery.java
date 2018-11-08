package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
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
public class StockOccupancyQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 占用明细id.
   */
  private Long detailId;
  /**
   * 占用主表id.
   */
  private Long mainId;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 排序时间开始.
   */
  private LocalDateTime sortTimeBegin;
  /**
   * 排序时间结束.
   */
  private LocalDateTime sortTimeEnd;
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
   * 占用明细id.
   * @param detailId 占用明细id
   */
  public void setDetailId(Long detailId) {
    this.detailId = detailId;
  }

  /**
   * 占用明细id.
   * @return 占用明细id
   */
  public Long getDetailId() {
      return this.detailId;
  }

  /**
   * 占用主表id.
   * @param mainId 占用主表id
   */
  public void setMainId(Long mainId) {
    this.mainId = mainId;
  }

  /**
   * 占用主表id.
   * @return 占用主表id
   */
  public Long getMainId() {
      return this.mainId;
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
   * 数量.
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   * @return 数量
   */
  public Integer getQuantity() {
      return this.quantity;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * 商品规格编码.
   * @param skuCode 商品规格编码
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   * @return 商品规格编码
   */
  public String getSkuCode() {
      return this.skuCode;
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
   * 排序时间开始.
   * @param sortTimeBegin 开始.
   */
  public void setSortTimeBegin(LocalDateTime sortTimeBegin) {
    this.sortTimeBegin = sortTimeBegin;
  }

  /**
   * 排序时间开始.
   * @return 排序时间开始
   */
  public LocalDateTime getSortTimeBegin() {
    return this.sortTimeBegin;
  }

  /**
   * 排序时间结束.
   * @param sortTimeEnd 结束
   */
  public void setSortTimeEnd(LocalDateTime sortTimeEnd) {
    this.sortTimeEnd = sortTimeEnd;
  }

  /**
   * 排序时间结束.
   * @return 排序时间结束
   */
  public LocalDateTime getSortTimeEnd() {
    return this.sortTimeEnd;
  }

  /**
   * 占用状态.
   * @param status 占用状态
   */
  public void setStatus(StockOccupancyStatus status) {
    this.status = status;
  }

  /**
   * 占用状态.
   * @return 占用状态
   */
  public StockOccupancyStatus getStatus() {
      return this.status;
  }

  /**
   * 占用id.
   * @param stockOccupancyId 占用id
   */
  public void setStockOccupancyId(Long stockOccupancyId) {
    this.stockOccupancyId = stockOccupancyId;
  }

  /**
   * 占用id.
   * @return 占用id
   */
  public Long getStockOccupancyId() {
      return this.stockOccupancyId;
  }

  /**
   * 占用类型.
   * @param stockOccupancyType 占用类型
   */
  public void setStockOccupancyType(StockOccupancyType stockOccupancyType) {
    this.stockOccupancyType = stockOccupancyType;
  }

  /**
   * 占用类型.
   * @return 占用类型
   */
  public StockOccupancyType getStockOccupancyType() {
      return this.stockOccupancyType;
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
   * 实体仓id.
   * @param warehouseId 实体仓id
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 实体仓id.
   * @return 实体仓id
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
}