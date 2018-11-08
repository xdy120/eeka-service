package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.domain.enums.WmsType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 仓库接口工厂.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/6
 */
@Component
public class WmsBridgeFactory {

  private final Map<WmsType, ProductBridge> productBridgeMap;
  private final Map<WmsType, DeliveryOrderBridge> deliveryOrderBridgeMap;
  private final Map<WmsType, ReturnOrderBridge> returnOrderBridgeMap;
  private final Map<WmsType, StockInOrderBridge> stockInOrderBridgeMap;
  private final Map<WmsType, StockOutOrderBridge> stockOutOrderBridgeMap;

  /**
   * 构造方法.
   *
   * @param productBridges 商品接口
   * @param deliveryOrderBridges 发货单接口
   * @param returnOrderBridges 退货单接口
   * @param stockInOrderBridges 入库单接口
   * @param stockOutOrderBridges 出库单接口
   */
  @Autowired
  public WmsBridgeFactory(
      List<ProductBridge> productBridges,
      List<DeliveryOrderBridge> deliveryOrderBridges,
      List<ReturnOrderBridge> returnOrderBridges,
      List<StockInOrderBridge> stockInOrderBridges,
      List<StockOutOrderBridge> stockOutOrderBridges) {
    this.productBridgeMap = new HashMap<>(productBridges.size());
    initMap(productBridges, this.productBridgeMap);
    this.deliveryOrderBridgeMap = new HashMap<>(deliveryOrderBridges.size());
    initMap(deliveryOrderBridges, this.deliveryOrderBridgeMap);
    this.returnOrderBridgeMap = new HashMap<>(returnOrderBridges.size());
    initMap(returnOrderBridges, this.returnOrderBridgeMap);
    this.stockInOrderBridgeMap = new HashMap<>(stockInOrderBridges.size());
    initMap(stockInOrderBridges, this.stockInOrderBridgeMap);
    this.stockOutOrderBridgeMap = new HashMap<>(stockOutOrderBridges.size());
    initMap(stockOutOrderBridges, this.stockOutOrderBridgeMap);
  }

  private <T extends WmsBridge> void initMap(List<T> list, Map<WmsType, T> map) {
    for (T wmsBridge : list) {
      for (WmsType wmsType : wmsBridge.supports()) {
        map.put(wmsType, wmsBridge);
      }
    }
  }

  /**
   * 获取商品接口实现.
   *
   * @param wmsType WMS编码
   */
  public ProductBridge getProductBridge(WmsType wmsType) {
    ProductBridge productBridge = this.productBridgeMap.get(wmsType);
    return productBridge != null ? productBridge : this.productBridgeMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2B出库单实现.
   *
   * @param wmsType WMS编码
   */
  public StockOutOrderBridge getStockOutOrderBridge(WmsType wmsType) {
    final StockOutOrderBridge stockOutOrderBridge = this.stockOutOrderBridgeMap.get(wmsType);
    return stockOutOrderBridge != null ? stockOutOrderBridge
        : this.stockOutOrderBridgeMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2B入库单实现.
   *
   * @param wmsType WMS编码
   */
  public StockInOrderBridge getStockInOrderBridge(WmsType wmsType) {
    final StockInOrderBridge stockInOrderBridge = this.stockInOrderBridgeMap.get(wmsType);
    return stockInOrderBridge != null ? stockInOrderBridge
        : this.stockInOrderBridgeMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2C出库单实现.
   *
   * @param wmsType WMS编码
   */
  public DeliveryOrderBridge getDeliveryOrderBridge(WmsType wmsType) {
    final DeliveryOrderBridge deliveryOrderBridge = this.deliveryOrderBridgeMap.get(wmsType);
    return deliveryOrderBridge != null ? deliveryOrderBridge
        : this.deliveryOrderBridgeMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2C入库单实现.
   *
   * @param wmsType WMS编码
   */
  public ReturnOrderBridge getReturnOrderBridge(WmsType wmsType) {
    final ReturnOrderBridge returnOrderBridge = this.returnOrderBridgeMap.get(wmsType);
    return returnOrderBridge != null ? returnOrderBridge
        : this.returnOrderBridgeMap.get(WmsType.QIMEN);
  }
}
