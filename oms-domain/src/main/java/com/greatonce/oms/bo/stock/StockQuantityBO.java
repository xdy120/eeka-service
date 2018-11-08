package com.greatonce.oms.bo.stock;


import java.io.Serializable;


public class StockQuantityBO implements Serializable {

  private Long virtualWarehouseId;
  private String skuId;
  private Integer quantity;
  private Integer lockedQuantity;

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public String getSkuId() {
    return skuId;
  }

  public void setSkuId(String skuId) {
    this.skuId = skuId;
  }

  public Integer getQuantity() {
    return quantity == null ? 0 : Integer.parseInt(String.valueOf(quantity));
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getLockedQuantity() {
    return lockedQuantity == null ? 0 : Integer.parseInt(String.valueOf(lockedQuantity));
  }

  public void setLockedQuantity(Integer lockedQuantity) {
    this.lockedQuantity = lockedQuantity;
  }

  @Override
  public String toString() {
    return "StockQuantityBO{" + "virtualWarehouseId=" + virtualWarehouseId
        + ", skuId='" + skuId + '\''
        + ", quantity=" + quantity
        + ", lockedQuantity=" + lockedQuantity
        + '}';
  }
}
