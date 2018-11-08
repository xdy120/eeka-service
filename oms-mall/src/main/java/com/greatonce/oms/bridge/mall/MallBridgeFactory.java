package com.greatonce.oms.bridge.mall;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.enums.MallType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商城接口工厂.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/6
 */
@Component
public class MallBridgeFactory {

  private final Map<MallType, OrderBridge> orderBridgeMap;
  private final Map<MallType, ProductBridge> productBridgeMap;
  private final Map<MallType, AuthorizeBridge> authorizeBridgeMap;
  private final Map<MallType, RefundBridge> refundBridgeMap;
  private final Map<MallType, ExchangeBridge> exchangeBridgeMap;
  private final Map<MallType, StoreBridge> storeBridgeMap;
  private final Map<MallType, SecurityBridge> securityBridgeMap;
  private final Map<MallType, LogisticsBridge> logisticsBridgeMap;

  /**
   * 构造方法.
   *
   * @param orderBridges 订单接口
   * @param productBridges 商品接口
   * @param authorizeBridges 授权接口
   * @param refundBridges 退款单接口
   * @param exchangeBridges 换货单接口
   * @param storeBridges 店铺接口
   * @param securityBridges 安全接口
   * @param logisticsBridges 物流接口
   */
  @Autowired
  public MallBridgeFactory(List<OrderBridge> orderBridges, List<ProductBridge> productBridges,
      List<AuthorizeBridge> authorizeBridges, List<RefundBridge> refundBridges,
      List<ExchangeBridge> exchangeBridges, List<StoreBridge> storeBridges,
      List<SecurityBridge> securityBridges, List<LogisticsBridge> logisticsBridges) {

    this.orderBridgeMap = new HashMap<>(orderBridges.size());
    this.initMap(orderBridges, this.orderBridgeMap);
    this.productBridgeMap = new HashMap<>(productBridges.size());
    this.initMap(productBridges, this.productBridgeMap);
    this.authorizeBridgeMap = new HashMap<>(authorizeBridges.size());
    this.initMap(authorizeBridges, this.authorizeBridgeMap);
    this.refundBridgeMap = new HashMap<>(refundBridges.size());
    this.initMap(refundBridges, this.refundBridgeMap);
    this.exchangeBridgeMap = new HashMap<>(exchangeBridges.size());
    this.initMap(exchangeBridges, this.exchangeBridgeMap);
    this.storeBridgeMap = new HashMap<>(storeBridges.size());
    this.initMap(storeBridges, this.storeBridgeMap);
    this.securityBridgeMap = new HashMap<>(securityBridges.size());
    this.initMap(securityBridges, this.securityBridgeMap);
    this.logisticsBridgeMap = new HashMap<>(logisticsBridges.size());
    this.initMap(logisticsBridges, this.logisticsBridgeMap);
  }

  private <T extends MallBridge> void initMap(List<T> list, Map<MallType, T> map) {
    for (T mallBridge : list) {
      for (MallType mallType : mallBridge.supports()) {
        map.put(mallType, mallBridge);
      }
    }
  }

  /**
   * 获取订单接口实现.
   */
  public OrderBridge getOrderBridge(MallType mallType) {
    OrderBridge orderBridge = this.orderBridgeMap.get(mallType);
    return Assert.isNull(orderBridge) ? this.orderBridgeMap.get(MallType.UNDEFINE) : orderBridge;
  }

  /**
   * 获取商品接口实现.
   *
   * @param mallType 商城类型
   */
  public ProductBridge getProductBridge(MallType mallType) {
    ProductBridge productBridge = this.productBridgeMap.get(mallType);
    return Assert.isNull(productBridge) ? this.productBridgeMap.get(MallType.UNDEFINE)
        : productBridge;
  }

  /**
   * 获取授权接口.
   *
   * @param mallType 商城类型
   */
  public AuthorizeBridge getAuthorizeBridge(MallType mallType) {
    AuthorizeBridge authorizeBridge = this.authorizeBridgeMap.get(mallType);
    return Assert.isNull(authorizeBridge) ? this.authorizeBridgeMap.get(MallType.UNDEFINE)
        : authorizeBridge;
  }

  /**
   * 获取售后接口.
   *
   * @param mallType 商城类型
   */
  public RefundBridge getRefundBridge(MallType mallType) {

    RefundBridge refundBridge = this.refundBridgeMap.get(mallType);
    return Assert.isNull(refundBridge) ? this.refundBridgeMap.get(MallType.UNDEFINE)
        : refundBridge;
  }

  /**
   * 获取换货接口.
   *
   * @param mallType 商城类型
   */
  public ExchangeBridge getExchangeBridge(MallType mallType) {

    ExchangeBridge exchangeBridge = this.exchangeBridgeMap.get(mallType);
    return Assert.isNull(exchangeBridge) ? this.exchangeBridgeMap.get(MallType.UNDEFINE)
        : exchangeBridge;
  }

  /**
   * 获取店铺换货地址接口.
   *
   * @param mallType 商城类型
   */
  public StoreBridge getStoreBridge(MallType mallType) {
    StoreBridge storeBridge = this.storeBridgeMap.get(mallType);
    return Assert.isNull(storeBridge) ? this.storeBridgeMap.get(MallType.UNDEFINE)
        : storeBridge;
  }

  /**
   * 获取安全接口.
   */
  public SecurityBridge getSecurityBridge(MallType mallType) {

    SecurityBridge securityBridge = this.securityBridgeMap.get(mallType);
    return Assert.isNull(securityBridge) ? this.securityBridgeMap.get(MallType.UNDEFINE)
        : securityBridge;

  }

  /**
   * 获取物流接口.
   */
  public LogisticsBridge getLogisticsBridge(MallType mallType) {

    LogisticsBridge logisticsBridge = this.logisticsBridgeMap.get(mallType);
    return Assert.isNull(logisticsBridge) ? this.logisticsBridgeMap.get(MallType.UNDEFINE)
        : logisticsBridge;

  }
}
