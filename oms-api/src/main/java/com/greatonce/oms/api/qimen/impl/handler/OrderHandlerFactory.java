package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.oms.domain.enums.OrderType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 单据处理工厂.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class OrderHandlerFactory {

  private final Map<OrderType, OrderHandler> orderHandlerMap;
  private final Map<OrderType, StockOutHandler> outHandlerMap;
  private final Map<OrderType, StockInHandler> inHandlerMap;

  /**
   * 构造方法.
   *
   * @param outHandlers 出库单处理器
   * @param inHandlers 入库单处理器
   */
  @Autowired
  public OrderHandlerFactory(List<OrderHandler> orderHandlers, List<StockOutHandler> outHandlers,
      List<StockInHandler> inHandlers) {
    this.orderHandlerMap = new HashMap<>(orderHandlers.size());
    for (OrderHandler handler : orderHandlers) {
      for (OrderType orderType : handler.supports()) {
        this.orderHandlerMap.put(orderType, handler);
      }
    }
    this.outHandlerMap = new HashMap<>(outHandlers.size());
    for (StockOutHandler handler : outHandlers) {
      for (OrderType orderType : handler.supports()) {
        this.outHandlerMap.put(orderType, handler);
      }

    }
    this.inHandlerMap = new HashMap<>(inHandlers.size());
    for (StockInHandler inHandler : inHandlers) {
      for (OrderType orderType : inHandler.supports()) {
        this.inHandlerMap.put(orderType, inHandler);
      }
    }
  }


  public OrderHandler getOrderHandler(OrderType orderType) {
    return this.orderHandlerMap.get(orderType);
  }

  public StockOutHandler getStockOutHandler(OrderType orderType) {
    return this.outHandlerMap.get(orderType);
  }

  public StockInHandler getStockInHandler(OrderType orderType) {
    return this.inHandlerMap.get(orderType);
  }
}
