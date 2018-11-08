package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.mall.MallExchangeStatus;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/9/13
 */
public class ExchangeOccupancyBO {

  private Long orderId;

  private LocalDateTime createdTime;

  private Integer quantity;

  private String code;

  private ReturnOrderStatus returnOrderStatus;

  private MallExchangeStatus mallExchangeStatus;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ReturnOrderStatus getReturnOrderStatus() {
    return returnOrderStatus;
  }

  public void setReturnOrderStatus(
      ReturnOrderStatus returnOrderStatus) {
    this.returnOrderStatus = returnOrderStatus;
  }

  public MallExchangeStatus getMallExchangeStatus() {
    return mallExchangeStatus;
  }

  public void setMallExchangeStatus(
      MallExchangeStatus mallExchangeStatus) {
    this.mallExchangeStatus = mallExchangeStatus;
  }
}
