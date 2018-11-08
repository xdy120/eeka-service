package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
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
public class SalesOrderDiscount extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 平台支付时间.
   */
  private LocalDateTime mallPaidTime;
  /**
   * 会员卡号.
   */
  private String memberCardNo;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 订单优惠id.
   */
  private Long salesOrderDiscountId;
  /**
   * 销售单id.
   */
  private Long salesOrderId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.salesOrderDiscountId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.salesOrderDiscountId;
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
   * 优惠金额.
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   */
  public Double getDiscountAmount() {
    return this.discountAmount;
  }

  /**
   * 优惠名称.
   */
  public void setDiscountName(String discountName) {
    this.discountName = discountName == null ? null : discountName.trim();
  }

  /**
   * 优惠名称.
   */
  public String getDiscountName() {
    return this.discountName;
  }

  /**
   * 优惠类型.
   */
  public void setDiscountType(DiscountType discountType) {
    this.discountType = discountType;
  }

  /**
   * 优惠类型.
   */
  public DiscountType getDiscountType() {
    return this.discountType;
  }

  /**
   * 平台支付时间.
   */
  public void setMallPaidTime(LocalDateTime mallPaidTime) {
    this.mallPaidTime = mallPaidTime;
  }

  /**
   * 平台支付时间.
   */
  public LocalDateTime getMallPaidTime() {
    return this.mallPaidTime;
  }

  /**
   * 会员卡号.
   */
  public void setMemberCardNo(String memberCardNo) {
    this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
  }

  /**
   * 会员卡号.
   */
  public String getMemberCardNo() {
    return this.memberCardNo;
  }

  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 订单优惠id.
   */
  public void setSalesOrderDiscountId(Long salesOrderDiscountId) {
    this.salesOrderDiscountId = salesOrderDiscountId;
  }

  /**
   * 订单优惠id.
   */
  public Long getSalesOrderDiscountId() {
    return this.salesOrderDiscountId;
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
}