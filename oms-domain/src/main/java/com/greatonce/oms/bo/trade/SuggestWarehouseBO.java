package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author buer
 * @version 2017-12-27 19:45
 */
public class SuggestWarehouseBO extends VersionBO {

  private Long virtualWarehouseId;

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

}
