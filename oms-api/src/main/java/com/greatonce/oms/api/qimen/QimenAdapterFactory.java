package com.greatonce.oms.api.qimen;

import com.greatonce.oms.bridge.wms.qimen.QimenStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenCombineitemSynchronizeStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenDeliveryConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenInventoryReportStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenItemsSynchronizeStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenReturnOrderConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenStockChangeReportStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenStockInConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenStockOutConfirmStrategy;
import com.greatonce.oms.domain.enums.WmsType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 奇门接口适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
@Component
public class QimenAdapterFactory {

  private final Map<WmsType, QimenItemsSynchronizeStrategy> itemsSynchronizeAdapterMap;
  private final Map<WmsType, QimenDeliveryConfirmStrategy> deliveryConfirmAdapterMap;
  private final Map<WmsType, QimenInventoryReportStrategy> inventoryReportAdapterMap;
  private final Map<WmsType, QimenReturnOrderConfirmStrategy> returnOrderConfirmAdapterMap;
  private final Map<WmsType, QimenStockInConfirmStrategy> stockInConfirmAdapterMap;
  private final Map<WmsType, QimenStockOutConfirmStrategy> stockOutConfirmAdapterMap;
  private final Map<WmsType, QimenStockChangeReportStrategy> stockChangeReportAdapterMap;
  private final Map<WmsType, QimenCombineitemSynchronizeStrategy> combineitemSynchronizeAdapterMap;


  @Autowired
  public QimenAdapterFactory(
      List<QimenItemsSynchronizeStrategy> itemsSynchronizeAdapters,
      List<QimenDeliveryConfirmStrategy> deliveryConfirmAdapters,
      List<QimenInventoryReportStrategy> inventoryReportAdapters,
      List<QimenReturnOrderConfirmStrategy> returnOrderConfirmAdapters,
      List<QimenStockInConfirmStrategy> stockInConfirmAdapters,
      List<QimenStockOutConfirmStrategy> stockOutConfirmAdapters,
      List<QimenStockChangeReportStrategy> stockChangeReportAdapters,
      List<QimenCombineitemSynchronizeStrategy> combineitemSynchronizeAdapters) {

    this.itemsSynchronizeAdapterMap = new HashMap<>(itemsSynchronizeAdapters.size());
    initMap(itemsSynchronizeAdapters, this.itemsSynchronizeAdapterMap);
    this.deliveryConfirmAdapterMap = new HashMap<>(deliveryConfirmAdapters.size());
    initMap(deliveryConfirmAdapters, this.deliveryConfirmAdapterMap);
    this.inventoryReportAdapterMap = new HashMap<>(inventoryReportAdapters.size());
    initMap(inventoryReportAdapters, this.inventoryReportAdapterMap);
    this.returnOrderConfirmAdapterMap = new HashMap<>(returnOrderConfirmAdapters.size());
    initMap(returnOrderConfirmAdapters, this.returnOrderConfirmAdapterMap);
    this.stockInConfirmAdapterMap = new HashMap<>(stockInConfirmAdapters.size());
    initMap(stockInConfirmAdapters, this.stockInConfirmAdapterMap);
    this.stockOutConfirmAdapterMap = new HashMap<>(stockOutConfirmAdapters.size());
    initMap(stockOutConfirmAdapters, this.stockOutConfirmAdapterMap);
    this.stockChangeReportAdapterMap = new HashMap<>(stockChangeReportAdapters.size());
    initMap(stockChangeReportAdapters, this.stockChangeReportAdapterMap);
    this.combineitemSynchronizeAdapterMap = new HashMap<>(combineitemSynchronizeAdapters.size());
    initMap(combineitemSynchronizeAdapters, this.combineitemSynchronizeAdapterMap);
  }

  private <T extends QimenStrategy> void initMap(List<T> list, Map<WmsType, T> map) {
    for (T qimenAdapter : list) {
      for (WmsType wmsType : qimenAdapter.supports()) {
        map.put(wmsType, qimenAdapter);
      }
    }
  }


  /**
   * 获取商品接口实现.
   *
   * @param wmsType WMS编码
   */
  public QimenItemsSynchronizeStrategy getItemsSynchronizeAdapter(WmsType wmsType) {
    QimenItemsSynchronizeStrategy adapter = this.itemsSynchronizeAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.itemsSynchronizeAdapterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取发货确认适配器.
   *
   * @param wmsType WMS编码
   */
  public QimenDeliveryConfirmStrategy getDeliveryConfirmAdapter(WmsType wmsType) {
    QimenDeliveryConfirmStrategy adapter = this.deliveryConfirmAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.deliveryConfirmAdapterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取盘点适配器.
   *
   * @param wmsType WMS编码
   */
  public QimenInventoryReportStrategy getInventoryReportAdapter(WmsType wmsType) {
    QimenInventoryReportStrategy adapter = this.inventoryReportAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.inventoryReportAdapterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取商品接口实现.
   *
   * @param wmsType WMS编码
   */
  public QimenReturnOrderConfirmStrategy getReturnOrderConfirmAdapter(WmsType wmsType) {
    QimenReturnOrderConfirmStrategy adapter = this.returnOrderConfirmAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.returnOrderConfirmAdapterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取入库确认适配器.
   *
   * @param wmsType WMS编码
   */
  public QimenStockInConfirmStrategy getStockInConfirmAdapter(WmsType wmsType) {
    QimenStockInConfirmStrategy adapter = this.stockInConfirmAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.stockInConfirmAdapterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取出库确认适配器.
   *
   * @param wmsType WMS编码
   */
  public QimenStockOutConfirmStrategy getStockOutConfirmAdapter(WmsType wmsType) {
    QimenStockOutConfirmStrategy adapter = this.stockOutConfirmAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.stockOutConfirmAdapterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取库存异动适配器.
   *
   * @param wmsType WMS编码
   */
  public QimenStockChangeReportStrategy getStockChangeReportAdapter(WmsType wmsType) {
    QimenStockChangeReportStrategy adapter = this.stockChangeReportAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.stockChangeReportAdapterMap.get(WmsType.QIMEN);
  }


  /**
   * 获取库存异动适配器.
   *
   * @param wmsType WMS编码
   */
  public QimenCombineitemSynchronizeStrategy getCombineitemSynchronizeAdapter(WmsType wmsType) {
    QimenCombineitemSynchronizeStrategy adapter = this.combineitemSynchronizeAdapterMap.get(wmsType);
    return adapter != null ? adapter : this.combineitemSynchronizeAdapterMap.get(WmsType.QIMEN);
  }
}
