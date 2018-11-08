package com.greatonce.oms.query.marketing;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.marketing.ActivityStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动报名.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ActivityQuery extends Query {
  /**
   * 活动编码.
   */
  private String activityCode;
  /**
   * .
   */
  private List<String> activityCodes;
  /**
   * 活动id.
   */
  private Long activityId;
  /**
   * 活动名称.
   */
  private String activityName;
  /**
   * 活动类型.
   */
  private String activityType;
  /**
   * 审核时间开始.
   */
  private LocalDateTime auditedTimeBegin;
  /**
   * 审核时间结束.
   */
  private LocalDateTime auditedTimeEnd;
  /**
   * 审核人.
   */
  private String auditor;
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
   * 创建人.
   */
  private String creator;
  /**
   * 结束日期开始.
   */
  private LocalDateTime endTimeBegin;
  /**
   * 结束日期结束.
   */
  private LocalDateTime endTimeEnd;
  /**
   * 货值.
   */
  private Double goodsValue;
  /**
   * 是否按锁定数量上传.
   */
  private Boolean useLockQuantity;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private ActivityStatus status;
  /**
   * .
   */
  private List<ActivityStatus> statuses;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;


  /**
   * 活动编码.
   * @param activityCode 活动编码
   */
  public void setActivityCode(String activityCode) {
    this.activityCode = activityCode == null ? null : activityCode.trim();
  }

  /**
   * 活动编码.
   * @return 活动编码
   */
  public String getActivityCode() {
      return this.activityCode;
  }

  /**
   * .
   * @param activityCodes 
   */
  public void setActivityCodes(List<String> activityCodes) {
    this.activityCodes = activityCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getActivityCodes() {
      return this.activityCodes;
  }

  /**
   * 活动id.
   * @param activityId 活动id
   */
  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  /**
   * 活动id.
   * @return 活动id
   */
  public Long getActivityId() {
      return this.activityId;
  }

  /**
   * 活动名称.
   * @param activityName 活动名称
   */
  public void setActivityName(String activityName) {
    this.activityName = activityName == null ? null : activityName.trim();
  }

  /**
   * 活动名称.
   * @return 活动名称
   */
  public String getActivityName() {
      return this.activityName;
  }

  /**
   * 活动类型.
   * @param activityType 活动类型
   */
  public void setActivityType(String activityType) {
    this.activityType = activityType == null ? null : activityType.trim();
  }

  /**
   * 活动类型.
   * @return 活动类型
   */
  public String getActivityType() {
      return this.activityType;
  }

  /**
   * 审核时间开始.
   * @param auditedTimeBegin 开始.
   */
  public void setAuditedTimeBegin(LocalDateTime auditedTimeBegin) {
    this.auditedTimeBegin = auditedTimeBegin;
  }

  /**
   * 审核时间开始.
   * @return 审核时间开始
   */
  public LocalDateTime getAuditedTimeBegin() {
    return this.auditedTimeBegin;
  }

  /**
   * 审核时间结束.
   * @param auditedTimeEnd 结束
   */
  public void setAuditedTimeEnd(LocalDateTime auditedTimeEnd) {
    this.auditedTimeEnd = auditedTimeEnd;
  }

  /**
   * 审核时间结束.
   * @return 审核时间结束
   */
  public LocalDateTime getAuditedTimeEnd() {
    return this.auditedTimeEnd;
  }

  /**
   * 审核人.
   * @param auditor 审核人
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   * @return 审核人
   */
  public String getAuditor() {
      return this.auditor;
  }

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
   * 创建人.
   * @param creator 创建人
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   * @return 创建人
   */
  public String getCreator() {
      return this.creator;
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
   * 货值.
   * @param goodsValue 货值
   */
  public void setGoodsValue(Double goodsValue) {
    this.goodsValue = goodsValue;
  }

  /**
   * 货值.
   * @return 货值
   */
  public Double getGoodsValue() {
      return this.goodsValue;
  }

  /**
   * 是否按锁定数量上传.
   * @param useLockQuantity 是否按锁定数量上传
   */
  public void setUseLockQuantity(Boolean useLockQuantity) {
    this.useLockQuantity = useLockQuantity;
  }

  /**
   * 是否按锁定数量上传.
   * @return 是否按锁定数量上传
   */
  public Boolean isUseLockQuantity() {
      return this.useLockQuantity;
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
   * 状态.
   * @param status 状态
   */
  public void setStatus(ActivityStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public ActivityStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<ActivityStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<ActivityStatus> getStatuses() {
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

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }

  /**
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }

  /**
   * 虚拟仓id.
   * @param virtualWarehouseId 虚拟仓id
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   * @return 虚拟仓id
   */
  public Long getVirtualWarehouseId() {
      return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   * @param virtualWarehouseName 虚拟仓名称
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   * @return 虚拟仓名称
   */
  public String getVirtualWarehouseName() {
      return this.virtualWarehouseName;
  }
}