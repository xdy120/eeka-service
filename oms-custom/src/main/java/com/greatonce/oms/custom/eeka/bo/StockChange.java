package com.greatonce.oms.custom.eeka.bo;

import java.io.Serializable;

public class StockChange implements Serializable {

  private String warehouseCode;
  private String quantity;
  private String skuCode;

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }
}
