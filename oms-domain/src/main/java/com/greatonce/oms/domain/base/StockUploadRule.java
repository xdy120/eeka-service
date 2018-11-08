package com.greatonce.oms.domain.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cca
 * Date: 2018-03-15
 * Time: 22:06
 */
public class StockUploadRule implements Serializable {

  private String brandNames;
  private List<StockUploadWarehouse> warehouses;

  public String getBrandNames() {
    return brandNames;
  }

  public void setBrandNames(String brandNames) {
    this.brandNames = brandNames;
  }

  public List<StockUploadWarehouse> getWarehouses() {
    return warehouses;
  }

  public void setWarehouses(List<StockUploadWarehouse> warehouses) {
    this.warehouses = warehouses;
  }
}
