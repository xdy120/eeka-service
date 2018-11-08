package com.greatonce.oms.query.marketing;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;

/**
 * 赠品策略赠品.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GiftStrategyGiftQuery extends Query {
  /**
   * 预警数量.
   */
  private Integer alertQuantity;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 赠品策略赠品id.
   */
  private Long giftStrategyGiftId;
  /**
   * 赠品规则id.
   */
  private Long giftStrategyRuleId;
  /**
   * 是否组合商品.
   */
  private Boolean combination;
  /**
   * 福袋编码.
   */
  private String luckyCode;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 计划赠送数量.
   */
  private Integer planQuantity;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * 商品名称.
   */
  private String productName;
  /**
   * 购买数量.
   */
  private Integer quantity;
  /**
   * 已赠送数量.
   */
  private Integer sentQuantity;
  /**
   * 尺码.
   */
  private String size;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 商品规格名称.
   */
  private String skuName;


  /**
   * 预警数量.
   * @param alertQuantity 预警数量
   */
  public void setAlertQuantity(Integer alertQuantity) {
    this.alertQuantity = alertQuantity;
  }

  /**
   * 预警数量.
   * @return 预警数量
   */
  public Integer getAlertQuantity() {
      return this.alertQuantity;
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
   * 赠品策略赠品id.
   * @param giftStrategyGiftId 赠品策略赠品id
   */
  public void setGiftStrategyGiftId(Long giftStrategyGiftId) {
    this.giftStrategyGiftId = giftStrategyGiftId;
  }

  /**
   * 赠品策略赠品id.
   * @return 赠品策略赠品id
   */
  public Long getGiftStrategyGiftId() {
      return this.giftStrategyGiftId;
  }

  /**
   * 赠品规则id.
   * @param giftStrategyRuleId 赠品规则id
   */
  public void setGiftStrategyRuleId(Long giftStrategyRuleId) {
    this.giftStrategyRuleId = giftStrategyRuleId;
  }

  /**
   * 赠品规则id.
   * @return 赠品规则id
   */
  public Long getGiftStrategyRuleId() {
      return this.giftStrategyRuleId;
  }

  /**
   * 是否组合商品.
   * @param combination 是否组合商品
   */
  public void setCombination(Boolean combination) {
    this.combination = combination;
  }

  /**
   * 是否组合商品.
   * @return 是否组合商品
   */
  public Boolean isCombination() {
      return this.combination;
  }

  /**
   * 福袋编码.
   * @param luckyCode 福袋编码
   */
  public void setLuckyCode(String luckyCode) {
    this.luckyCode = luckyCode == null ? null : luckyCode.trim();
  }

  /**
   * 福袋编码.
   * @return 福袋编码
   */
  public String getLuckyCode() {
      return this.luckyCode;
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
   * 计划赠送数量.
   * @param planQuantity 计划赠送数量
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划赠送数量.
   * @return 计划赠送数量
   */
  public Integer getPlanQuantity() {
      return this.planQuantity;
  }

  /**
   * 商品编码.
   * @param productCode 商品编码
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   * @return 商品编码
   */
  public String getProductCode() {
      return this.productCode;
  }

  /**
   * 商品id.
   * @param productId 商品id
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   * @return 商品id
   */
  public Long getProductId() {
      return this.productId;
  }

  /**
   * 商品名称.
   * @param productName 商品名称
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   * @return 商品名称
   */
  public String getProductName() {
      return this.productName;
  }

  /**
   * 购买数量.
   * @param quantity 购买数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 购买数量.
   * @return 购买数量
   */
  public Integer getQuantity() {
      return this.quantity;
  }

  /**
   * 已赠送数量.
   * @param sentQuantity 已赠送数量
   */
  public void setSentQuantity(Integer sentQuantity) {
    this.sentQuantity = sentQuantity;
  }

  /**
   * 已赠送数量.
   * @return 已赠送数量
   */
  public Integer getSentQuantity() {
      return this.sentQuantity;
  }

  /**
   * 尺码.
   * @param size 尺码
   */
  public void setSize(String size) {
    this.size = size == null ? null : size.trim();
  }

  /**
   * 尺码.
   * @return 尺码
   */
  public String getSize() {
      return this.size;
  }

  /**
   * 商品规格编码.
   * @param skuCode 商品规格编码
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   * @return 商品规格编码
   */
  public String getSkuCode() {
      return this.skuCode;
  }

  /**
   * 商品规格id.
   * @param skuId 商品规格id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   * @return 商品规格id
   */
  public Long getSkuId() {
      return this.skuId;
  }

  /**
   * 商品规格名称.
   * @param skuName 商品规格名称
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   * @return 商品规格名称
   */
  public String getSkuName() {
      return this.skuName;
  }
}