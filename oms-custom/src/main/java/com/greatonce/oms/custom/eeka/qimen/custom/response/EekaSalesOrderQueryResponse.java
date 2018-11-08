package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.ReturnSign;
import com.greatonce.oms.domain.trade.SalesOrder;
import java.util.List;

public class EekaSalesOrderQueryResponse extends OmsQimenCustomResponse {

  public EekaSalesOrderQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaSalesOrderQueryResponse(Long requestId,String message) {
    super(requestId,message);
  }

  private List<RefundApplyOrder> refundApplyOrders;

  private List<ExchangeApplyOrder> exchangeApplyOrders;

  private List<SalesOrder> salesOrders;

  private Store store;

  private List<ReturnSign> expressSign;

  private boolean resultFlag;

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

  public boolean isResultFlag() {
    return resultFlag;
  }

  public void setResultFlag(boolean resultFlag) {
    this.resultFlag = resultFlag;
  }
}
