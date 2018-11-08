package com.greatonce.oms.query.marketing;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyType;
import com.greatonce.oms.domain.enums.marketing.TimeType;
import java.time.LocalDateTime;

/**
 * 赠品策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GiftStrategyQuery extends Query {
  /**
   * 开始日期开始.
   */
  private LocalDateTime beginTimeBegin;
  /**
   * 开始日期结束.
   */
  private LocalDateTime beginTimeEnd;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 结束日期开始.
   */
  private LocalDateTime endTimeBegin;
  /**
   * 结束日期结束.
   */
  private LocalDateTime endTimeEnd;
  /**
   * 赠品sku编码.
   */
  private String giftSkuCode;
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
   * 活动商品sku编码.
   */
  private String giftStrategySkuCode;
  /**
   * 赠品策略类型.
   */
  private GiftStrategyType giftStrategyType;
  /**
   * 是否启用.
   */
  private Boolean enable;
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
   * 时间类型.
   */
  private TimeType timeType;


  /**
   * 开始日期开始.
   * @param beginTimeBegin 开始.
   */
  public void setBeginTimeBegin(LocalDateTime beginTimeBegin) {
    this.beginTimeBegin = beginTimeBegin;
  }

  /**
   * 开始日期开始.
   * @return 开始日期开始
   */
  public LocalDateTime getBeginTimeBegin() {
    return this.beginTimeBegin;
  }

  /**
   * 开始日期结束.
   * @param beginTimeEnd 结束
   */
  public void setBeginTimeEnd(LocalDateTime beginTimeEnd) {
    this.beginTimeEnd = beginTimeEnd;
  }

  /**
   * 开始日期结束.
   * @return 开始日期结束
   */
  public LocalDateTime getBeginTimeEnd() {
    return this.beginTimeEnd;
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
   * 结束日期开始.
   * @param endTimeBegin 开始.
   */
  public void setEndTimeBegin(LocalDateTime endTimeBegin) {
    this.endTimeBegin = endTimeBegin;
  }

  /**
   * 结束日期开始.
   * @return 结束日期开始
   */
  public LocalDateTime getEndTimeBegin() {
    return this.endTimeBegin;
  }

  /**
   * 结束日期结束.
   * @param endTimeEnd 结束
   */
  public void setEndTimeEnd(LocalDateTime endTimeEnd) {
    this.endTimeEnd = endTimeEnd;
  }

  /**
   * 结束日期结束.
   * @return 结束日期结束
   */
  public LocalDateTime getEndTimeEnd() {
    return this.endTimeEnd;
  }

  /**
   * 赠品sku编码.
   * @param giftSkuCode 赠品sku编码
   */
  public void setGiftSkuCode(String giftSkuCode) {
    this.giftSkuCode = giftSkuCode == null ? null : giftSkuCode.trim();
  }

  /**
   * 赠品sku编码.
   * @return 赠品sku编码
   */
  public String getGiftSkuCode() {
      return this.giftSkuCode;
  }

  /**
   * 赠品策略编码.
   * @param giftStrategyCode 赠品策略编码
   */
  public void setGiftStrategyCode(String giftStrategyCode) {
    this.giftStrategyCode = giftStrategyCode == null ? null : giftStrategyCode.trim();
  }

  /**
   * 赠品策略编码.
   * @return 赠品策略编码
   */
  public String getGiftStrategyCode() {
      return this.giftStrategyCode;
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
   * 赠品策略名称.
   * @param giftStrategyName 赠品策略名称
   */
  public void setGiftStrategyName(String giftStrategyName) {
    this.giftStrategyName = giftStrategyName == null ? null : giftStrategyName.trim();
  }

  /**
   * 赠品策略名称.
   * @return 赠品策略名称
   */
  public String getGiftStrategyName() {
      return this.giftStrategyName;
  }

  /**
   * 活动商品sku编码.
   * @param giftStrategySkuCode 活动商品sku编码
   */
  public void setGiftStrategySkuCode(String giftStrategySkuCode) {
    this.giftStrategySkuCode = giftStrategySkuCode == null ? null : giftStrategySkuCode.trim();
  }

  /**
   * 活动商品sku编码.
   * @return 活动商品sku编码
   */
  public String getGiftStrategySkuCode() {
      return this.giftStrategySkuCode;
  }

  /**
   * 赠品策略类型.
   * @param giftStrategyType 赠品策略类型
   */
  public void setGiftStrategyType(GiftStrategyType giftStrategyType) {
    this.giftStrategyType = giftStrategyType;
  }

  /**
   * 赠品策略类型.
   * @return 赠品策略类型
   */
  public GiftStrategyType getGiftStrategyType() {
      return this.giftStrategyType;
  }

  /**
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
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
   * 时间类型.
   * @param timeType 时间类型
   */
  public void setTimeType(TimeType timeType) {
    this.timeType = timeType;
  }

  /**
   * 时间类型.
   * @return 时间类型
   */
  public TimeType getTimeType() {
      return this.timeType;
  }
}