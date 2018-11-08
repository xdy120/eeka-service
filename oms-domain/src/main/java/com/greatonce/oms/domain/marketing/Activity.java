package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.marketing.ActivityStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动报名.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Activity extends VersionDO {
  /**
   * 活动编码.
   */
  private String activityCode;
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
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 开始日期.
   */
  private LocalDateTime beginTime;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 结束日期.
   */
  private LocalDateTime endTime;
  /**
   * 货值.
   */
  private Double goodsValue;
  /**
   * 是否按锁定数量上传.
   */
  private Boolean useLockQuantity;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private ActivityStatus status;
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
   * 明细.
   */
  private List<ActivityDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.activityId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.activityId;
  }


  /**
   * 活动编码.
   */
  public void setActivityCode(String activityCode) {
    this.activityCode = activityCode == null ? null : activityCode.trim();
  }

  /**
   * 活动编码.
   */
  public String getActivityCode() {
    return this.activityCode;
  }


  /**
   * 活动id.
   */
  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  /**
   * 活动id.
   */
  public Long getActivityId() {
    return this.activityId;
  }

  /**
   * 活动名称.
   */
  public void setActivityName(String activityName) {
    this.activityName = activityName == null ? null : activityName.trim();
  }

  /**
   * 活动名称.
   */
  public String getActivityName() {
    return this.activityName;
  }

  /**
   * 活动类型.
   */
  public void setActivityType(String activityType) {
    this.activityType = activityType == null ? null : activityType.trim();
  }

  /**
   * 活动类型.
   */
  public String getActivityType() {
    return this.activityType;
  }

  /**
   * 审核时间.
   */
  public void setAuditedTime(LocalDateTime auditedTime) {
    this.auditedTime = auditedTime;
  }

  /**
   * 审核时间.
   */
  public LocalDateTime getAuditedTime() {
    return this.auditedTime;
  }

  /**
   * 审核人.
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   */
  public String getAuditor() {
    return this.auditor;
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
   * 创建人.
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   */
  public String getCreator() {
    return this.creator;
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
   * 货值.
   */
  public void setGoodsValue(Double goodsValue) {
    this.goodsValue = goodsValue;
  }

  /**
   * 货值.
   */
  public Double getGoodsValue() {
    return this.goodsValue;
  }

  /**
   * 是否按锁定数量上传.
   */
  public void setUseLockQuantity(Boolean useLockQuantity) {
    this.useLockQuantity = useLockQuantity;
  }

  /**
   * 是否按锁定数量上传.
   */
  public Boolean isUseLockQuantity() {
    return this.useLockQuantity;
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
   * 状态.
   */
  public void setStatus(ActivityStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public ActivityStatus getStatus() {
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
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }

  /**
   * 虚拟仓id.
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   */
  public Long getVirtualWarehouseId() {
    return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   */
  public String getVirtualWarehouseName() {
    return this.virtualWarehouseName;
  }

  /**
   * 明细.
   */
  public void setDetails(List<ActivityDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<ActivityDetail> getDetails() {
    return this.details;
  }
}