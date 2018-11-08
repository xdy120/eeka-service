package com.greatonce.oms.bo.mall;

import com.greatonce.oms.domain.enums.trade.DiscountType;
import java.time.LocalDateTime;

/**
 * 商城优惠详情
 *
 * @author ginta
 */
public class MallSalesOrderDiscountInfo {

  /**
   * 优惠价格
   */
  private Double amount;
  /**
   * 卡号
   */
  private String cardNo;
  /**
   * 优惠名称
   */
  private String discountName;
  /**
   * 优惠Id
   */
  private String discountId;
  /**
   * 优惠类型
   */
  private DiscountType type;
  /**
   * 平台支付时间.
   */
  private LocalDateTime mallPaidTime;

  public MallSalesOrderDiscountInfo() {
  }

  public MallSalesOrderDiscountInfo(Double amount) {
    this.amount = amount;
  }

  public MallSalesOrderDiscountInfo(Double amount, DiscountType type) {
    this.amount = amount;
    this.type = type;
  }

  public MallSalesOrderDiscountInfo(Double amount, String discountId, DiscountType type) {
    this.amount = amount;
    this.discountId = discountId;
    this.type = type;
  }

  public MallSalesOrderDiscountInfo(Double amount, String cardNo, String discountId,
      DiscountType type) {
    this.amount = amount;
    this.cardNo = cardNo;
    this.discountId = discountId;
    this.type = type;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getDiscountId() {
    return discountId;
  }

  public void setDiscountId(String discountId) {
    this.discountId = discountId;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public DiscountType getType() {
    return type;
  }

  public void setType(DiscountType type) {
    this.type = type;
  }

  public String getDiscountName() {
    return discountName;
  }

  public void setDiscountName(String discountName) {
    this.discountName = discountName;
  }

  public LocalDateTime getMallPaidTime() {
    return mallPaidTime;
  }

  public void setMallPaidTime(LocalDateTime mallPaidTime) {
    this.mallPaidTime = mallPaidTime;
  }
}