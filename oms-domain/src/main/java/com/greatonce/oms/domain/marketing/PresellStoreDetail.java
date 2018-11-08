package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.marketing.PresellDetailStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 预售店铺明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PresellStoreDetail extends BaseDO {
  /**
   * 开始时间.
   */
  private LocalDateTime beginTime;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 结束时间.
   */
  private LocalDateTime endTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 预售明细id.
   */
  private Long presellDetailId;
  /**
   * 预售计划id.
   */
  private Long presellId;
  /**
   * 预售店铺明细id.
   */
  private Long presellStoreDetailId;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 预售店铺明细状态.
   */
  private PresellDetailStatus status;
  /**
   * 店铺id.
   */
  private Long storeId;

  /**
   * 预售明细.
   */
  private PresellDetail presellDetail;
  /**
   * 预售店铺.
   */
  private PresellStore presellStore;

  @Override
  public void setPrimaryKey(Long pk) {
    this.presellStoreDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.presellStoreDetailId;
  }


  /**
   * 开始时间.
   */
  public void setBeginTime(LocalDateTime beginTime) {
    this.beginTime = beginTime;
  }

  /**
   * 开始时间.
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
   * 结束时间.
   */
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  /**
   * 结束时间.
   */
  public LocalDateTime getEndTime() {
    return this.endTime;
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
   * 预售明细id.
   */
  public void setPresellDetailId(Long presellDetailId) {
    this.presellDetailId = presellDetailId;
  }

  /**
   * 预售明细id.
   */
  public Long getPresellDetailId() {
    return this.presellDetailId;
  }

  /**
   * 预售计划id.
   */
  public void setPresellId(Long presellId) {
    this.presellId = presellId;
  }

  /**
   * 预售计划id.
   */
  public Long getPresellId() {
    return this.presellId;
  }

  /**
   * 预售店铺明细id.
   */
  public void setPresellStoreDetailId(Long presellStoreDetailId) {
    this.presellStoreDetailId = presellStoreDetailId;
  }

  /**
   * 预售店铺明细id.
   */
  public Long getPresellStoreDetailId() {
    return this.presellStoreDetailId;
  }


  /**
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
  }


  /**
   * 预售店铺明细状态.
   */
  public void setStatus(PresellDetailStatus status) {
    this.status = status;
  }

  /**
   * 预售店铺明细状态.
   */
  public PresellDetailStatus getStatus() {
    return this.status;
  }


  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }

  /**
   * 预售明细.
   */
  public void setPresellDetail(PresellDetail presellDetail) {
    this.presellDetail = presellDetail;
  }

  /**
   * 预售明细.
   */
  public PresellDetail getPresellDetail() {
    return this.presellDetail;
  }

  /**
   * 预售店铺.
   */
  public void setPresellStore(PresellStore presellStore) {
    this.presellStore = presellStore;
  }

  /**
   * 预售店铺.
   */
  public PresellStore getPresellStore() {
    return this.presellStore;
  }
}