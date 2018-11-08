package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.DispatchOrder;

/**
 * 电子面单获取请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
public class WaybillGetRequest extends MallRequest {

  /**
   * 全局快递.
   */
  private String globalExpressCode;
  /**
   * 配货单.
   */
  private DispatchOrder dispatchOrder;
  /**
   * 平台交易号
   */
  private String tradeId;
  /**
   * 发货快递
   */
  private Express express;
  /**
   * 订单店铺
   */
  private Store orderStore;

  /**
   * 构造函数.
   */
  public WaybillGetRequest(Store store) {
    super(store);
  }

  public DispatchOrder getDispatchOrder() {
    return dispatchOrder;
  }

  public void setDispatchOrder(DispatchOrder dispatchOrder) {
    this.dispatchOrder = dispatchOrder;
  }

  public String getGlobalExpressCode() {
    return globalExpressCode;
  }

  public void setGlobalExpressCode(String globalExpressCode) {
    this.globalExpressCode = globalExpressCode;
  }

  public Express getExpress() {
    return express;
  }

  public void setExpress(Express express) {
    this.express = express;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public Store getOrderStore() {
    return orderStore;
  }

  public void setOrderStore(Store orderStore) {
    this.orderStore = orderStore;
  }
}
