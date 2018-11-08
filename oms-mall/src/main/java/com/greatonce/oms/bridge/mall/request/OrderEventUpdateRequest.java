package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;

/**
 * 订单事件通知.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-05
 */
public class OrderEventUpdateRequest extends MallRequest {

  public OrderEventUpdateRequest(Store store) {
    super(store);
  }

  private String tradeId;
  private Long createTime;
  private OrderEvent orderEvent;

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }


  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public OrderEvent getOrderEvent() {
    return orderEvent;
  }

  public void setOrderEvent(
      OrderEvent orderEvent) {
    this.orderEvent = orderEvent;
  }

  public enum OrderEvent {
    DOWNLOAD,
    AUDIT,
    DISPATCH,
    DELIVERY
  }
}
