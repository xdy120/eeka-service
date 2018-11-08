package com.greatonce.oms.query.marketing;

import com.greatonce.core.database.Query;
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
public class PresellStoreDetailQuery extends Query {
  /**
   * 开始时间开始.
   */
  private LocalDateTime beginTimeBegin;
  /**
   * 开始时间结束.
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
   * 结束时间开始.
   */
  private LocalDateTime endTimeBegin;
  /**
   * 结束时间结束.
   */
  private LocalDateTime endTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * .
   */
  private List<String> productCodes;
  /**
   * 备注.
   */
  private String remark;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * 预售店铺明细状态.
   */
  private PresellDetailStatus status;
  /**
   * .
   */
  private List<PresellDetailStatus> statuses;
  /**
   * 店铺id.
   */
  private Long storeId;


  /**
   * 开始时间开始.
   * @param beginTimeBegin 开始.
   */
  public void setBeginTimeBegin(LocalDateTime beginTimeBegin) {
    this.beginTimeBegin = beginTimeBegin;
  }

  /**
   * 开始时间开始.
   * @return 开始时间开始
   */
  public LocalDateTime getBeginTimeBegin() {
    return this.beginTimeBegin;
  }

  /**
   * 开始时间结束.
   * @param beginTimeEnd 结束
   */
  public void setBeginTimeEnd(LocalDateTime beginTimeEnd) {
    this.beginTimeEnd = beginTimeEnd;
  }

  /**
   * 开始时间结束.
   * @return 开始时间结束
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
   * 结束时间开始.
   * @param endTimeBegin 开始.
   */
  public void setEndTimeBegin(LocalDateTime endTimeBegin) {
    this.endTimeBegin = endTimeBegin;
  }

  /**
   * 结束时间开始.
   * @return 结束时间开始
   */
  public LocalDateTime getEndTimeBegin() {
    return this.endTimeBegin;
  }

  /**
   * 结束时间结束.
   * @param endTimeEnd 结束
   */
  public void setEndTimeEnd(LocalDateTime endTimeEnd) {
    this.endTimeEnd = endTimeEnd;
  }

  /**
   * 结束时间结束.
   * @return 结束时间结束
   */
  public LocalDateTime getEndTimeEnd() {
    return this.endTimeEnd;
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
   * 预售明细id.
   * @param presellDetailId 预售明细id
   */
  public void setPresellDetailId(Long presellDetailId) {
    this.presellDetailId = presellDetailId;
  }

  /**
   * 预售明细id.
   * @return 预售明细id
   */
  public Long getPresellDetailId() {
      return this.presellDetailId;
  }

  /**
   * 预售计划id.
   * @param presellId 预售计划id
   */
  public void setPresellId(Long presellId) {
    this.presellId = presellId;
  }

  /**
   * 预售计划id.
   * @return 预售计划id
   */
  public Long getPresellId() {
      return this.presellId;
  }

  /**
   * 预售店铺明细id.
   * @param presellStoreDetailId 预售店铺明细id
   */
  public void setPresellStoreDetailId(Long presellStoreDetailId) {
    this.presellStoreDetailId = presellStoreDetailId;
  }

  /**
   * 预售店铺明细id.
   * @return 预售店铺明细id
   */
  public Long getPresellStoreDetailId() {
      return this.presellStoreDetailId;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 预售店铺明细状态.
   * @param status 预售店铺明细状态
   */
  public void setStatus(PresellDetailStatus status) {
    this.status = status;
  }

  /**
   * 预售店铺明细状态.
   * @return 预售店铺明细状态
   */
  public PresellDetailStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<PresellDetailStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<PresellDetailStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }
}