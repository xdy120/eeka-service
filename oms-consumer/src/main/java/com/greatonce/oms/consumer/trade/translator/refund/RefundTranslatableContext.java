package com.greatonce.oms.consumer.trade.translator.refund;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.mall.MallRefundOrder;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.List;

/**
 * RefundTranslatableContext
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/30
 */
public class RefundTranslatableContext {

  private final long serialNumber;
  private final MallRefundOrder mallRefundOrder;
  private final MallRefundOrderInfo mallRefundOrderInfo;
  private RefundApplyOrder applyRefundOrder;
  private Store store;
  private SalesOrder salesOrder;
  private List<SalesOrderDetail> salesOrderDetails;
  private List<DispatchOrder> dispatchOrders;

  public RefundTranslatableContext(Long serialNumber, MallRefundOrder mallRefundOrder) {
    this.serialNumber = serialNumber;
    this.mallRefundOrder = mallRefundOrder;
    this.mallRefundOrderInfo = JsonUtil
        .toObject(mallRefundOrder.getOrderJson(), MallRefundOrderInfo.class);
  }

  public long getSerialNumber() {
    return serialNumber;
  }

  public MallRefundOrder getMallRefundOrder() {
    return mallRefundOrder;
  }

  public MallRefundOrderInfo getMallRefundOrderInfo() {
    return mallRefundOrderInfo;
  }

  public RefundApplyOrder getApplyRefundOrder() {
    return applyRefundOrder;
  }

  public void setApplyRefundOrder(RefundApplyOrder applyRefundOrder) {
    this.applyRefundOrder = applyRefundOrder;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    this.salesOrder = salesOrder;
  }

  public List<SalesOrderDetail> getSalesOrderDetails() {
    return salesOrderDetails;
  }

  public void setSalesOrderDetails(List<SalesOrderDetail> salesOrderDetails) {
    this.salesOrderDetails = salesOrderDetails;
  }

  public List<DispatchOrder> getDispatchOrders() {
    return dispatchOrders;
  }

  public void setDispatchOrders(List<DispatchOrder> dispatchOrders) {
    this.dispatchOrders = dispatchOrders;
  }

  @Override
  public String toString() {
    return "RefundTranslatableContext{" + "serialNumber=" + serialNumber
        + ", tradeId=" + mallRefundOrder.getTradeId()
        + ", refundId=" + mallRefundOrder.getMallRefundId()
        + '}';
  }
}
