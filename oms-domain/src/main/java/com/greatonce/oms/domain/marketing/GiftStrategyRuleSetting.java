package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.enums.marketing.GiftStrategyDoublyType;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyGiftType;
import com.greatonce.oms.domain.enums.marketing.ProductRange;
import java.io.Serializable;

/**
 * 赠品策略规则.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/13
 */
public class GiftStrategyRuleSetting implements Serializable {

  /**
   * 活动商品范围.
   */
  private ProductRange productRange;
  /**
   * 赠品类型.
   */
  private GiftStrategyGiftType giftType;
  /**
   * 赠送数量.
   */
  private Integer giveQuantity;
  /**
   * 最低金额.
   */
  private Double minAmount;
  /**
   * 最低款数.
   */
  private Integer minProductQuantity;
  /**
   * 最低件数.
   */
  private Integer minQuantity;
  /**
   * 预警手机.
   */
  private String alertMobile;
  /**
   * 翻倍规则.
   */
  private GiftStrategyDoublyType doublyType;
  /**
   * 是否翻倍.
   */
  private Boolean doubly;
  /**
   * 翻倍额.
   */
  private Double doublyNumber;
  /**
   * 最大倍数.
   */
  private Integer maxDoubly;
  /**
   * 福袋赠送款数.
   */
  private Integer luckBagGiveProductQuantity;
  /**
   * 预售订单.
   */
  private Boolean presell;
  /**
   * 预付订单.
   */
  private Boolean prepay;
  /**
   * 预付首付.
   */
  private Boolean prepayFirst;
  /**
   * 到付订单.
   */
  private Boolean cod;
  /**
   * 匹配尺码.
   */
  private Boolean matchSize;

  private Long expressId;
  private String expressName;

  public ProductRange getProductRange() {
    return productRange;
  }

  public void setProductRange(ProductRange productRange) {
    this.productRange = productRange;
  }

  public GiftStrategyGiftType getGiftType() {
    return giftType;
  }

  public void setGiftType(GiftStrategyGiftType giftType) {
    this.giftType = giftType;
  }

  public Integer getGiveQuantity() {
    return giveQuantity;
  }

  public void setGiveQuantity(Integer giveQuantity) {
    this.giveQuantity = giveQuantity;
  }

  public Double getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(Double minAmount) {
    this.minAmount = minAmount;
  }

  public Integer getMinProductQuantity() {
    return minProductQuantity;
  }

  public void setMinProductQuantity(Integer minProductQuantity) {
    this.minProductQuantity = minProductQuantity;
  }

  public Integer getMinQuantity() {
    return minQuantity;
  }

  public void setMinQuantity(Integer minQuantity) {
    this.minQuantity = minQuantity;
  }

  public String getAlertMobile() {
    return alertMobile;
  }

  public void setAlertMobile(String alertMobile) {
    this.alertMobile = alertMobile;
  }

  public GiftStrategyDoublyType getDoublyType() {
    return doublyType;
  }

  public void setDoublyType(GiftStrategyDoublyType doublyType) {
    this.doublyType = doublyType;
  }

  public Boolean isDoubly() {
    return doubly;
  }

  public void setDoubly(Boolean doubly) {
    this.doubly = doubly;
  }

  public Double getDoublyNumber() {
    return doublyNumber;
  }

  public void setDoublyNumber(Double doublyNumber) {
    this.doublyNumber = doublyNumber;
  }

  public Integer getMaxDoubly() {
    return maxDoubly;
  }

  public void setMaxDoubly(Integer maxDoubly) {
    this.maxDoubly = maxDoubly;
  }

  public Integer getLuckBagGiveProductQuantity() {
    return luckBagGiveProductQuantity;
  }

  public void setLuckBagGiveProductQuantity(Integer luckBagGiveProductQuantity) {
    this.luckBagGiveProductQuantity = luckBagGiveProductQuantity;
  }

  public Boolean isPresell() {
    return presell;
  }

  public void setPresell(Boolean presell) {
    this.presell = presell;
  }

  public Boolean isPrepay() {
    return prepay;
  }

  public void setPrepay(Boolean prepay) {
    this.prepay = prepay;
  }

  public Boolean isPrepayFirst() {
    return prepayFirst;
  }

  public void setPrepayFirst(Boolean prepayFirst) {
    this.prepayFirst = prepayFirst;
  }

  public Boolean isCod() {
    return cod;
  }

  public void setCod(Boolean cod) {
    this.cod = cod;
  }

  public Boolean isMatchSize() {
    return matchSize;
  }

  public void setMatchSize(Boolean matchSize) {
    this.matchSize = matchSize;
  }

  public Long getExpressId() {
    return expressId;
  }

  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }
}
