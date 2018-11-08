package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品销售单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipSalesOrderDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 平台支付时间.
   */
  private LocalDateTime mallPaidTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 专场号.
   */
  private String salesNo;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 交易号.
   */
  private String tradeId;
  /**
   * 唯品商品编码.
   */
  private String vipBarcode;
  /**
   * 唯品销售单明细id.
   */
  private Long vipSalesOrderDetailId;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;
  /**
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.vipSalesOrderDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipSalesOrderDetailId;
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
   * 平台支付时间.
   */
  public void setMallPaidTime(LocalDateTime mallPaidTime) {
    this.mallPaidTime = mallPaidTime;
  }

  /**
   * 平台支付时间.
   */
  public LocalDateTime getMallPaidTime() {
    return this.mallPaidTime;
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
   * 专场号.
   */
  public void setSalesNo(String salesNo) {
    this.salesNo = salesNo == null ? null : salesNo.trim();
  }

  /**
   * 专场号.
   */
  public String getSalesNo() {
    return this.salesNo;
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
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }

  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 交易号.
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   */
  public String getTradeId() {
    return this.tradeId;
  }

  /**
   * 唯品商品编码.
   */
  public void setVipBarcode(String vipBarcode) {
    this.vipBarcode = vipBarcode == null ? null : vipBarcode.trim();
  }

  /**
   * 唯品商品编码.
   */
  public String getVipBarcode() {
    return this.vipBarcode;
  }

  /**
   * 唯品销售单明细id.
   */
  public void setVipSalesOrderDetailId(Long vipSalesOrderDetailId) {
    this.vipSalesOrderDetailId = vipSalesOrderDetailId;
  }

  /**
   * 唯品销售单明细id.
   */
  public Long getVipSalesOrderDetailId() {
    return this.vipSalesOrderDetailId;
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
}