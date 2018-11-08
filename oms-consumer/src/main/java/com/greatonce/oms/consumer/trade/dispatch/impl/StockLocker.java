package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.stock.DispatchStockInfoBO;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderDetailWrapper;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderWrapper;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.StockDispatchStrategyWrapper;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import io.netty.util.internal.ConcurrentSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
@Component
@DispatchOrderCondition
public class StockLocker {

  @Autowired
  private StockService stockService;
  private final Map<StockLockerItem, AtomicInteger> map = new ConcurrentHashMap<>();
  private final Set<DispatchOrderDetailWrapper> lockDetails = new ConcurrentSet<>();

  private boolean take(DispatchOrderDetailWrapper wrapper, DispatchStockInfoBO stockInfo) {
    int availableQuantity = stockInfo.getQuantity() - stockInfo.getLockQuantity();
    if (availableQuantity < wrapper.getNoticeQuantity()) {
      return false;
    }
    StockLockerItem item = new StockLockerItem(stockInfo.getVirtualWarehouseId(),
        wrapper.getSkuId());
    AtomicInteger atomicInteger = map.get(item);
    if (atomicInteger == null) {
      atomicInteger = new AtomicInteger(0);
      map.put(item, atomicInteger);
    }
    if (availableQuantity - atomicInteger.get() >= wrapper.getNoticeQuantity()) {
      atomicInteger.addAndGet(wrapper.getNoticeQuantity());
      this.lockDetails.add(wrapper);
      return true;
    }
    return false;
  }

  void calcStock(DispatchContext context, StockDispatchStrategyWrapper dispatchStrategy,
      DispatchOrderWrapper dispatchOrderWrapper, Collection<Long> virtualWarehouseIds) {
    calcStockDetail(context, dispatchStrategy, dispatchOrderWrapper, virtualWarehouseIds);
  }

  /**
   * 1、获取相同实体仓下可用虚拟仓的指定SKU库存
   * 2、如果各自的推荐虚拟仓库存充足，则继续使用推荐仓库
   * 3、如果推荐仓库不足，则使用同一实体仓下的其他仓库可用库存
   * 4、如果都没有，标记缺货
   *
   * @param dispatchOrderWrapper 配货单
   * @param virtualWarehouseIds 配货仓库
   */
  private void calcStockDetail(DispatchContext context,
      StockDispatchStrategyWrapper dispatchStrategy, DispatchOrderWrapper dispatchOrderWrapper,
      Collection<Long> virtualWarehouseIds) {
    Set<Long> skuIds = dispatchOrderWrapper.getDetails().stream()
        .map(DispatchOrderDetail::getSkuId).collect(Collectors.toSet());
    List<DispatchStockInfoBO> stockInfos =
        stockService.listDispatchStock(skuIds, virtualWarehouseIds,
            dispatchOrderWrapper.getMainSalesOrder().getSub().getMallPaidTime());
    DispatchStockInfoBO stockInfo;
    for (DispatchOrderDetail detail : dispatchOrderWrapper.getDetails()) {
      DispatchOrderDetailWrapper wrapper = (DispatchOrderDetailWrapper) detail;
      wrapper.setOos(false);
      //占用的虚拟仓有货
      Optional<DispatchStockInfoBO> optional = stockInfos.stream().filter(
          x -> x.getVirtualWarehouseId().equals(wrapper.getVirtualWarehouseId()) && x.getSkuId()
              .equals(wrapper.getSkuId())).findFirst();
      if (optional.isPresent()) {
        if (take(wrapper, optional.get())) {
          StockDispatchWarehouse warehouse = dispatchStrategy.getStockDispatchWarehouseMap()
              .get(wrapper.getVirtualWarehouseId());
          List<DispatchOrderDetailWrapper> dispatchableDetails = context.getDispatchableDetails()
              .computeIfAbsent(warehouse.getWarehouseId(), k -> new ArrayList<>(6));
          dispatchableDetails.add(wrapper);
          continue;
        }
      } else {
        optional = stockInfos.stream().filter(x -> x.getSkuId().equals(wrapper.getSkuId()))
            .findFirst();
        if (optional.isPresent()) {
          stockInfo = optional.get();
          if (take(wrapper, stockInfo)) {
            wrapper.setVirtualWarehouseId(stockInfo.getVirtualWarehouseId());
            StockDispatchWarehouse warehouse = dispatchStrategy.getStockDispatchWarehouseMap()
                .get(stockInfo.getVirtualWarehouseId());
            wrapper.setVirtualWarehouseName(warehouse.getVirtualWarehouseName());
            List<DispatchOrderDetailWrapper> dispatchableDetails = context.getDispatchableDetails()
                .computeIfAbsent(warehouse.getWarehouseId(), k -> new ArrayList<>(6));
            dispatchableDetails.add(wrapper);
            continue;
          }
        }
      }
      wrapper.setOos(true);
    }
  }

  /**
   * 释放库存
   */
  void releaseStock(DispatchOrderWrapper dispatchOrder) {
    for (DispatchOrderDetail dispatchOrderDetail : dispatchOrder.getDetails()) {
      DispatchOrderDetailWrapper wrapper = (DispatchOrderDetailWrapper) dispatchOrderDetail;
      if (lockDetails.contains(wrapper)) {
        StockLockerItem item = new StockLockerItem(wrapper.getVirtualWarehouseId(),
            wrapper.getSkuId());
        lockDetails.remove(wrapper);
        final AtomicInteger atomicInteger = map.get(item);
        if (atomicInteger != null) {
          final int value = atomicInteger.addAndGet(-wrapper.getNoticeQuantity());
          if (value == 0) {
            map.remove(item);
          }
        }
      }
    }
  }


  /**
   * 库存申请单.
   */
  public static class StockLockerItem {

    /**
     * 虚拟仓ID.
     */
    private Long virtualWarehouseId;
    /**
     * 规格ID.
     */
    private Long skuId;

    StockLockerItem(Long virtualWarehouseId, Long skuId) {
      this.virtualWarehouseId = virtualWarehouseId;
      this.skuId = skuId;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof StockLockerItem) {
        StockLockerItem item = (StockLockerItem) obj;
        return item.skuId.equals(this.skuId) && item.virtualWarehouseId
            .equals(this.virtualWarehouseId);
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(virtualWarehouseId, skuId);
    }
  }
}
