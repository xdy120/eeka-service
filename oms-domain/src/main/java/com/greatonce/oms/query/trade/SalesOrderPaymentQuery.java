package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class SalesOrderPaymentQuery extends Query {
  /**
   * 支付金额.
   */
  private Double actualAmount;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 更新时间2开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间2结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime paidTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime paidTimeEnd;
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


  /**
   * 支付金额.
   * @param actualAmount 支付金额
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 支付金额.
   * @return 支付金额
   */
  public Double getActualAmount() {
      return this.actualAmount;
  }

  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 更新时间2开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间2开始.
   * @return 更新时间2开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间2结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间2结束.
   * @return 更新时间2结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 更新时间开始.
   * @param paidTimeBegin 开始.
   */
  public void setPaidTimeBegin(LocalDateTime paidTimeBegin) {
    this.paidTimeBegin = paidTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getPaidTimeBegin() {
    return this.paidTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param paidTimeEnd 结束
   */
  public void setPaidTimeEnd(LocalDateTime paidTimeEnd) {
    this.paidTimeEnd = paidTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getPaidTimeEnd() {
    return this.paidTimeEnd;
  }

  /**
   * 支付方式.
   * @param payType 支付方式
   */
  public void setPayType(PayType payType) {
    this.payType = payType;
  }

  /**
   * 支付方式.
   * @return 支付方式
   */
  public PayType getPayType() {
      return this.payType;
  }

  /**
   * 销售单id.
   * @param salesOrderId 销售单id
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   * @return 销售单id
   */
  public Long getSalesOrderId() {
      return this.salesOrderId;
  }

  /**
   * 销售单支付id.
   * @param salesOrderPaymentId 销售单支付id
   */
  public void setSalesOrderPaymentId(Long salesOrderPaymentId) {
    this.salesOrderPaymentId = salesOrderPaymentId;
  }

  /**
   * 销售单支付id.
   * @return 销售单支付id
   */
  public Long getSalesOrderPaymentId() {
      return this.salesOrderPaymentId;
  }
}