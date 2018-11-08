package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.AutoDispatchBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.Dispatchable;
import com.greatonce.oms.consumer.trade.dispatch.Mergeable;
import com.greatonce.oms.consumer.trade.dispatch.Validatable;
import com.greatonce.oms.consumer.trade.dispatch.WarehouseValidatable;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderDetailWrapper;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderWrapper;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.StockDispatchStrategyWrapper;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.ExpressStrategyRule.StrategyExpress;
import com.greatonce.oms.domain.base.StockDispatchRule;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting.OrderMergeRule;
import com.greatonce.oms.domain.enums.DispatchMode;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.WaybillStrategy;
import com.greatonce.oms.domain.enums.WaybillType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.DispatchLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/23
 */
@Component
@DispatchOrderCondition
public class Dispatcher implements Dispatchable {

  private static final DispatchLogger LOGGER = OmsLoggerFactory.getDispatchLogger();
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.SALES_ORDER);
  private static final Logger FILE_LOGGER = LoggerFactory.getLogger(Dispatcher.class);

  @Autowired
  private Mergeable mergeable;
  @Autowired
  private Validatable validator;
  @Autowired
  private DispatchOrderBuilder dispatchOrderBuilder;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private StockLocker stockLocker;
  @Autowired
  private WarehouseValidatable warehouseValidatable;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private ExpressService expressService;

  /**
   * 自动配货.
   */
  @Override
  public void dispatch(DispatchContext context) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    if (!validator.validate(mainSalesOrder, context)) {
      return;
    }
    mergeOrder(context);
    DispatchOrderWrapper dispatchOrder = dispatchOrderBuilder.build(context);
    context.getDispatchOrders().add(dispatchOrder);
    boolean warehouseFilter = warehouseFilter(context, dispatchOrder,
        context.getDispatchStrategy().getRule().getWarehouses());
    StockDispatchStrategyWrapper dispatchStrategy =
        new StockDispatchStrategyWrapper(context.getDispatchStrategy());
    context.setDispatchStrategy(dispatchStrategy);
    if (!warehouseFilter) {
      changeSalesOrderStatus(context, dispatchOrder);
      return;
    }
    dispatchOrderWarehouseSetter(context, mainSalesOrder, dispatchStrategy, dispatchOrder);
    try {
      //计算库存，修改占用
      Set<Long> dispatchWarehouseIds =
          dispatchStrategy.getWarehouseIdMap().get(dispatchOrder.getWarehouseId());
      if (!Assert.isEmpty(dispatchWarehouseIds)) {
        stockLocker.calcStock(context, dispatchStrategy, dispatchOrder, dispatchWarehouseIds);
        if (dispatchOrder.isWhole()) {
          doDispatch(context, dispatchOrder);
          return;
        }
        stockLocker.releaseStock(dispatchOrder);
      }
      loopWarehouse(context, dispatchOrder, dispatchStrategy);
      if (dispatchOrder.isWhole()) {
        doDispatch(context, dispatchOrder);
        return;
      }
      //判断拆单
      if (Assert.isTrue(context.getStore().getSetting().getDispatchMode() == DispatchMode.ANY)) {
        splitDispatchOrderFilter(context.getDispatchableDetails());
        if (!Assert.isEmpty(context.getDispatchableDetails())) {
          //如果允许拆单，直接拆掉发
          LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
              mainSalesOrder.getTradeId(),
              "订单{}拆单，合单：{}", mainSalesOrder.getSalesOrderCode(),
              dispatchOrder.isMerged() ? "有" : "无");
          splitDispatchOrder(context, dispatchOrder, dispatchStrategy);
          return;
        } else {
          LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
              mainSalesOrder.getTradeId(),
              "订单{}无主单可配明细，无法拆单配货。", mainSalesOrder.getSalesOrderCode());
        }
      } else {
        //如果是合单，去掉其他明细，主单单独配货
        if (dispatchOrder.isMerged()) {
          LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
              mainSalesOrder.getTradeId(), "库存不足，订单{}取消合单", mainSalesOrder.getSalesOrderCode());
          dispatchOrder.getDetails()
              .removeIf(x -> !((DispatchOrderDetailWrapper) x).isMainOrderDetail());
          dispatchOrder.setMerged(false);
          loopWarehouse(context, dispatchOrder, dispatchStrategy, true);
          if (dispatchOrder.isWhole()) {
            doDispatch(context, dispatchOrder);
            return;
          }
        }
      }
      //整单配货失败修改状态
      changeSalesOrderStatus(context, dispatchOrder);
    } catch (Exception e) {
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "配货失败，异常信息：" + e);
      FILE_LOGGER.error("配货失败，异常信息：", e);
      changeSalesOrderStatus(context, dispatchOrder);
    } finally {
      stockLocker.releaseStock(dispatchOrder);
    }
  }

  /**
   * 拆单前过滤主单不可配明细的记录.
   */
  private void splitDispatchOrderFilter(Map<Long, List<DispatchOrderDetailWrapper>> map) {
    map.entrySet().removeIf(entry -> entry.getValue().stream()
        .noneMatch(DispatchOrderDetailWrapper::isMainOrderDetail));
  }

  /**
   * 配货单初始仓库设置.
   */
  private void dispatchOrderWarehouseSetter(DispatchContext context, SalesOrder mainSalesOrder,
      StockDispatchStrategyWrapper dispatchStrategy,
      DispatchOrderWrapper dispatchOrder) {
    Map<Long, StockDispatchWarehouse> strategyWarehouseMap =
        getDispatchWarehouMap(dispatchStrategy);
    if (mainSalesOrder.getSuggestVirtualWarehouseId() != null
        && mainSalesOrder.getSuggestWarehouseId() != null
        && strategyWarehouseMap.get(mainSalesOrder.getSuggestVirtualWarehouseId()) != null) {
      dispatchOrder.setWarehouseId(mainSalesOrder.getSuggestWarehouseId());
      dispatchOrder.setWarehouseName(mainSalesOrder.getSuggestWarehouseName());
      dispatchOrder.getDetails().forEach(x -> {
        x.setVirtualWarehouseId(mainSalesOrder.getSuggestVirtualWarehouseId());
        x.setVirtualWarehouseName(mainSalesOrder.getSuggestVirtualWarehouseName());
      });

      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "订单{}中有推荐仓库{}且可用，设置为配货单的推荐仓库",
          mainSalesOrder.getSalesOrderCode(), mainSalesOrder.getSuggestVirtualWarehouseName());
    } else {
      List<StockDispatchWarehouse> list = strategyWarehouseMap.values().stream().sorted()
          .collect(Collectors.toList());
      StockDispatchWarehouse warehouse = list.get(0);
      dispatchOrder.setWarehouseId(warehouse.getWarehouseId());
      dispatchOrder.setWarehouseName(warehouse.getWarehouseName());
      dispatchOrder.getDetails().forEach(x -> {
        x.setVirtualWarehouseId(warehouse.getVirtualWarehouseId());
        x.setVirtualWarehouseName(warehouse.getVirtualWarehouseName());
      });
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "设置配货单的推荐仓库为{}", warehouse.getVirtualWarehouseName());
    }
    //没有指定快递就设置
    if (!context.isHasDesignatedExpress()) {
      StrategyExpress strategyExpress = strategyWarehouseMap
          .get(dispatchOrder.getDetails().get(0).getVirtualWarehouseId()).getExpressStrategy()
          .getRule().getExpresses().get(0);
      dispatchOrder.setSuggestExpressId(strategyExpress.getExpressId());
      dispatchOrder.setSuggestExpressName(strategyExpress.getExpressName());
    }
  }

  /**
   * 不可配仓库过滤.
   *
   * @return 返回是否有可用仓库.
   */
  private boolean warehouseFilter(DispatchContext context, DispatchOrderWrapper dispatchOrder,
      List<StockDispatchWarehouse> warehouses) {
    Iterator<StockDispatchWarehouse> iterator = warehouses.iterator();
    StockDispatchWarehouse warehouse;
    for (; iterator.hasNext(); ) {
      warehouse = iterator.next();
      boolean validation = warehouseValidatable.validate(context, dispatchOrder, warehouse);
      if (!validation) {
        iterator.remove();
        continue;
      }
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMainSalesOrder().getSalesOrderCode(),
          context.getMainSalesOrder().getTradeId(), "仓库{}通过校验",
          warehouse.getVirtualWarehouseName());
    }
    if (warehouses.size() == 0) {
      return false;
    }
    return true;
  }

  /**
   * 仓库格式转换.
   * 转换库存配货策略仓库集合转换为<虚拟仓id，策略虚拟仓>的map
   */
  private Map<Long, StockDispatchWarehouse> getDispatchWarehouMap(
      StockDispatchStrategyWrapper dispatchStrategy) {
    StockDispatchRule rule = dispatchStrategy.getRule();
    return rule.getWarehouses().stream()
        .collect(Collectors.toMap(StockDispatchWarehouse::getVirtualWarehouseId, x -> x));
  }

  /**
   * 配货合单.
   */
  private void mergeOrder(DispatchContext context) {
    //订单配置判断
    if (context.getSalesOrderSetting().getOrderMergeRule() == OrderMergeRule.NONE) {
      return;
    }
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    //合单订单
    List<SalesOrder> combOrders = mergeable.listMergeableSalesOrder(mainSalesOrder);
    if (!Assert.isEmpty(combOrders)) {
      combOrders.removeIf(salesOrder -> !validator.validate(salesOrder, context));
      for (SalesOrder combOrder : combOrders) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(),
            "订单{}自动配货，与{}合单", mainSalesOrder.getSalesOrderCode(), combOrder.getSalesOrderCode());
        context.getVersionMap().put(combOrder.getSalesOrderId(), combOrder.getVersion());
      }
      context.setSalesOrders(combOrders);
    }
  }

  /**
   * 配货处理.
   * <p/>
   *
   * 逻辑：
   * 1.如果时oms获取面单，拿到电子面单后保存到配货单中，然后按配置是否回传平台快递单号。如果拿不到直接配货失败。
   * 2.保存配货单
   */
  private void doDispatch(DispatchContext context, DispatchOrder dispatchOrder) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
        mainSalesOrder.getTradeId(), "配货选择仓库成功，配货仓库为{}，{}，匹配快递为{}",
        dispatchOrder.getWarehouseName(),
        dispatchOrder.getDetails().get(0).getVirtualWarehouseName(),
        dispatchOrder.getSuggestExpressName());

    setRemark(context, dispatchOrder);

    Express express = expressService.getByKey(dispatchOrder.getSuggestExpressId());
    //判断电子面单的获取方式
    if (warehouseService.getByKey(dispatchOrder.getWarehouseId()).getWaybillStrategy()
        == WaybillStrategy.OMS) {
      if (express.getWaybillType() != WaybillType.NONE) {
        //OMS获取电子面单
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(), "订单{}由OMS获取电子面单",
            mainSalesOrder.getSalesOrderCode());
        SecurityBridge securityBridge =
            mallBridgeFactory.getSecurityBridge(context.getStore().getMallType());
        dispatchOrder.setContact(
            securityBridge.decrypt(context.getStore(), dispatchOrder.getContact(), DataType.NAME));
        dispatchOrder.setMobile(
            securityBridge.decrypt(context.getStore(), dispatchOrder.getMobile(), DataType.MOBILE));
        dispatchOrder.setTelephone(securityBridge
            .decrypt(context.getStore(), dispatchOrder.getTelephone(), DataType.TELEPHONE));
        dispatchOrderService.getWayBill(dispatchOrder, express);
        //没获取到面单则进入下一个快递逻辑
        if (Assert.isEmpty(dispatchOrder.getSuggestExpressNo())) {
          StockDispatchStrategyWrapper strategy = (StockDispatchStrategyWrapper) context
              .getDispatchStrategy();
          StockDispatchWarehouse warehouse = strategy.getStockDispatchWarehouseMap()
              .get(dispatchOrder.getDetails().get(0).getVirtualWarehouseId());
          for (StrategyExpress strategyExpress : warehouse.getExpressStrategy().getRule()
              .getExpresses()) {
            if (strategyExpress.getExpressId().equals(dispatchOrder.getSuggestExpressId())) {
              continue;
            }
            LOGGER.info(context.getSerialNo(), context.getStore(),
                mainSalesOrder.getSalesOrderCode(),
                mainSalesOrder.getTradeId(), "更换快递为{}来尝试获取面单", strategyExpress.getExpressName());
            dispatchOrder.setSuggestExpressId(strategyExpress.getExpressId());
            dispatchOrder.setSuggestExpressName(strategyExpress.getExpressName());
            express = expressService.getByKey(dispatchOrder.getSuggestExpressId());
            if (express.getWaybillType() == WaybillType.NONE) {
              LOGGER.info(context.getSerialNo(), context.getStore(),
                  mainSalesOrder.getSalesOrderCode(),
                  mainSalesOrder.getTradeId(), "快递{}不支持获取电子面单，直接推送仓库", express.getExpressName());
              break;
            } else {
              dispatchOrderService.getWayBill(dispatchOrder, express);
              if (!Assert.isEmpty(dispatchOrder.getSuggestExpressNo())) {
                break;
              }
            }
          }
          //跳出循环时如果快递支持面单且配货单中没有面单号说明没获取到快递
          if (Assert.isEmpty(dispatchOrder.getSuggestExpressNo()) &&
              express.getWaybillType() != WaybillType.NONE) {
            LOGGER
                .info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
                    mainSalesOrder.getTradeId(), "配货失败，获取所有可配快递电子面单都失败，请查看日志");
            BIZ_LOGGER.log(mainSalesOrder.getSalesOrderId(), BizLogger.DISPATCH,
                "所有可配快递获取面单都失败，配货失败");
            throw new OmsException(
                String.format("交易号为%s订单获取面单失败，配货失败", context.getMainSalesOrder().getTradeId()));
          }
        }
        dispatchOrder.setContact(mainSalesOrder.getContact());
        dispatchOrder.setMobile(mainSalesOrder.getMobile());
        dispatchOrder.setTelephone(mainSalesOrder.getTelephone());
      } else {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(), "快递{}不支持获取电子面单，直接推送仓库", express.getExpressName());
      }
    }
    AutoDispatchBO autoDispatchBO = new AutoDispatchBO();
    autoDispatchBO.setVersionMap(context.getVersionMap());
    dispatchOrderService.autoDispatch(dispatchOrder, autoDispatchBO);
  }

  /**
   * 设置备注.
   */
  private void setRemark(DispatchContext context, DispatchOrder dispatchOrder) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    if (context.isMerge()) {
      context.getSalesOrders().forEach(order -> {
        if (Assert.isEmpty(dispatchOrder.getRemark())) {
          dispatchOrder.setRemark(order.getRemark());
        } else {
          dispatchOrder.setRemark(
              dispatchOrder.getRemark().concat(System.lineSeparator()).concat(order.getRemark()));
        }
      });
    } else {
      dispatchOrder.setRemark(mainSalesOrder.getRemark());
    }
  }

  /**
   * 配货拆单.
   * 如果允许拆单，剔除无货明细后只要有货配掉,全部没货就换仓库配
   */
  private void splitDispatchOrder(DispatchContext context, DispatchOrderWrapper dispatchOrder,
      StockDispatchStrategyWrapper dispatchStrategy) {

    Long maxWarehouseId = context.getDispatchableDetails()
        .entrySet().stream().max(Comparator.comparingInt(o -> o.getValue().size())).get().getKey();
    stockLocker.calcStock(context, dispatchStrategy, dispatchOrder,
        dispatchStrategy.getWarehouseIdMap().get(maxWarehouseId));
    String warehouseName = dispatchStrategy.getWarehouseMap().get(maxWarehouseId).get(0)
        .getWarehouseName();
    dispatchOrder.setWarehouseId(maxWarehouseId);
    dispatchOrder.setWarehouseName(warehouseName);

    Iterator<DispatchOrderDetail> iterator = dispatchOrder.getDetails().iterator();
    //缺货明细
    List<DispatchOrderDetail> oosDetails = new ArrayList<>(dispatchOrder.getDetails().size());
    while (iterator.hasNext()) {
      DispatchOrderDetailWrapper detail = (DispatchOrderDetailWrapper) iterator.next();
      if (detail.isOos()) {
        oosDetails.add(detail);
        iterator.remove();
      }
    }
    if (!Assert.isEmpty(dispatchOrder.getDetails())) {
      doDispatch(context, dispatchOrder);
      //拆单配货完更新context中修改了版本的订单
      List<Long> needUpdateSalesOrderIds = dispatchOrder.getDetails().stream()
          .filter(x -> oosDetails.stream()
              .anyMatch(z -> x.getSalesOrderId().equals(z.getSalesOrderId())))
          .map(DispatchOrderDetail::getSalesOrderId)
          .distinct().collect(Collectors.toList());
      if (!Assert.isEmpty(needUpdateSalesOrderIds)) {
        if (needUpdateSalesOrderIds.contains(context.getMainSalesOrder().getSalesOrderId())) {
          context.setMainSalesOrder(
              salesOrderService.getSimpleInfo(context.getMainSalesOrder().getSalesOrderId()));
          needUpdateSalesOrderIds
              .removeIf(x -> x.equals(context.getMainSalesOrder().getSalesOrderId()));
        }
        if (needUpdateSalesOrderIds.stream().anyMatch(
            x -> context.getSalesOrders().stream().anyMatch(z -> x.equals(z.getSalesOrderId())))) {
          context.getSalesOrders()
              .removeIf(x -> needUpdateSalesOrderIds.contains(x.getSalesOrderId()));
          needUpdateSalesOrderIds
              .forEach(x -> context.getSalesOrders().add(salesOrderService.getSimpleInfo(x)));
        }
      }
    }
    if (!Assert.isEmpty(oosDetails)) {
      dispatchOrder.setDetails(oosDetails);
      changeSalesOrderStatus(context, dispatchOrder);
    }
  }

  private void loopWarehouse(DispatchContext context, DispatchOrderWrapper dispatchOrder,
      StockDispatchStrategyWrapper dispatchStrategy) {
    loopWarehouse(context, dispatchOrder, dispatchStrategy, false);
  }

  /**
   * 遍历实体仓.
   *
   * @param dispatchOrder 配货单
   * @param dispatchStrategy 配货策略
   * @param includeSelf 是否包含配货单仓库
   */
  private void loopWarehouse(DispatchContext context, DispatchOrderWrapper dispatchOrder,
      StockDispatchStrategyWrapper dispatchStrategy, boolean includeSelf) {
    StockDispatchWarehouse suggestVirtualWarehouse;
    for (Entry<Long, List<StockDispatchWarehouse>> entry : dispatchStrategy.getWarehouseMap()
        .entrySet()) {
      Long warehouseId = entry.getKey();
      if (includeSelf || !dispatchOrder.getWarehouseId().equals(warehouseId)) {
        Set<Long> virtualWarehouseIds = dispatchStrategy.getWarehouseIdMap().get(warehouseId);
        if (Assert.isEmpty(virtualWarehouseIds)) {
          continue;
        }
        //该实体仓下优先级最高的虚拟仓，设为推荐仓库
        suggestVirtualWarehouse = entry.getValue().get(0);
        dispatchOrder.setWarehouseId(suggestVirtualWarehouse.getWarehouseId());
        dispatchOrder.setWarehouseName(suggestVirtualWarehouse.getWarehouseName());
        for (DispatchOrderDetail detail : dispatchOrder.getDetails()) {
          detail.setVirtualWarehouseId(suggestVirtualWarehouse.getVirtualWarehouseId());
          detail.setVirtualWarehouseName(suggestVirtualWarehouse.getVirtualWarehouseName());
        }
        dispatchRetry(context, dispatchStrategy, dispatchOrder, virtualWarehouseIds);
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMainSalesOrder().getSalesOrderCode(),
            context.getMainSalesOrder().getTradeId(),
            "更换实体仓为：{}，尝试进行配货", dispatchOrder.getWarehouseName());
        if (dispatchOrder.isWhole()) {
          if (context.isHasDesignatedExpress()) {
            return;
          } else {
            //判断配货过程是否更换了虚拟仓
            if (!suggestVirtualWarehouse.getVirtualWarehouseId()
                .equals(dispatchOrder.getDetails().get(0).getVirtualWarehouseId())) {
              for (StockDispatchWarehouse stockDispatchWarehouse : entry.getValue()) {
                if (stockDispatchWarehouse.getVirtualWarehouseId()
                    .equals(dispatchOrder.getDetails().get(0).getVirtualWarehouseId())) {
                  suggestVirtualWarehouse = stockDispatchWarehouse;
                  break;
                }
              }
            }
            StrategyExpress express =
                suggestVirtualWarehouse.getExpressStrategy().getRule().getExpresses().get(0);
            dispatchOrder.setSuggestExpressId(express.getExpressId());
            dispatchOrder.setSuggestExpressName(express.getExpressName());
            LOGGER.info(context.getSerialNo(), context.getStore(),
                context.getMainSalesOrder().getSalesOrderCode(),
                context.getMainSalesOrder().getTradeId(),
                "虚拟仓：{}满足整单，设置该仓库下的策略快递：{}",
                dispatchOrder.getDetails().get(0).getVirtualWarehouseName(),
                dispatchOrder.getSuggestExpressName());
            return;
          }
        }
        stockLocker.releaseStock(dispatchOrder);
      }
    }
  }

  /**
   * 重试配货.
   */
  private void dispatchRetry(DispatchContext context, StockDispatchStrategyWrapper dispatchStrategy,
      DispatchOrderWrapper dispatchOrder, Collection<Long> dispatchWarehouseIds) {
    stockLocker.calcStock(context, dispatchStrategy, dispatchOrder, dispatchWarehouseIds);
  }

  /**
   * 修改订单状态.
   *
   * 1.无可配仓库导致配货失败
   * 2.配货过程中出现异常导致配货失败
   * 3.配货失败，找到缺货明细和修改订单状态 如果订单可以拆单，需要修改所有拆单订单里面的状态和标记缺货商品明细
   * 4.如果订单不能拆单，只修改主订单的状态和标记缺货商品明细
   */
  private void changeSalesOrderStatus(DispatchContext context, DispatchOrderWrapper dispatchOrder) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    if (context.getDispatchStrategy().getRule().getWarehouses().size() == 0) {
      VersionBO<SalesOrder> bo = new VersionBO<>();
      bo.setVersion(mainSalesOrder.getVersion());
      LOGGER.info(context.getSerialNo(), context.getStore(),
          mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "配货失败，库存配货策略中的仓库都校验失败！");
      BIZ_LOGGER.log(mainSalesOrder.getSalesOrderId(), BizLogger.DISPATCH, "配货失败，无可配仓库");
      salesOrderService.setDispatchFailingStatus(mainSalesOrder, bo);
    } else if (dispatchOrder.getDetails().stream()
        .anyMatch(x -> ((DispatchOrderDetailWrapper) x).isOos())) {
      List<SalesOrder> salesOrders = new ArrayList<>(context.getSalesOrders().size() + 1);
      salesOrders.add(mainSalesOrder);
      //不能拆单情况下如果合单，清除合单，只对主订单进行处理
      if (context.getStore().getSetting().getDispatchMode() != DispatchMode.ANY && dispatchOrder
          .isMerged()) {
        dispatchOrder.getDetails().removeIf(
            x -> !x.getSalesOrderId().equals(dispatchOrder.getMainSalesOrder().getSalesOrderId()));
        dispatchOrder.setMerged(false);
      } else {
        salesOrders.addAll(context.getSalesOrders());
      }
      //找出缺货明细
      for (SalesOrder salesOrder : salesOrders) {
        List<SalesOrderDetail> oosDetails = new ArrayList<>(dispatchOrder.getDetails().size());
        for (DispatchOrderDetail detail : dispatchOrder.getDetails()) {
          DispatchOrderDetailWrapper detailWrapper = (DispatchOrderDetailWrapper) detail;
          if (salesOrder.getSalesOrderId().equals(detailWrapper.getSalesOrderId()) &&
              Assert.isTrue(detailWrapper.isOos())) {
            LOGGER.info(context.getSerialNo(), context.getStore(),
                mainSalesOrder.getSalesOrderCode(),
                mainSalesOrder.getTradeId(), "配货失败，订单：{} 的明细：{}--{} 缺货",
                salesOrder.getSalesOrderCode(), detailWrapper.getSkuCode(),
                detailWrapper.getSkuName());
            oosDetails.add(detailWrapper.getSalesOrderDetail());
          }
        }
        if (!Assert.isEmpty(oosDetails)) {
          salesOrderService.autoDispatchOOS(salesOrder, oosDetails);
        }
      }
      BIZ_LOGGER.log(mainSalesOrder.getSalesOrderId(), BizLogger.DISPATCH, "配货失败，库存不足");
    } else {
      VersionBO<SalesOrder> bo = new VersionBO<>();
      bo.setVersion(mainSalesOrder.getVersion());
      LOGGER.info(context.getSerialNo(), context.getStore(),
          mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "配货失败，配货过程中出现异常");
      BIZ_LOGGER.log(mainSalesOrder.getSalesOrderId(), BizLogger.DISPATCH,
          "配货失败，配货过程中出现异常，请联系管理员查看配货日志");
      salesOrderService.setDispatchFailingStatus(mainSalesOrder, bo);
    }
  }
}
