package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.MathUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 订单工具类.
 *
 * @author buer
 * @version 2017-12-29 18:28
 */
public abstract class SalesOrderUtil {

  public static final SalesOrderStatus[] BEFORE_DISPATCH_STATUS = new SalesOrderStatus[]{
      SalesOrderStatus.CREATED, SalesOrderStatus.AUDITED_ABNORMAL, SalesOrderStatus.AUDITED,
      SalesOrderStatus.DISPATCHED_ABNORMAL};
  private static final SalesOrderStatus[] BEFORE_ALL_DISPATCH_STATUS = new SalesOrderStatus[]{
      SalesOrderStatus.CREATED, SalesOrderStatus.AUDITED_ABNORMAL, SalesOrderStatus.AUDITED,
      SalesOrderStatus.DISPATCHED_ABNORMAL, SalesOrderStatus.DISPATCHED};
  public static final SalesOrderStatus[] BEFORE_DELIVERY_STATUS = new SalesOrderStatus[]{
      SalesOrderStatus.CREATED, SalesOrderStatus.AUDITED_ABNORMAL, SalesOrderStatus.AUDITED,
      SalesOrderStatus.DISPATCHED_ABNORMAL, SalesOrderStatus.DISPATCHED,
      SalesOrderStatus.DELIVERY_ABNORMAL};
  private static final SalesOrderStatus[] BEFORE_ALL_DELIVERY_STATUS = new SalesOrderStatus[]{
      SalesOrderStatus.CREATED, SalesOrderStatus.AUDITED_ABNORMAL, SalesOrderStatus.AUDITED,
      SalesOrderStatus.DISPATCHED_ABNORMAL, SalesOrderStatus.DISPATCHED, SalesOrderStatus
      .DELIVERY_ABNORMAL, SalesOrderStatus.DELIVERED};

  /**
   * 重新设置状态.
   * 如果普通明细全部作废或退款-> 集合为0 ->单据设为作废状态
   * 设置配货状态
   * 设置发货状态
   * 此方法除作废外，不设置订单状态
   *
   * @param order 订单
   * @param details 明细
   */
  public static void recountStatus(SalesOrder order, List<SalesOrderDetail> details) {
    int total = 0;
    int normal = 0;
    int dispatched = 0;
    int delivered = 0;
    int refunded = 0;
    for (SalesOrderDetail detail : details) {
      if (detail.getSalesOrderDetailType() == SalesOrderDetailType.NORMAL) {
        switch (detail.getStatus()) {
          case WAITING:
            normal++;
            break;
          case DISPATCHED:
            dispatched++;
            normal++;
            break;
          case DELIVERED:
            dispatched++;
            delivered++;
            normal++;
            break;
          default:
            break;
        }
        if (detail.getSalesOrderDetailRefundStatus() == SalesOrderDetailRefundStatus.REFUND) {
          refunded++;
          if (detail.getStatus() == SalesOrderDetailStatus.WAITING) {
            normal--;
          }
        }
        total++;
      }
    }
    if (refunded > 0) {
      order.setRefundStatus(refunded < total ? RefundStatus.PART : RefundStatus.ALL);
    } else {
      order.setRefundStatus(RefundStatus.NONE);
    }
    //已作废
    if (total == 0 ||
        (order.getRefundStatus() == RefundStatus.ALL && delivered == 0 && dispatched == 0)) {
      order.setStatus(SalesOrderStatus.INVALID);
      order.setOos(false);
      order.setDispatchStatus(DispatchStatus.NONE);
      order.setDeliveryStatus(DeliveryStatus.NONE);
    } else {
      //配货状态
      if (normal <= dispatched) {
        order.setDispatchStatus(DispatchStatus.ALL);
        order.setStatus(SalesOrderStatus.DISPATCHED);
      } else if (dispatched > 0) {
        order.setDispatchStatus(DispatchStatus.PART);
        order.setStatus(SalesOrderStatus.DISPATCHED);
      } else {
        order.setDispatchStatus(DispatchStatus.NONE);
      }
      //发货状态
      if (normal <= delivered) {
        order.setDeliveryStatus(DeliveryStatus.ALL);
        order.setStatus(SalesOrderStatus.DELIVERED);
      } else if (delivered > 0) {
        order.setDeliveryStatus(DeliveryStatus.PART);
        order.setStatus(SalesOrderStatus.DELIVERED);
      } else {
        order.setDeliveryStatus(DeliveryStatus.NONE);
      }
    }
  }

  /**
   * 校验订单在配货前.
   *
   * @param order 订单
   */
  public static void mustBeforeDispatch(SalesOrder order) {
    mustStatus(order, BEFORE_DISPATCH_STATUS);
    mustDispatchStatus(order, TradeExceptions.SALES_ORDER_DISPATCHED, DispatchStatus.NONE);
  }

