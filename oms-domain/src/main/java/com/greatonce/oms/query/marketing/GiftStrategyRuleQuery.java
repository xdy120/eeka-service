package com.greatonce.oms.query.marketing;

import com.greatonce.core.database.Query;
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
public class GiftStrategyRuleQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 赠品策略id.
   * @param giftStrategyId 赠品策略id
   */
  public void setGiftStrategyId(Long giftStrategyId) {
    this.giftStrategyId = giftStrategyId;
  }

  /**
   * 赠品策略id.
   * @return 赠品策略id
   */
  public Long getGiftStrategyId() {
      return this.giftStrategyId;
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
   * 赠品规则名称.
   * @param giftStrategyRuleName 赠品规则名称
   */
  public void setGiftStrategyRuleName(String giftStrategyRuleName) {
    this.giftStrategyRuleName = giftStrategyRuleName == null ? null : giftStrategyRuleName.trim();
  }

  /**
   * 赠品规则名称.
   * @return 赠品规则名称
   */
  public String getGiftStrategyRuleName() {
      return this.giftStrategyRuleName;
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
   * 排序号.
   * @param priorityNo 排序号
   */
  public void setPriorityNo(Integer priorityNo) {
    this.priorityNo = priorityNo;
  }

  /**
   * 排序号.
   * @return 排序号
   */
  public Integer getPriorityNo() {
      return this.priorityNo;
  }

  /**
   * 规则内容.
   * @param ruleSetting 规则内容
   */
  public void setRuleSetting(String ruleSetting) {
    this.ruleSetting = ruleSetting == null ? null : ruleSetting.trim();
  }

  /**
   * 规则内容.
   * @return 规则内容
   */
  public String getRuleSetting() {
      return this.ruleSetting;
  }

  /**
   * 赠品规则类型.
   * @param ruleType 赠品规则类型
   */
  public void setRuleType(GiftStrategyRuleType ruleType) {
    this.ruleType = ruleType;
  }

  /**
   * 赠品规则类型.
   * @return 赠品规则类型
   */
  public GiftStrategyRuleType getRuleType() {
      return this.ruleType;
  }
}