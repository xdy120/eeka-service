package com.greatonce.oms.domain;

import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.RefundType;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.stock.StockTransit;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.domain.trade.RefundOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import java.time.LocalDate;

/**
 * Created by wangc on 2017/11/30.
 */
public final class DomainUtil {

  public static StockTransit createStockTransit(Long skuId, Integer transitQuantity,
      LocalDate arrivalDate) {
    StockTransit transit = new StockTransit();
    transit.setSkuId(skuId);
    transit.setTransitQuantity(transitQuantity);
    transit.setArrivalDate(arrivalDate);
    return transit;
  }

  public static StockOccupancy createStockOccupancy(VirtualWarehouse virtualWarehouse,
      StockOccupancyType occupancyType,
      Long skuId, String skuCode, Integer quantity, Long mainId, Long detailId,
      StockOccupancyStatus status) {
    return createStockOccupancy(virtualWarehouse.getWarehouseId(),
        virtualWarehouse.getWarehouseName(), virtualWarehouse.getVirtualWarehouseId(),
        virtualWarehouse.getVirtualWarehouseName(), occupancyType, skuId, skuCode, quantity,
        mainId, detailId, status);
  }

  public static StockOccupancy createStockOccupancy(Long warehouseId, String warehouseName,
      Long virtualWarehouseId, String virtualWarehouseName, StockOccupancyType occupancyType,
      Long skuId, String skuCode, Integer quantity, Long mainId, Long detailId,
      StockOccupancyStatus status) {
    StockOccupancy occupancy = new StockOccupancy();
    occupancy.setWarehouseId(warehouseId);
    occupancy.setWarehouseName(warehouseName);
    occupancy.setVirtualWarehouseId(virtualWarehouseId);
    occupancy.setVirtualWarehouseName(virtualWarehouseName);
    occupancy.setStockOccupancyType(occupancyType);
    occupancy.setSkuId(skuId);
    occupancy.setSkuCode(skuCode);
    occupancy.setQuantity(quantity);
    occupancy.setMainId(mainId);
    occupancy.setDetailId(detailId);
    occupancy.setStatus(status);
    return occupancy;
  }

  public static Stock createStock(Long warehouseId, String warehouseName, Long virtualWarehouseId,
      String virtualWarehouseName, Long skuId, String skuCode) {
    return createStock(warehouseId, warehouseName, virtualWarehouseId, virtualWarehouseName, skuId,
        skuCode, 0, 0, null);
  }

  public static Stock createStock(Long warehouseId, String warehouseName, Long virtualWarehouseId,
      String virtualWarehouseName, Long skuId, String skuCode, Integer quantity) {
    return createStock(warehouseId, warehouseName, virtualWarehouseId, virtualWarehouseName, skuId,
        skuCode, quantity, 0, null);
  }

  public static Stock createStock(Long warehouseId, String warehouseName, Long virtualWarehouseId,
      String virtualWarehouseName, Long skuId, String skuCode, Integer quantity,
      Integer transitQuantity, String remark) {
    Stock stock = new Stock();
    stock.setWarehouseId(warehouseId);
    stock.setWarehouseName(warehouseName);
    stock.setVirtualWarehouseId(virtualWarehouseId);
    stock.setVirtualWarehouseName(virtualWarehouseName);
    stock.setSkuId(skuId);
    stock.setSkuCode(skuCode);
    stock.setQuantity(quantity);
    stock.setTransitQuantity(transitQuantity);
    stock.setRemark(remark);
    return stock;
  }

  public static PurchaseOrderDetail createPurchaseOrderDetail(Long purchaseOrderDetailId,
      int noticeQuantity) {
    PurchaseOrderDetail detail = new PurchaseOrderDetail();
    detail.setPurchaseOrderDetailId(purchaseOrderDetailId);
    detail.setNoticeQuantity(noticeQuantity);
    return detail;
  }

  public static RefundOrder createRefundOrder(String tradeId, String storeName, Long storeId,
      String refundMethod, Long salesOrderId, String salesOrderCode,
      String returnReasonType, Long returnOrderId, String returnOrderCode, Double refundableAmount,
      RefundType refundType,
      Double actualAmount, Boolean cod, Boolean quickRefund, String payeeName, String payeeNo,
      String payeeOrganization) {
    RefundOrder refundOrder = new RefundOrder();
    refundOrder.setTradeId(tradeId);
    refundOrder.setStoreName(storeName);
    refundOrder.setStoreId(storeId);
    refundOrder.setRefundMethod(refundMethod);
    refundOrder.setSalesOrderId(salesOrderId);
    refundOrder.setSalesOrderCode(salesOrderCode);
    refundOrder.setReturnReasonType(returnReasonType);
    refundOrder.setReturnOrderId(returnOrderId);
    refundOrder.setReturnOrderCode(returnOrderCode);
    refundOrder.setRefundableAmount(refundableAmount);
    refundOrder.setRefundType(refundType);
    refundOrder.setActualAmount(actualAmount);
    refundOrder.setCod(cod);
    refundOrder.setQuickRefund(quickRefund);
    refundOrder.setPayeeName(payeeName);
    refundOrder.setPayeeNo(payeeNo);
    refundOrder.setPayeeOrganization(payeeOrganization);
    return refundOrder;
  }

  public static RefundOrderDetail createRefundOrderDetail(Long returnOrderId,
      Double refundableAmount, Integer quantity, Long productId, String productName,
      String productCode, Long skuId, String skuName, String skuCode) {
    RefundOrderDetail detail = new RefundOrderDetail();
    detail.setReturnOrderId(returnOrderId);
    detail.setActualAmount(0D);
    detail.setRefundableAmount(refundableAmount);
    detail.setQuantity(quantity);
    detail.setProductId(productId);
    detail.setProductCode(productCode);
    detail.setProductName(productName);
    detail.setSkuId(skuId);
    detail.setSkuCode(skuCode);
    detail.setSkuName(skuName);
    return detail;
  }

  public static ReturnOrderOutDetail createReturnOrderOutDetail(Long returnOrderId,
      String exchangeId, Double actualAmount, Integer quantity, Long productId, String productName,
      String productCode, Long skuId, String skuName, String skuCode) {
    ReturnOrderOutDetail detail = new ReturnOrderOutDetail();
    detail.setReturnOrderId(returnOrderId);
    detail.setExchangeId(exchangeId);
    detail.setActualAmount(actualAmount);
    detail.setQuantity(quantity);
    detail.setProductId(productId);
    detail.setProductCode(productCode);
    detail.setProductName(productName);
    detail.setSkuId(skuId);
    detail.setSkuCode(skuCode);
    detail.setSkuName(skuName);
    return detail;
  }
}
