package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.enums.marketing.TimeType;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 赠品策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GiftStrategy extends EnableDO {
  /**
   * 开始日期.
   */
  private LocalDateTime beginTime;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 结束日期.
   */
  private LocalDateTime endTime;
  /**
   * 赠品策略编码.
   */
  private String giftStrategyCode;
  /**
   * 赠品策略id.
   */
  private Long giftStrategyId;
  /**
   * 赠品策略名称.
   */
  private String giftStrategyName;
  /**
   * 赠品策略类型.
   */
  private GiftStrategyType giftStrategyType;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 排序号.
   */
  private Integer priorityNo;
  /**
   * 时间类型.
   */
  private TimeType timeType;

  /**
   * ${field.comment}.
   */
  private List<GiftStrategyRule> rules;
  /**
   * ${field.comment}.
   */
  private List<GiftStrategyStore> stores;

  @Override
  public void setPrimaryKey(Long pk) {
    this.giftStrategyId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.giftStrategyId;
  }


  /**
   * 开始日期.
   */
  public void setBeginTime(LocalDateTime beginTime) {
    this.beginTime = beginTime;
  }

  /**
   * 开始日期.
   */
  public LocalDateTime getBeginTime() {
    return this.beginTime;
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
   * 结束日期.
   */
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  /**
   * 结束日期.
   */
  public LocalDateTime getEndTime() {
    return this.endTime;
  }

  /**
   * 赠品策略编码.
   */
  public void setGiftStrategyCode(String giftStrategyCode) {
    this.giftStrategyCode = giftStrategyCode == null ? null : giftStrategyCode.trim();
  }

  /**
   * 赠品策略编码.
   */
  public String getGiftStrategyCode() {
    return this.giftStrategyCode;
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
   * 赠品策略名称.
   */
  public void setGiftStrategyName(String giftStrategyName) {
    this.giftStrategyName = giftStrategyName == null ? null : giftStrategyName.trim();
  }

  /**
   * 赠品策略名称.
   */
  public String getGiftStrategyName() {
    return this.giftStrategyName;
  }

  /**
   * 赠品策略类型.
   */
  public void setGiftStrategyType(GiftStrategyType giftStrategyType) {
    this.giftStrategyType = giftStrategyType;
  }

  /**
   * 赠品策略类型.
   */
  public GiftStrategyType getGiftStrategyType() {
    return this.giftStrategyType;
  }

  /**
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
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
   * 时间类型.
   */
  public void setTimeType(TimeType timeType) {
    this.timeType = timeType;
  }

  /**
   * 时间类型.
   */
  public TimeType getTimeType() {
    return this.timeType;
  }

  /**
   * ${field.comment}.
   */
  public void setRules(List<GiftStrategyRule> rules) {
    this.rules = rules;
  }

  /**
   * ${field.comment}.
   */
  public List<GiftStrategyRule> getRules() {
    return this.rules;
  }

  /**
   * ${field.comment}.
   */
  public void setStores(List<GiftStrategyStore> stores) {
    this.stores = stores;
  }

  /**
   * ${field.comment}.
   */
  public List<GiftStrategyStore> getStores() {
    return this.stores;
  }
}