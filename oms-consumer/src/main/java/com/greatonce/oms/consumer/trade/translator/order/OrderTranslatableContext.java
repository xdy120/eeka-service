package com.greatonce.oms.consumer.trade.translator.order;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.setting.OrderAuditStrategy;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单转化上下文.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/12
 */
public class OrderTranslatableContext {

  private final long serialNo;
  private final MallSalesOrder mallSalesOrder;
  private final MallSalesOrderInfo mallSalesOrderInfo;
  private final List<String> logs = new ArrayList<>();
  private final Map<String, Object> dataMap = new HashMap<>(6);
  private final List<SalesOrderDetail> preSellDetails = new ArrayList<>(6);
  private final List<SalesOrderDetail> virtualDetails = new ArrayList<>(6);
  private final boolean needDelivery;
  private boolean passAudited;
  /**
   * 付尾款预付款订单标记.
   */
  private boolean prepayFinalPaid;
  private SalesOrder salesOrder;
  private OrderTranslatableMode mode;
  private Store store;
  private SalesOrderSetting salesOrderSetting;
  private OrderAuditStrategy orderAuditStrategy;
  private StockDispatchStrategy stockDispatchStrategy;
  private SalesOrder marketingCheckOrder;
  private Map<Long, ProductSku> skuMap;

  public OrderTranslatableContext(long serialNumber, MallSalesOrder mallSalesOrder) {
    this.mode = OrderTranslatableMode.CREATE;
    this.serialNo = serialNumber;
    this.mallSalesOrder = mallSalesOrder;
    this.mallSalesOrderInfo = JsonUtil
        .toObject(mallSalesOrder.getOrderJson(), MallSalesOrderInfo.class);
    this.mallSalesOrder.setStatus(MallDataProcessStatus.SUCCESS);
    this.needDelivery = setNeedDelivery();
    this.passAudited = true;
  }

  public long getSerialNo() {
    return serialNo;
  }

  public MallSalesOrder getMallSalesOrder() {
    return mallSalesOrder;
  }

  public MallSalesOrderInfo getMallSalesOrderInfo() {
    return mallSalesOrderInfo;
  }

  public List<String> getLogs() {
    return logs;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    this.salesOrder = salesOrder;
  }

  public OrderTranslatableMode getMode() {
    return mode;
  }

  public void setMode(OrderTranslatableMode mode) {
    this.mode = mode;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public SalesOrderSetting getSalesOrderSetting() {
    return salesOrderSetting;
  }

  public void setSalesOrderSetting(SalesOrderSetting salesOrderSetting) {
    this.salesOrderSetting = salesOrderSetting;
  }

  public OrderAuditStrategy getOrderAuditStrategy() {
    return orderAuditStrategy;
  }

  public void setOrderAuditStrategy(OrderAuditStrategy orderAuditStrategy) {
    this.orderAuditStrategy = orderAuditStrategy;
  }

  public StockDispatchStrategy getStockDispatchStrategy() {
    return stockDispatchStrategy;
  }

  public void setStockDispatchStrategy(StockDispatchStrategy stockDispatchStrategy) {
    this.stockDispatchStrategy = stockDispatchStrategy;
  }

  public void setData(String key, Object value) {
    dataMap.put(key, value);
  }

  public List<SalesOrderDetail> getPreSellDetails() {
    return preSellDetails;
  }

  public List<SalesOrderDetail> getVirtualDetails() {
    return virtualDetails;
  }

  /**
   * 获取数据.
   */
  @SuppressWarnings("unchecked")
  public <T> T getData(String key) {
    if (dataMap.containsKey(key)) {
      return (T) dataMap.get(key);
    }
    return null;
  }

  @Override
  public String toString() {
    return "OrderTranslatableContext{" + "serialNumber=" + serialNo
        + ", tradeId=" + mallSalesOrder.getTradeId()
        + '}';
  }

  /**
   * 判断此订单是否需要发货.
   */
  private boolean setNeedDelivery() {
    if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.WAIT_BUYER_PAY) {
      return mallSalesOrderInfo.getStepTradeStatus() != null || mallSalesOrderInfo.isCod();
    } else if (mallSalesOrderInfo.getStatus() == MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS) {
      return true;
    } else {
      return mallSalesOrderInfo.getDetails().stream().anyMatch(x ->
          mallSalesOrderInfo.isCod() || mallSalesOrderInfo.getStepTradeStatus() != null || (
              !x.isDeliveried() && !x.isShopDelivery()
                  && x.getRefundStatus() == MallRefundStatus.NO_REFUND
                  && x.getStatus() == MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS));
    }
  }

  public boolean isNeedDelivery() {
    return needDelivery;
  }

  public SalesOrder getMarketingCheckOrder() {
    return marketingCheckOrder;
  }

  public void setMarketingCheckOrder(SalesOrder marketingCheckOrder) {
    this.marketingCheckOrder = marketingCheckOrder;
  }

  public boolean isPassAudited() {
    return passAudited;
  }

  public void setPassAudited(boolean passAudited) {
    this.passAudited = passAudited;
  }

  public Map<Long, ProductSku> getSkuMap() {
    return skuMap;
  }

  public void setSkuMap(Map<Long, ProductSku> skuMap) {
    this.skuMap = skuMap;
  }

  public boolean isPrepayFinalPaid() {
    return prepayFinalPaid;
  }

  public void setPrepayFinalPaid(boolean prepayFinalPaid) {
    this.prepayFinalPaid = prepayFinalPaid;
  }
}