  /**
   * 明细在配货前.
   *
   * @param detail 订单明细
   */
  public static void mustBeforeDispatch(SalesOrderDetail detail) {
    inStatus(detail, SalesOrderDetailStatus.WAITING);
  }

  /**
   * 明细在发货前.
   *
   * @param detail 订单明细
   */
  public static void mustBeforeDelivery(SalesOrderDetail detail) {
    inStatus(detail, SalesOrderDetailStatus.WAITING, SalesOrderDetailStatus.DISPATCHED);
  }

  /**
   * 校验订单在全部配货前.
   *
   * @param order 订单
   */
  public static void mustBeforeAllDispatch(SalesOrder order) {
    mustStatus(order, BEFORE_ALL_DELIVERY_STATUS);
    mustDispatchStatus(order, TradeExceptions.SALES_ORDER_DISPATCHED, DispatchStatus.NONE,
        DispatchStatus.PART);
  }

  /**
   * 校验订单在发货前.
   *
   * @param order 订单
   */
  public static void mustBeforeDelivery(SalesOrder order) {
    mustStatus(order, BEFORE_DELIVERY_STATUS);
    mustDeliveryStatus(order, TradeExceptions.SALES_ORDER_DELIVERED, DeliveryStatus.NONE);
  }

  /**
   * 校验订单在全部发货前.
   *
   * @param order 订单
   */
  public static void mustBeforeAllDelivery(SalesOrder order) {
    mustStatus(order, BEFORE_ALL_DELIVERY_STATUS);
    mustDeliveryStatus(order, TradeExceptions.SALES_ORDER_DELIVERED, DeliveryStatus.NONE,
        DeliveryStatus.PART);
  }

  /**
   * 校验订单状态必须在指定状态内，否则异常.
   *
   * @param order 订单
   * @param statuses 状态
   */
  public static void mustStatus(SalesOrder order, SalesOrderStatus... statuses) {
    mustStatus(order, TradeExceptions.SALES_ORDER_NOT_ALLOW_ACTION, statuses);
  }

  public static void mustStatus(SalesOrder order, OmsException ex, SalesOrderStatus... statuses) {
    if (!inStatus(order, statuses)) {
      throw ex;
    }
  }

  public static void mustDispatchStatus(SalesOrder order, OmsException ex,
      DispatchStatus... statuses) {
    if (!inDispatchStatus(order, statuses)) {
      throw ex;
    }
  }

  public static void mustDeliveryStatus(SalesOrder order, OmsException ex,
      DeliveryStatus... statuses) {
    if (!inDeliveryStatus(order, statuses)) {
      throw ex;
    }
  }

  /**
   * 校验订单明细必须在指定状态，否则异常.
   *
   * @param detail 订单明细
   * @param statuses 状态
   */
  public static void inStatus(SalesOrderDetail detail, SalesOrderDetailStatus... statuses) {
    if (Arrays.stream(statuses).noneMatch(x -> x == detail.getStatus())) {
      throw TradeExceptions.SALES_ORDER_NOT_ALLOW_ACTION;
    }
  }

  public static boolean inStatus(SalesOrder order, SalesOrderStatus... statuses) {
    return Arrays.stream(statuses).anyMatch(x -> x == order.getStatus());
  }

  public static boolean inDispatchStatus(SalesOrder order, DispatchStatus... statuses) {
    return Arrays.stream(statuses).anyMatch(x -> x == order.getDispatchStatus());
  }

  public static boolean inDeliveryStatus(SalesOrder order, DeliveryStatus... statuses) {
    return Arrays.stream(statuses).anyMatch(x -> x == order.getDeliveryStatus());
  }

  /**
   * 验证输入信息.
   *
   * @param order 订单
   */
  public static void validateInput(SalesOrder order) {
    Assert.notNull(order, SysExceptions.DATA_NOT_ALLOW_EMPTY);
    Assert.notNull(order.getSalesOrderId(), SysExceptions.PRIMARY_KEY_NOT_ALLOW_EMPTY);
    Assert.notNull(order.getVersion(), SysExceptions.VERSION_NOT_ALLOW_EMPTY);
  }

