package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.WarehouseType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockQuery extends Query {
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
   * .
   */
  private List<String> productCodes;
  /**
   * .
   */
  private String productName;
  /**
   * .
   */
  private Boolean hasStock;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 动销率.
   */
  private Integer ratio;
  /**
   * 备注.
   */
  private String remark;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * .
   */
  private List<Long> skuIds;
  /**
   * 库存id.
   */
  private Long stockId;
  /**
   * 在途数量.
   */
  private Integer transitQuantity;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * .
   */
  private List<Long> virtualWarehouseIds;
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
   * .
   */
  private WarehouseType warehouseType;


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
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
  }

  /**
   * .
   * @param productName 
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * .
   * @return 
   */
  public String getProductName() {
      return this.productName;
  }

  /**
   * .
   * @param hasStock 
   */
  public void setHasStock(Boolean hasStock) {
    this.hasStock = hasStock;
  }

  /**
   * .
   * @return 
   */
  public Boolean isHasStock() {
      return this.hasStock;
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
   * 动销率.
   * @param ratio 动销率
   */
  public void setRatio(Integer ratio) {
    this.ratio = ratio;
  }

  /**
   * 动销率.
   * @return 动销率
   */
  public Integer getRatio() {
      return this.ratio;
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
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
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
   * .
   * @param skuIds 
   */
  public void setSkuIds(List<Long> skuIds) {
    this.skuIds = skuIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getSkuIds() {
      return this.skuIds;
  }

  /**
   * 库存id.
   * @param stockId 库存id
   */
  public void setStockId(Long stockId) {
    this.stockId = stockId;
  }

  /**
   * 库存id.
   * @return 库存id
   */
  public Long getStockId() {
      return this.stockId;
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
   * .
   * @param virtualWarehouseIds 
   */
  public void setVirtualWarehouseIds(List<Long> virtualWarehouseIds) {
    this.virtualWarehouseIds = virtualWarehouseIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getVirtualWarehouseIds() {
      return this.virtualWarehouseIds;
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

  /**
   * .
   * @param warehouseType 
   */
  public void setWarehouseType(WarehouseType warehouseType) {
    this.warehouseType = warehouseType;
  }

  /**
   * .
   * @return 
   */
  public WarehouseType getWarehouseType() {
      return this.warehouseType;
  }
}