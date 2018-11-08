package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyRuleType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 赠品规则.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GiftStrategyRule extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 赠品策略id.
   */
  private Long giftStrategyId;
  /**
   * 赠品规则id.
   */
  private Long giftStrategyRuleId;
  /**
   * 赠品规则名称.
   */
  private String giftStrategyRuleName;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 排序号.
   */
  private Integer priorityNo;
  /**
   * 规则内容.
   */
  private String ruleSetting;
  /**
   * 赠品规则类型.
   */
  private GiftStrategyRuleType ruleType;

  /**
   * 赠送商品.
   */
  private List<GiftStrategyGift> gifts;
  /**
   * 规则设置.
   */
  private GiftStrategyRuleSetting setting;
  /**
   * 活动商品.
   */
  private List<GiftStrategyProduct> skus;

  @Override
  public void setPrimaryKey(Long pk) {
    this.giftStrategyRuleId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.giftStrategyRuleId;
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
   * 赠品策略id.
   */
  public void setGiftStrategyId(Long giftStrategyId) {
    this.giftStrategyId = giftStrategyId;
  }

  /**
   * 赠品策略id.
   */
  public Long getGiftStrategyId() {
    return this.giftStrategyId;
  }

  /**
   * 赠品规则id.
   */
  public void setGiftStrategyRuleId(Long giftStrategyRuleId) {
    this.giftStrategyRuleId = giftStrategyRuleId;
  }

  /**
   * 赠品规则id.
   */
  public Long getGiftStrategyRuleId() {
    return this.giftStrategyRuleId;
  }

  /**
   * 赠品规则名称.
   */
  public void setGiftStrategyRuleName(String giftStrategyRuleName) {
    this.giftStrategyRuleName = giftStrategyRuleName == null ? null : giftStrategyRuleName.trim();
  }

  /**
   * 赠品规则名称.
   */
  public String getGiftStrategyRuleName() {
    return this.giftStrategyRuleName;
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
   * 排序号.
   */
  public void setPriorityNo(Integer priorityNo) {
    this.priorityNo = priorityNo;
  }

  /**
   * 排序号.
   */
  public Integer getPriorityNo() {
    return this.priorityNo;
  }

  /**
   * 规则内容.
   */
  public void setRuleSetting(String ruleSetting) {
    this.ruleSetting = ruleSetting == null ? null : ruleSetting.trim();
  }

  /**
   * 规则内容.
   */
  public String getRuleSetting() {
    return this.ruleSetting;
  }

  /**
   * 赠品规则类型.
   */
  public void setRuleType(GiftStrategyRuleType ruleType) {
    this.ruleType = ruleType;
  }

  /**
   * 赠品规则类型.
   */
  public GiftStrategyRuleType getRuleType() {
    return this.ruleType;
  }

  /**
   * 赠送商品.
   */
  public void setGifts(List<GiftStrategyGift> gifts) {
    this.gifts = gifts;
  }

  /**
   * 赠送商品.
   */
  public List<GiftStrategyGift> getGifts() {
    return this.gifts;
  }

  /**
   * 规则设置.
   */
  public void setSetting(GiftStrategyRuleSetting setting) {
    this.setting = setting;
  }

  /**
   * 规则设置.
   */
  public GiftStrategyRuleSetting getSetting() {
    return this.setting;
  }

  /**
   * 活动商品.
   */
  public void setSkus(List<GiftStrategyProduct> skus) {
    this.skus = skus;
  }

  /**
   * 活动商品.
   */
  public List<GiftStrategyProduct> getSkus() {
    return this.skus;
  }
}