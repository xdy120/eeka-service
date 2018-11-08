package com.greatonce.oms.biz.impl.trade;

import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.DispatchOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author buer
 * @version 2018-01-08 15:56
 */
public final class DispatchOrderUtil {

  public static void isBeforeDelivery(DispatchOrder dispatchOrder) {
    inStatus(dispatchOrder, DispatchOrderStatus.CREATED, DispatchOrderStatus.NOTIFIED,
        DispatchOrderStatus.NOTICE_FAILED);
    inOutStatus(dispatchOrder, TradeExceptions.DISPATCH_ORDER_NOT_ALLOW_ACTION, OutStatus.NO_OUT);
  }

  public static void isBeforeAllDelivery(DispatchOrder dispatchOrder) {
    inStatus(dispatchOrder, DispatchOrderStatus.CREATED, DispatchOrderStatus.NOTIFIED,
        DispatchOrderStatus.NOTICE_FAILED);
    inOutStatus(dispatchOrder, TradeExceptions.DISPATCH_ORDER_NOT_ALLOW_ACTION, OutStatus.NO_OUT,
        OutStatus.PART_OUT);
  }

  public static void inStatus(DispatchOrder dispatchOrder, DispatchOrderStatus... statuses) {
    inStatus(dispatchOrder, TradeExceptions.DISPATCH_ORDER_NOT_ALLOW_ACTION, statuses);
  }

  public static void inStatus(DispatchOrder dispatchOrder, OmsException ex,
      DispatchOrderStatus... statuses) {
    if (Arrays.stream(statuses).noneMatch(x -> x == dispatchOrder.getStatus())) {
      throw ex;
    }
  }

  public static void isBeforeDelivery(DispatchOrderDetail detail) {
    inStatus(detail, TradeExceptions.DISPATCH_ORDER_NOT_ALLOW_ACTION,
        DispatchOrderDetailStatus.WAITING);
  }

  public static void inStatus(DispatchOrderDetail detail, OmsException ex,
      DispatchOrderDetailStatus... statuses) {
    if (Arrays.stream(statuses).noneMatch(x -> x == detail.getStatus())) {
      throw ex;
    }
  }

  public static void inOutStatus(DispatchOrder dispatchOrder, OmsException ex,
      OutStatus... statuses) {
    if (Arrays.stream(statuses).noneMatch(x -> x == dispatchOrder.getOutStatus())) {
      throw ex;
    }
  }

  public static void recountStatus(DispatchOrder dispatchOrder, List<DispatchOrderDetail> details) {
    int total = 0;
    int delivered = 0;
    int canceled = 0;
    for (DispatchOrderDetail detail : details) {
      switch (detail.getStatus()) {
        case CANCELED:
          canceled++;
          total++;
          break;
        case WAITING:
          total++;
          break;
        case DELIVERED:
          delivered++;
          total++;
          break;
        default:
          break;
      }
      total++;
    }
    //已作废
    if (total == canceled) {
      dispatchOrder.setStatus(DispatchOrderStatus.CANCELED);
    }
    //出库状态
    if (total == delivered) {
      dispatchOrder.setOutStatus(OutStatus.ALL_OUT);
    } else if (delivered > 0) {
      dispatchOrder.setOutStatus(OutStatus.PART_OUT);
    } else {
      dispatchOrder.setOutStatus(OutStatus.NO_OUT);
    }
  }

  /**
   * 生成占用
   *
   * @param dispatchOrder 订单
   * @param details 商品明细
   */
  public static List<StockOccupancy> buildOccupancy(DispatchOrder dispatchOrder,
      List<DispatchOrderDetail> details) {
    List<StockOccupancy> occupancies = new ArrayList<>(details.size());
    for (DispatchOrderDetail detail : details) {
      occupancies.add(buildOccupancy(dispatchOrder, detail));
    }
    return occupancies;
  }

  /**
   * 生成占用
   *
   * @param dispatchOrder 订单
   * @param detail 订单明细
   */
  public static StockOccupancy buildOccupancy(DispatchOrder dispatchOrder,
      DispatchOrderDetail detail) {
    return new StockOccupancy() {{
      setVirtualWarehouseId(detail.getVirtualWarehouseId());
      setVirtualWarehouseName(detail.getVirtualWarehouseName());
      setWarehouseId(dispatchOrder.getWarehouseId());
      setWarehouseName(dispatchOrder.getWarehouseName());
      setSkuId(detail.getSkuId());
      setSkuCode(detail.getSkuCode());
      setStockOccupancyType(StockOccupancyType.DISPATCH_ORDER);
      setQuantity(detail.getNoticeQuantity());
      setMainId(dispatchOrder.getDispatchOrderId());
      setDetailId(detail.getDispatchOrderDetailId());
      setStatus(StockOccupancyStatus.LOCK);
    }};
  }
}
