package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.enums.PrepackageType;
import com.greatonce.oms.domain.enums.WarehouseDispatchType;
import java.io.Serializable;
import java.util.List;

/**
 * 库存配货策略仓库.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/22
 */
public class StockDispatchWarehouse implements Serializable, Comparable<StockDispatchWarehouse> {

  private Long warehouseId;
  private String warehouseName;
  private Long virtualWarehouseId;
  private String virtualWarehouseName;
  private WarehouseDispatchType dispatchType;
  private Integer priorityNo;
  private Long expressStrategyId;
  private String expressStrategyName;
  private PrepackageType prepackageType;
  private ExpressStrategy expressStrategy;

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

  public WarehouseDispatchType getDispatchType() {
    return dispatchType;
  }

  public void setDispatchType(WarehouseDispatchType dispatchType) {
    this.dispatchType = dispatchType;
  }

  public Integer getPriorityNo() {
    return priorityNo;
  }

  public void setPriorityNo(Integer priorityNo) {
    this.priorityNo = priorityNo;
  }

  public Long getExpressStrategyId() {
    return expressStrategyId;
  }

  public void setExpressStrategyId(Long expressStrategyId) {
    this.expressStrategyId = expressStrategyId;
  }

  public String getExpressStrategyName() {
    return expressStrategyName;
  }

  public void setExpressStrategyName(String expressStrategyName) {
    this.expressStrategyName = expressStrategyName;
  }

  @Override
  public int compareTo(StockDispatchWarehouse o) {
    return this.priorityNo.compareTo(o.priorityNo);
  }

  public ExpressStrategy getExpressStrategy() {
    return expressStrategy;
  }

  public void setExpressStrategy(ExpressStrategy expressStrategy) {
    this.expressStrategy = expressStrategy;
  }

  public PrepackageType getPrepackageType() {
    return prepackageType;
  }

  public void setPrepackageType(PrepackageType prepackageType) {
    this.prepackageType = prepackageType;
  }
}
