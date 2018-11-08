package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.trade.PayType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售订单支付明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SalesOrderPayment extends BaseDO {
  /**
   * 支付金额.
   */
  private Double actualAmount;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间2.
   */
  private LocalDateTime modifiedTime;
  /**
   * 更新时间.
   */
  private LocalDateTime paidTime;
  /**
   * 支付方式.
   */
  private PayType payType;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 销售单支付id.
   */
  private Long salesOrderPaymentId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.salesOrderPaymentId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.salesOrderPaymentId;
  }


  /**
   * 支付金额.
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 支付金额.
   */
  public Double getActualAmount() {
    return this.actualAmount;
  }

  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 更新时间2.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间2.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 更新时间.
   */
  public void setPaidTime(LocalDateTime paidTime) {
    this.paidTime = paidTime;
  }

  /**
   * 更新时间.
   */
  public LocalDateTime getPaidTime() {
    return this.paidTime;
  }

  /**
   * 支付方式.
   */
  public void setPayType(PayType payType) {
    this.payType = payType;
  }

  /**
   * 支付方式.
   */
  public PayType getPayType() {
    return this.payType;
  }

  /**
   * 销售单id.
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   */
  public Long getSalesOrderId() {
    return this.salesOrderId;
  }

  /**
   * 销售单支付id.
   */
  public void setSalesOrderPaymentId(Long salesOrderPaymentId) {
    this.salesOrderPaymentId = salesOrderPaymentId;
  }

  /**
   * 销售单支付id.
   */
  public Long getSalesOrderPaymentId() {
    return this.salesOrderPaymentId;
  }
}