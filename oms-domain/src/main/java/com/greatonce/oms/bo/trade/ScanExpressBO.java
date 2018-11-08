package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.ReturnSign;
import com.greatonce.oms.domain.trade.SalesOrder;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-05-08
 * Time: 17:40
 * Description:
 */
public class ScanExpressBO {

  private List<RefundApplyOrder> refundApplyOrders;

  private List<ExchangeApplyOrder> exchangeApplyOrders;

  private List<SalesOrder> salesOrders;

  private Store store;

  private List<ReturnSign> expressSign;

  private List<ImageBO> colorAndImages;

  public List<RefundApplyOrder> getRefundApplyOrders() {
    return refundApplyOrders;
  }

  public void setRefundApplyOrders(List<RefundApplyOrder> refundApplyOrders) {
    this.refundApplyOrders = refundApplyOrders;
  }

  public List<ExchangeApplyOrder> getExchangeApplyOrders() {
    return exchangeApplyOrders;
  }

  public void setExchangeApplyOrders(List<ExchangeApplyOrder> exchangeApplyOrders) {
    this.exchangeApplyOrders = exchangeApplyOrders;
  }

  public List<SalesOrder> getSalesOrders() {
    return salesOrders;
  }

  public void setSalesOrders(List<SalesOrder> salesOrders) {
    this.salesOrders = salesOrders;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public List<ReturnSign> getExpressSign() {
    return expressSign;
  }

  public void setExpressSign(List<ReturnSign> expressSign) {
    this.expressSign = expressSign;
  }

  public ScanExpressBO(List<RefundApplyOrder> refundApplyOrders,
      List<ExchangeApplyOrder> exchangeApplyOrders, List<SalesOrder> salesOrders, Store store,
      List<ImageBO> colorAndImages) {
    this.refundApplyOrders = refundApplyOrders;
    this.exchangeApplyOrders = exchangeApplyOrders;
    this.salesOrders = salesOrders;
    this.store = store;
    this.colorAndImages = colorAndImages;
  }

  public List<ImageBO> getColorAndImages() {
    return colorAndImages;
  }

  public void setColorAndImages(List<ImageBO> colorAndImages) {
    this.colorAndImages = colorAndImages;
  }

  public ScanExpressBO() {
  }
}
