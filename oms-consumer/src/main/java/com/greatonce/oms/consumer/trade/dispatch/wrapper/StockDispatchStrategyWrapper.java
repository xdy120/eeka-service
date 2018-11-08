package com.greatonce.oms.consumer.trade.dispatch.wrapper;

import com.greatonce.oms.domain.base.StockDispatchRule;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/29
 */
public class StockDispatchStrategyWrapper extends StockDispatchStrategy {

  /**
   * 库存配货策略.
   */
  private final StockDispatchStrategy dispatchStrategy;
  /**
   * 实体仓Id为Key，实体仓下的虚拟策略仓集合为值的键值表.
   */
  private final Map<Long, List<StockDispatchWarehouse>> warehouseMap;
  /**
   * 某实体仓Id为Key，实体仓下虚拟策略仓Id的集合为值的键值表.
   */
  private final Map<Long, Set<Long>> warehouseIdMap;
  /**
   * 虚拟仓的map.
   */
  private final Map<Long, StockDispatchWarehouse> stockDispatchWarehouseMap;

  public StockDispatchStrategyWrapper(StockDispatchStrategy dispatchStrategy) {
    this.dispatchStrategy = dispatchStrategy;

    List<StockDispatchWarehouse> warehouseList = dispatchStrategy.getRule().getWarehouses();
    this.warehouseMap = new LinkedHashMap<>(warehouseList.size());
    this.warehouseIdMap = new LinkedHashMap<>(warehouseList.size());
    this.stockDispatchWarehouseMap = new HashMap<>(warehouseList.size());

    Collections.sort(warehouseList);
    for (StockDispatchWarehouse warehouse : warehouseList) {
      if (!warehouseMap.containsKey(warehouse.getWarehouseId())) {
        List<StockDispatchWarehouse> warehouses = new ArrayList<>(1);
        Set<Long> warehouseIds = new HashSet<>(1);
        warehouses.add(warehouse);
        warehouseIds.add(warehouse.getVirtualWarehouseId());
        warehouseMap.put(warehouse.getWarehouseId(), warehouses);
        warehouseIdMap.put(warehouse.getWarehouseId(), warehouseIds);
      } else {
        warehouseMap.get(warehouse.getWarehouseId()).add(warehouse);
        warehouseIdMap.get(warehouse.getWarehouseId()).add(warehouse.getVirtualWarehouseId());
      }
      if (!stockDispatchWarehouseMap.containsKey(warehouse.getVirtualWarehouseId())) {
        stockDispatchWarehouseMap.put(warehouse.getVirtualWarehouseId(), warehouse);
      }
    }
  }

  public Map<Long, StockDispatchWarehouse> getStockDispatchWarehouseMap() {
    return stockDispatchWarehouseMap;
  }

  public Map<Long, List<StockDispatchWarehouse>> getWarehouseMap() {
    return warehouseMap;
  }

  public Map<Long, Set<Long>> getWarehouseIdMap() {
    return warehouseIdMap;
  }

  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    dispatchStrategy.setCreatedTime(createdTime);
  }

  @Override
  public LocalDateTime getCreatedTime() {
    return dispatchStrategy.getCreatedTime();
  }

  @Override
  public void setDefaultCodExpressId(Long defaultCodExpressId) {
    dispatchStrategy.setDefaultCodExpressId(defaultCodExpressId);
  }

  @Override
  public Long getDefaultCodExpressId() {
    return dispatchStrategy.getDefaultCodExpressId();
  }

  @Override
  public void setDefaultCodExpressName(String defaultCodExpressName) {
    dispatchStrategy.setDefaultCodExpressName(defaultCodExpressName);
  }

  @Override
  public String getDefaultCodExpressName() {
    return dispatchStrategy.getDefaultCodExpressName();
  }

  @Override
  public void setDefaultExpressId(Long defaultExpressId) {
    dispatchStrategy.setDefaultExpressId(defaultExpressId);
  }

  @Override
  public Long getDefaultExpressId() {
    return dispatchStrategy.getDefaultExpressId();
  }

  @Override
  public void setDefaultExpressName(String defaultExpressName) {
    dispatchStrategy.setDefaultExpressName(defaultExpressName);
  }

  @Override
  public String getDefaultExpressName() {
    return dispatchStrategy.getDefaultExpressName();
  }

  @Override
  public void setEnable(Boolean enable) {
    dispatchStrategy.setEnable(enable);
  }

  @Override
  public Boolean isEnable() {
    return dispatchStrategy.isEnable();
  }

  @Override
  public void setMallWarehouse(String mallWarehouse) {
    dispatchStrategy.setMallWarehouse(mallWarehouse);
  }

  @Override
  public String getMallWarehouse() {
    return dispatchStrategy.getMallWarehouse();
  }

  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    dispatchStrategy.setModifiedTime(modifiedTime);
  }

  @Override
  public LocalDateTime getModifiedTime() {
    return dispatchStrategy.getModifiedTime();
  }

  @Override
  public void setSettingJson(String settingJson) {
    dispatchStrategy.setSettingJson(settingJson);
  }

  @Override
  public String getSettingJson() {
    return dispatchStrategy.getSettingJson();
  }

  @Override
  public void setStockDispatchStrategyId(Long stockDispatchStrategyId) {
    dispatchStrategy.setStockDispatchStrategyId(stockDispatchStrategyId);
  }

  @Override
  public Long getStockDispatchStrategyId() {
    return dispatchStrategy.getStockDispatchStrategyId();
  }

  @Override
  public void setStockDispatchStrategyName(String stockDispatchStrategyName) {
    dispatchStrategy.setStockDispatchStrategyName(stockDispatchStrategyName);
  }

  @Override
  public String getStockDispatchStrategyName() {
    return dispatchStrategy.getStockDispatchStrategyName();
  }

  @Override
  public void setRule(StockDispatchRule rule) {
    dispatchStrategy.setRule(rule);
  }

  @Override
  public StockDispatchRule getRule() {
    return dispatchStrategy.getRule();
  }

  @Override
  public Object clone() {
    return dispatchStrategy.clone();
  }
}
