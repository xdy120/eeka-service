package com.greatonce.oms.consumer.trade.dispatch.wrapper;

import com.greatonce.oms.domain.enums.trade.DispatchOrderDetailStatus;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/28
 */
public class DispatchOrderDetailWrapper extends DispatchOrderDetail {

  private final DispatchOrderDetail detail;
  private final SalesOrder salesOrder;
  private final SalesOrderDetail salesOrderDetail;
  /**
   * 是否缺货
   */
  private boolean oos;
  /**
   * 是否主订单的明细
   */
  private boolean isMainOrderDetail;

  public DispatchOrderDetailWrapper(DispatchOrderDetail detail, SalesOrder salesOrder,
      SalesOrderDetail salesOrderDetail, boolean isMainOrderDetail) {
    this.detail = detail;
    this.salesOrder = salesOrder;
    this.salesOrderDetail = salesOrderDetail;
    this.isMainOrderDetail = isMainOrderDetail;
  }

  public boolean isOos() {
    return oos;
  }

  public void setOos(boolean oos) {
    this.oos = oos;
  }

  @Override
  public void setPrimaryKey(Long pk) {
    detail.setPrimaryKey(pk);
  }

  @Override
  public Long getPrimaryKey() {
    return detail.getPrimaryKey();
  }

  @Override
  public void setActualAmount(Double actualAmount) {
    detail.setActualAmount(actualAmount);
  }

  @Override
  public Double getActualAmount() {
    return detail.getActualAmount();
  }

  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    detail.setCreatedTime(createdTime);
  }

  @Override
  public LocalDateTime getCreatedTime() {
    return detail.getCreatedTime();
  }

  @Override
  public void setDiscountAmount(Double discountAmount) {
    detail.setDiscountAmount(discountAmount);
  }

  @Override
  public Double getDiscountAmount() {
    return detail.getDiscountAmount();
  }

  @Override
  public void setDispatchOrderDetailId(Long dispatchOrderDetailId) {
    detail.setDispatchOrderDetailId(dispatchOrderDetailId);
  }

  @Override
  public Long getDispatchOrderDetailId() {
    return detail.getDispatchOrderDetailId();
  }

  @Override
  public void setDispatchOrderId(Long dispatchOrderId) {
    detail.setDispatchOrderId(dispatchOrderId);
  }

  @Override
  public Long getDispatchOrderId() {
    return detail.getDispatchOrderId();
  }

  @Override
  public void setDistributionAmount(Double distributionAmount) {
    detail.setDistributionAmount(distributionAmount);
  }

  @Override
  public Double getDistributionAmount() {
    return detail.getDistributionAmount();
  }

  @Override
  public void setDistributionPrice(Double distributionPrice) {
    detail.setDistributionPrice(distributionPrice);
  }

  @Override
  public Double getDistributionPrice() {
    return detail.getDistributionPrice();
  }

  @Override
  public void setHasInvoice(Boolean hasInvoice) {
    detail.setHasInvoice(hasInvoice);
  }

  @Override
  public Boolean isHasInvoice() {
    return detail.isHasInvoice();
  }

  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    detail.setModifiedTime(modifiedTime);
  }

  @Override
  public LocalDateTime getModifiedTime() {
    return detail.getModifiedTime();
  }

  @Override
  public void setNoticeQuantity(Integer noticeQuantity) {
    detail.setNoticeQuantity(noticeQuantity);
  }

  @Override
  public Integer getNoticeQuantity() {
    return detail.getNoticeQuantity();
  }

  @Override
  public void setOutQuantity(Integer outQuantity) {
    detail.setOutQuantity(outQuantity);
  }

  @Override
  public Integer getOutQuantity() {
    return detail.getOutQuantity();
  }

  @Override
  public void setProductCode(String productCode) {
    detail.setProductCode(productCode);
  }

  @Override
  public String getProductCode() {
    return detail.getProductCode();
  }

  @Override
  public void setProductId(Long productId) {
    detail.setProductId(productId);
  }

  @Override
  public Long getProductId() {
    return detail.getProductId();
  }

  @Override
  public void setProductName(String productName) {
    detail.setProductName(productName);
  }

  @Override
  public String getProductName() {
    return detail.getProductName();
  }

  @Override
  public void setSalesOrderCode(String salesOrderCode) {
    detail.setSalesOrderCode(salesOrderCode);
  }

  @Override
  public String getSalesOrderCode() {
    return detail.getSalesOrderCode();
  }

  @Override
  public void setSalesOrderDetailId(Long salesOrderDetailId) {
    detail.setSalesOrderDetailId(salesOrderDetailId);
  }

  @Override
  public Long getSalesOrderDetailId() {
    return detail.getSalesOrderDetailId();
  }

  @Override
  public void setSalesOrderId(Long salesOrderId) {
    detail.setSalesOrderId(salesOrderId);
  }

  @Override
  public Long getSalesOrderId() {
    return detail.getSalesOrderId();
  }

  @Override
  public void setSellingAmount(Double sellingAmount) {
    detail.setSellingAmount(sellingAmount);
  }

  @Override
  public Double getSellingAmount() {
    return detail.getSellingAmount();
  }

  @Override
  public void setSellingPrice(Double sellingPrice) {
    detail.setSellingPrice(sellingPrice);
  }

  @Override
  public Double getSellingPrice() {
    return detail.getSellingPrice();
  }

  @Override
  public void setSettlementAmount(Double settlementAmount) {
    detail.setSettlementAmount(settlementAmount);
  }

  @Override
  public Double getSettlementAmount() {
    return detail.getSettlementAmount();
  }

  @Override
  public void setSettlementPrice(Double settlementPrice) {
    detail.setSettlementPrice(settlementPrice);
  }

  @Override
  public Double getSettlementPrice() {
    return detail.getSettlementPrice();
  }

  @Override
  public void setSkuCode(String skuCode) {
    detail.setSkuCode(skuCode);
  }

  @Override
  public String getSkuCode() {
    return detail.getSkuCode();
  }

  @Override
  public void setSkuId(Long skuId) {
    detail.setSkuId(skuId);
  }

  @Override
  public Long getSkuId() {
    return detail.getSkuId();
  }

  @Override
  public void setSkuName(String skuName) {
    detail.setSkuName(skuName);
  }

  @Override
  public String getSkuName() {
    return detail.getSkuName();
  }

  @Override
  public void setStatus(DispatchOrderDetailStatus status) {
    detail.setStatus(status);
  }

  @Override
  public DispatchOrderDetailStatus getStatus() {
    return detail.getStatus();
  }

  @Override
  public void setTradeId(String tradeId) {
    detail.setTradeId(tradeId);
  }

  @Override
  public String getTradeId() {
    return detail.getTradeId();
  }

  @Override
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    detail.setVirtualWarehouseId(virtualWarehouseId);
  }

  @Override
  public Long getVirtualWarehouseId() {
    return detail.getVirtualWarehouseId();
  }

  @Override
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    detail.setVirtualWarehouseName(virtualWarehouseName);
  }

  @Override
  public String getVirtualWarehouseName() {
    return detail.getVirtualWarehouseName();
  }

  @Override
  public void setDeliveries(List<DispatchOrderDelivery> deliveries) {
    detail.setDeliveries(deliveries);
  }

  @Override
  public List<DispatchOrderDelivery> getDeliveries() {
    return detail.getDeliveries();
  }

  @Override
  public Object clone() {
    return detail.clone();
  }


  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public SalesOrderDetail getSalesOrderDetail() {
    return salesOrderDetail;
  }

  public boolean isMainOrderDetail() {
    return isMainOrderDetail;
  }
}
