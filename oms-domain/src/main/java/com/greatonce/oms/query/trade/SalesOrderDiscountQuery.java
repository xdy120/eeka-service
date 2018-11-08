package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.trade.DiscountType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售订单优惠明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SalesOrderDiscountQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 优惠金额.
   */
  private Double discountAmount;
  /**
   * 优惠名称.
   */
  private String discountName;
  /**
   * 优惠类型.
   */
  private DiscountType discountType;
  /**
   * 平台支付时间开始.
   */
  private LocalDateTime mallPaidTimeBegin;
  /**
   * 平台支付时间结束.
   */
  private LocalDateTime mallPaidTimeEnd;
  /**
   * 会员卡号.
   */
  private String memberCardNo;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 订单优惠id.
   */
  private Long salesOrderDiscountId;
  /**
   * 销售单id.
   */
  private Long salesOrderId;


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
   * 优惠金额.
   * @param discountAmount 优惠金额
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   * @return 优惠金额
   */
  public Double getDiscountAmount() {
      return this.discountAmount;
  }

  /**
   * 优惠名称.
   * @param discountName 优惠名称
   */
  public void setDiscountName(String discountName) {
    this.discountName = discountName == null ? null : discountName.trim();
  }

  /**
   * 优惠名称.
   * @return 优惠名称
   */
  public String getDiscountName() {
      return this.discountName;
  }

  /**
   * 优惠类型.
   * @param discountType 优惠类型
   */
  public void setDiscountType(DiscountType discountType) {
    this.discountType = discountType;
  }

  /**
   * 优惠类型.
   * @return 优惠类型
   */
  public DiscountType getDiscountType() {
      return this.discountType;
  }

  /**
   * 平台支付时间开始.
   * @param mallPaidTimeBegin 开始.
   */
  public void setMallPaidTimeBegin(LocalDateTime mallPaidTimeBegin) {
    this.mallPaidTimeBegin = mallPaidTimeBegin;
  }

  /**
   * 平台支付时间开始.
   * @return 平台支付时间开始
   */
  public LocalDateTime getMallPaidTimeBegin() {
    return this.mallPaidTimeBegin;
  }

  /**
   * 平台支付时间结束.
   * @param mallPaidTimeEnd 结束
   */
  public void setMallPaidTimeEnd(LocalDateTime mallPaidTimeEnd) {
    this.mallPaidTimeEnd = mallPaidTimeEnd;
  }

  /**
   * 平台支付时间结束.
   * @return 平台支付时间结束
   */
  public LocalDateTime getMallPaidTimeEnd() {
    return this.mallPaidTimeEnd;
  }

  /**
   * 会员卡号.
   * @param memberCardNo 会员卡号
   */
  public void setMemberCardNo(String memberCardNo) {
    this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
  }

  /**
   * 会员卡号.
   * @return 会员卡号
   */
  public String getMemberCardNo() {
      return this.memberCardNo;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 订单优惠id.
   * @param salesOrderDiscountId 订单优惠id
   */
  public void setSalesOrderDiscountId(Long salesOrderDiscountId) {
    this.salesOrderDiscountId = salesOrderDiscountId;
  }

  /**
   * 订单优惠id.
   * @return 订单优惠id
   */
  public Long getSalesOrderDiscountId() {
      return this.salesOrderDiscountId;
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
}