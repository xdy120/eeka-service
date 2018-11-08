package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderDetailWrapper;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配货上下文
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/20
 */
public class DispatchContext {


  private final long serialNo;

  /**
   * 是否合单
   */
  private boolean merge;
  /**
   * 店铺
   */
  private final Store store;
  /**
   * 主订单
   */
  private SalesOrder mainSalesOrder;
  /**
   * 订单列表
   */
  private List<SalesOrder> salesOrders;
  /**
   * 配货单列表
   */
  private final List<DispatchOrder> dispatchOrders;
  /**
   * 配货策略
   */
  private StockDispatchStrategy dispatchStrategy;
  /**
   * 订单配置
   */
  private SalesOrderSetting salesOrderSetting;
  /**
   * 是否有指定快递
   */
  private final boolean hasDesignatedExpress;
  /**
   * 可配明细集合.
   */
  private final Map<Long, List<DispatchOrderDetailWrapper>> dispatchableDetails;

  private Map<Long, Integer> versionMap;

  public DispatchContext(long serialNumber, Store store, SalesOrder mainSalesOrder) {
    this.serialNo = serialNumber;
    this.store = store;
    this.mainSalesOrder = mainSalesOrder;
    this.dispatchOrders = new ArrayList<>(6);
    this.salesOrders = new ArrayList<>(6);
    this.versionMap = new HashMap<>(6);
    this.versionMap.put(mainSalesOrder.getSalesOrderId(), mainSalesOrder.getVersion());
    this.hasDesignatedExpress = checkDesignatedExpress(mainSalesOrder);
    this.merge = false;
    this.dispatchableDetails = new HashMap<>(6);
  }

  public boolean isMerge() {
    return merge;
  }

  public void setMerge(boolean merge) {
    this.merge = merge;
  }

  public Store getStore() {
    return store;
  }

  public SalesOrder getMainSalesOrder() {
    return mainSalesOrder;
  }

  public List<SalesOrder> getSalesOrders() {
    return salesOrders;
  }

  public void setSalesOrders(List<SalesOrder> salesOrders) {
    this.salesOrders = salesOrders;
  }

  public List<DispatchOrder> getDispatchOrders() {
    return dispatchOrders;
  }

  public StockDispatchStrategy getDispatchStrategy() {
    return dispatchStrategy;
  }

  public void setDispatchStrategy(StockDispatchStrategy dispatchStrategy) {
    this.dispatchStrategy = dispatchStrategy;
  }

  public SalesOrderSetting getSalesOrderSetting() {
    return salesOrderSetting;
  }

  public void setSalesOrderSetting(SalesOrderSetting salesOrderSetting) {
    this.salesOrderSetting = salesOrderSetting;
  }

  public Map<Long, Integer> getVersionMap() {
    return versionMap;
  }

  public void setVersionMap(Map<Long, Integer> versionMap) {
    this.versionMap = versionMap;
  }

  public long getSerialNo() {
    return serialNo;
  }

  public void setMainSalesOrder(SalesOrder mainSalesOrder) {
    this.mainSalesOrder = mainSalesOrder;
  }

  public boolean isHasDesignatedExpress() {
    return hasDesignatedExpress;
  }

  private boolean checkDesignatedExpress(SalesOrder mainSalesOrder) {
    return mainSalesOrder.getSuggestExpressId() != null;
  }

  public Map<Long, List<DispatchOrderDetailWrapper>> getDispatchableDetails() {
    return dispatchableDetails;
  }
}
