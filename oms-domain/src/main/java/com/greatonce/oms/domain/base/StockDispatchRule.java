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
public class StockDispatchRule implements Serializable {

  private List<StockDispatchWarehouse> warehouses;

  public List<StockDispatchWarehouse> getWarehouses() {
    return warehouses;
  }

  public void setWarehouses(List<StockDispatchWarehouse> warehouses) {
    this.warehouses = warehouses;
  }
}
