package com.greatonce.oms.bo.trade;

/**
 * @author buer
 * @version 2018-01-06 10:31
 */
public class ManualDispatchDetailBO extends DispatchDetailBO {

  private Long virtualWarehouseId;
  private String virtualWarehouseName;

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
}