  /**
   * 验证收货信息.
   */
  public static void validateReceiverInfo(SalesOrder order) {
    Assert.notEmpty(order.getContact(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notFalse(Assert.isEmpty(order.getMobile()) && Assert.isEmpty(order.getTelephone()),
        TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notEmpty(order.getAddress(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notEmpty(order.getCountryName(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notEmpty(order.getProvinceName(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notEmpty(order.getCityName(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notEmpty(order.getDistrictName(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
  }

  /**
   * 校验异常明细
   */
  public static void validateAbnormalDetail(SalesOrder order) {
    Assert.notEmpty(order.getDetails(), TradeExceptions.SALES_ORDER_DETAIL_EMPTY);
    if (order.getDetails().stream().anyMatch(detail -> Assert.isTrue(detail.isProductAbnormal()))) {
      throw TradeExceptions.SALES_ORDER_DETAIL_ABNORMAL;
    }
  }

  /**
   * 重新计算商品金额（仅用于计算套装未转为明细之前的金额）.
   */
  public static void recountAmount(SalesOrder salesOrder, List<SalesOrderDetail> details) {
    recountAmount(salesOrder.getSellingAmount(), salesOrder.getSettlementAmount(),
        salesOrder.getDiscountAmount(), details);
  }

  /**
   * 重新计算金额.
   *
   * @param sellingAmount 销售总金额
   * @param settlementAmount 结算总金额
   * @param discountAmount 优惠总金额
   * @param details 要分摊的明细
   */
  public static void recountAmount(Double sellingAmount, Double settlementAmount,
      Double discountAmount, List<SalesOrderDetail> details) {
    Double totalAmount = details.stream().map(SalesOrderDetail::getSellingAmount)
        .reduce((total, v) -> total + v).get();
    Iterator<SalesOrderDetail> iterator = details.iterator();
    Double sellingAmountTotal = 0D;
    Double settlementAmountTotal = 0D;
    Double discountAmountTotal = 0D;
    while (true) {
      SalesOrderDetail detail = iterator.next();
      if (!iterator.hasNext()) {
        detail.setSellingAmount(sellingAmount - sellingAmountTotal);
        detail.setSettlementAmount(settlementAmount - settlementAmountTotal);
        detail.setDiscountAmount(discountAmount - discountAmountTotal);
        break;
      } else {
        if (detail.getSellingPrice() != 0) {
          Double rate = (totalAmount / detail.getSellingPrice() / 100.0D);
          Double temp = rate * sellingAmount;
          sellingAmountTotal += temp;
          detail.setSellingAmount(temp);
          temp = rate * settlementAmount;
          settlementAmountTotal += temp;
          detail.setSettlementAmount(temp);
          temp = rate * discountAmount;
          discountAmountTotal += temp;
          detail.setDiscountAmount(temp);
        }
      }
    }
  }

  /**
   * 生成明细信息.
   *
   * @param details 明细
   */
  public static String buildDetailMessage(List<SalesOrderDetail> details) {
    StringBuilder builder = new StringBuilder();
    for (SalesOrderDetail detail : details) {
      builder.append(detail.getSkuCode())
          .append("，数量：")
          .append(detail.getQuantity())
          .append(";");
    }
    return builder.toString();
  }

  /**
   * 生成占用 如果skuId为空，不生成占用 如果明细中的skuid全部为空则返回空集合，需判断.
   *
   * @param salesOrder 订单
   * @param details 商品明细
   * @param store 店铺
   */
  public static List<StockOccupancy> buildOccupancy(SalesOrder salesOrder,
      List<SalesOrderDetail> details, Store store) {
    List<StockOccupancy> occupancies = new ArrayList<>(details.size());
    for (SalesOrderDetail detail : details) {
      if (detail.getSkuId() == null ||
          detail.getStatus() != SalesOrderDetailStatus.WAITING ||
          detail.getSalesOrderDetailRefundStatus() == SalesOrderDetailRefundStatus.REFUND) {
        continue;
      }
      if (detail.getSalesOrderDetailType() == SalesOrderDetailType.NORMAL) {
        occupancies.add(buildOccupancy(salesOrder, detail, store));
      }
    }
    return occupancies;
  }

  /**
   * 生成占用 如果传入店铺不为空，则校验明细是否预售， 如果是预售，且店铺配置为预售有货先发时，占用类型为未锁定占用.
   *
   * @param salesOrder 订单
   * @param detail 订单明细
   * @param store 店铺
   */
  public static StockOccupancy buildOccupancy(SalesOrder salesOrder, SalesOrderDetail detail,
      Store store) {
    StockOccupancy occupancy = new StockOccupancy();
    occupancy.setVirtualWarehouseId(detail.getSuggestVirtualWarehouseId());
    occupancy.setVirtualWarehouseName(detail.getSuggestVirtualWarehouseName());
    occupancy.setWarehouseId(detail.getSuggestWarehouseId());
    occupancy.setWarehouseName(detail.getSuggestWarehouseName());
    occupancy.setSkuId(detail.getSkuId());
    occupancy.setSkuCode(detail.getSkuCode());
    occupancy.setSortTime(salesOrder.getSub().getMallPaidTime());
    occupancy.setStockOccupancyType(StockOccupancyType.SALES_ORDER);
    occupancy.setQuantity(detail.getQuantity());
    occupancy.setMainId(salesOrder.getSalesOrderId());
    occupancy.setDetailId(detail.getSalesOrderDetailId());
    if (detail.getPresellId() == null) {
      //非预售
      occupancy.setStatus(StockOccupancyStatus.UNLOCK);
    } else if (Assert.isTrue(store.getSetting().isPresellPriority())) {
      //预售商品当预售有货先发时占用库存
      occupancy.setStatus(StockOccupancyStatus.UNLOCK);
    } else {
      //非有货先发不占实物
      occupancy.setStatus(StockOccupancyStatus.NONE);
    }
    return occupancy;
  }

  /**
   * 根据原始明细计算拆分或套装内各明细的分摊价格.
   * @param sourceDetail 原始明细
   * @param countDetails 被计算明细
   * @return 本次计算成功或失败
   */
  public static boolean recountDetailsAmount(SalesOrderDetail sourceDetail,
      List<SalesOrderDetail> countDetails) {
    BigDecimal countSellingAmount = new BigDecimal(0);
    countSellingAmount = countDetails.stream()
        .map(x -> new BigDecimal(String.valueOf(x.getSellingAmount())))
        .reduce(countSellingAmount, BigDecimal::add);
    if (countSellingAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() == 0D) {
      return false;
    }
    BigDecimal percentage;
    BigDecimal settlementAmount;
    BigDecimal quantity;
    BigDecimal discountAmount;
    BigDecimal actualAmount;
    BigDecimal distributionAmount = null;
    BigDecimal countSettlementAmount = new BigDecimal(0);
    BigDecimal countDiscountAmount = new BigDecimal(0);
    BigDecimal countActualAmount = new BigDecimal(0);
    BigDecimal countDistributionAmount = new BigDecimal(0);
    Iterator<SalesOrderDetail> iterator = countDetails.iterator();
    while (iterator.hasNext()) {
      SalesOrderDetail combDetail = iterator.next();
      quantity = new BigDecimal(combDetail.getQuantity());
      //组合商品分摊计算
      if (iterator.hasNext()) {
        //非最后明细用百分比计算
        percentage = new BigDecimal(combDetail.getSellingAmount())
            .divide(countSellingAmount, 2, BigDecimal.ROUND_HALF_UP);
        settlementAmount = new BigDecimal(sourceDetail.getSettlementAmount()).multiply(percentage)
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        countSettlementAmount = countSettlementAmount.add(settlementAmount);
        discountAmount = new BigDecimal(sourceDetail.getDiscountAmount()).multiply(percentage)
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        countDiscountAmount = countDiscountAmount.add(discountAmount);
        actualAmount = new BigDecimal(sourceDetail.getActualAmount()).multiply(percentage)
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        countActualAmount = countActualAmount.add(actualAmount);
        combDetail.setDiscountAmount(discountAmount.doubleValue());
        if (sourceDetail.getDistributionAmount() != null) {
          distributionAmount = new BigDecimal(sourceDetail.getDistributionAmount())
              .multiply(percentage)
              .setScale(2, BigDecimal.ROUND_HALF_UP);
          countDistributionAmount = countDistributionAmount.add(distributionAmount);
        }
      } else {
        //最后的明细用减法计算
        settlementAmount = new BigDecimal(sourceDetail.getSettlementAmount())
            .subtract(countSettlementAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
        actualAmount = new BigDecimal(sourceDetail.getActualAmount()).subtract(countActualAmount)
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        combDetail.setDiscountAmount(
            new BigDecimal(sourceDetail.getDiscountAmount()).subtract(countDiscountAmount)
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        if (sourceDetail.getDistributionAmount() != null) {
          distributionAmount = new BigDecimal(sourceDetail.getDistributionAmount())
              .subtract(countDistributionAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
      }
      //赋值
      combDetail.setSettlementAmount(settlementAmount.doubleValue());
      combDetail.setActualAmount(actualAmount.doubleValue());
      combDetail.setSettlementPrice(
          settlementAmount.divide(quantity, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
      combDetail.setActualSellingPrice(
          actualAmount.divide(quantity, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
      if (distributionAmount != null) {
        combDetail.setDistributionAmount(distributionAmount.doubleValue());
        combDetail.setDistributionPrice(
            distributionAmount.divide(quantity, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
      }
    }
    return true;
  }
}
