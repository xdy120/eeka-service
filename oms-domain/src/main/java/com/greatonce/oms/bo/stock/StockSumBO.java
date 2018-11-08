package com.greatonce.oms.bo.stock;

/**
 * @author buer
 * @version 2018-01-05 15:06
 */
public class StockSumBO {

  /**
   * 规格ID
   */
  private Long skuId;
  /**
   * 仓库ID
   */
  private Long warehouseId;
  /**
   * 仓库名称
   */
  private String warehouseName;
  /**
   * 虚拟仓ID
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称
   */
  private String virtualWarehouseName;
  /**
   * 库存数
   */
  private Integer quantity;
  /**
   * 可销数，库存-占用
   */
  private Integer salesQuantity;
  /**
   * 可用数，库存-占用+未配货订单占用+换货单占用
   */
  private Integer usableQuantity;

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public String getVirtualWarehouseName() {
    return virtualWarehouseName;
  }

  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getSalesQuantity() {
    return salesQuantity;
  }

  public void setSalesQuantity(Integer salesQuantity) {
    this.salesQuantity = salesQuantity;
  }

  public Integer getUsableQuantity() {
    return usableQuantity;
  }

  public void setUsableQuantity(Integer usableQuantity) {
    this.usableQuantity = usableQuantity;
  }
}
