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
public class WmsFilterFactory {

  private final Map<WmsType, ProductFilter> productFilterMap;
  private final Map<WmsType, DeliveryOrderFilter> deliveryOrderFilterMap;
  private final Map<WmsType, ReturnOrderFilter> returnOrderFilterMap;
  private final Map<WmsType, StockInOrderFilter> stockInOrderFilterMap;
  private final Map<WmsType, StockOutOrderFilter> stockOutOrderFilterMap;

  /**
   * 构造方法.
   *
   * @param productFilters 商品接口
   * @param deliveryOrderFilters 发货单接口
   * @param returnOrderFilters 退货单接口
   * @param stockInOrderFilters 入库单接口
   * @param stockOutOrderFilters 出库单接口
   */
  @Autowired
  public WmsFilterFactory(
      List<ProductFilter> productFilters,
      List<DeliveryOrderFilter> deliveryOrderFilters,
      List<ReturnOrderFilter> returnOrderFilters,
      List<StockInOrderFilter> stockInOrderFilters,
      List<StockOutOrderFilter> stockOutOrderFilters) {
    this.productFilterMap = new HashMap<>(productFilters.size());
    initMap(productFilters, this.productFilterMap);
    this.deliveryOrderFilterMap = new HashMap<>(deliveryOrderFilters.size());
    initMap(deliveryOrderFilters, this.deliveryOrderFilterMap);
    this.returnOrderFilterMap = new HashMap<>(returnOrderFilters.size());
    initMap(returnOrderFilters, this.returnOrderFilterMap);
    this.stockInOrderFilterMap = new HashMap<>(stockInOrderFilters.size());
    initMap(stockInOrderFilters, this.stockInOrderFilterMap);
    this.stockOutOrderFilterMap = new HashMap<>(stockOutOrderFilters.size());
    initMap(stockOutOrderFilters, this.stockOutOrderFilterMap);
  }

  private <T extends WmsFilter> void initMap(List<T> list, Map<WmsType, T> map) {
    for (T wmsFilter : list) {
      for (WmsType wmsType : wmsFilter.supports()) {
        map.put(wmsType, wmsFilter);
      }
    }
  }

  /**
   * 获取商品接口实现.
   *
   * @param wmsType WMS编码
   */
  public ProductFilter getProductFilter(WmsType wmsType) {
    ProductFilter productFilter = this.productFilterMap.get(wmsType);
    return productFilter != null ? productFilter : this.productFilterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2B出库单实现.
   *
   * @param wmsType WMS编码
   */
  public StockOutOrderFilter getStockOutOrderFilter(WmsType wmsType) {
    final StockOutOrderFilter stockOutOrderFilter = this.stockOutOrderFilterMap.get(wmsType);
    return stockOutOrderFilter != null ? stockOutOrderFilter
        : this.stockOutOrderFilterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2B入库单实现.
   *
   * @param wmsType WMS编码
   */
  public StockInOrderFilter getStockInOrderFilter(WmsType wmsType) {
    final StockInOrderFilter stockInOrderFilter = this.stockInOrderFilterMap.get(wmsType);
    return stockInOrderFilter != null ? stockInOrderFilter
        : this.stockInOrderFilterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2C出库单实现.
   *
   * @param wmsType WMS编码
   */
  public DeliveryOrderFilter getDeliveryOrderFilter(WmsType wmsType) {
    final DeliveryOrderFilter deliveryOrderFilter = this.deliveryOrderFilterMap.get(wmsType);
    return deliveryOrderFilter != null ? deliveryOrderFilter
        : this.deliveryOrderFilterMap.get(WmsType.QIMEN);
  }

  /**
   * 获取B2C入库单实现.
   *
   * @param wmsType WMS编码
   */
  public ReturnOrderFilter getReturnOrderFilter(WmsType wmsType) {
    final ReturnOrderFilter returnOrderFilter = this.returnOrderFilterMap.get(wmsType);
    return returnOrderFilter != null ? returnOrderFilter
        : this.returnOrderFilterMap.get(WmsType.QIMEN);
  }
}
