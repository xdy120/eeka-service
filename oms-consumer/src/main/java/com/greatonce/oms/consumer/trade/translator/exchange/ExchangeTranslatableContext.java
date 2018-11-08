package com.greatonce.oms.consumer.trade.translator.exchange;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bo.mall.MallExchangeOrderInfo;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;

/**
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/30
 */
public class ExchangeTranslatableContext {

  private final long serialNumber;
  private final MallExchangeOrder mallExchangeOrder;
  private final MallExchangeOrderInfo mallExchangeOrderInfo;
  private ProductMallMapping outProductMapping;
  private ProductMallMapping inProductMappping;
  private ExchangeApplyOrder exchangeApplyOrder;
  private Store store;
  private SalesOrder salesOrder;
  private SalesOrderDetail salesOrderDetail;

  public ExchangeTranslatableContext(Long serialNumber, MallExchangeOrder mallExchangeOrder) {
    this.serialNumber = serialNumber;
    this.mallExchangeOrder = mallExchangeOrder;
    this.mallExchangeOrderInfo = JsonUtil
        .toObject(mallExchangeOrder.getOrderJson(), MallExchangeOrderInfo.class);

  }

  public long getSerialNumber() {
    return serialNumber;
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

  public ProductMallMapping getInProductMappping() {
    return inProductMappping;
  }

  public void setInProductMappping(ProductMallMapping inProductMappping) {
    this.inProductMappping = inProductMappping;
  }

  public MallExchangeOrder getMallExchangeOrder() {
    return mallExchangeOrder;
  }

  public MallExchangeOrderInfo getMallExchangeOrderInfo() {
    return mallExchangeOrderInfo;
  }

  public ExchangeApplyOrder getExchangeApplyOrder() {
    return exchangeApplyOrder;
  }

  public void setExchangeApplyOrder(ExchangeApplyOrder exchangeApplyOrder) {
    this.exchangeApplyOrder = exchangeApplyOrder;
  }
  public ProductMallMapping getOutProductMapping() {
    return outProductMapping;
  }

  public void setOutProductMapping(ProductMallMapping outProductMapping) {
    this.outProductMapping = outProductMapping;
  }

  public SalesOrderDetail getSalesOrderDetail() {
    return salesOrderDetail;
  }

  public void setSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
    this.salesOrderDetail = salesOrderDetail;
  }

  @Override
  public String toString() {
    return "RefundTranslatableContext{" + "serialNumber=" + serialNumber
        + ", tradeId=" + exchangeApplyOrder.getTradeId()
        + ", exchangeId=" + exchangeApplyOrder.getMallExchangeId()
        + '}';
  }
}
