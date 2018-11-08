package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.vip.VipAdjustType;
import com.greatonce.oms.domain.enums.vip.VipAdjustStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品调整单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipAdjustQuery extends Query {
  /**
   * 调整原因.
   */
  private String adjustReason;
  /**
   * 调整类型.
   */
  private VipAdjustType adjustType;
  /**
   * .
   */
  private List<VipAdjustType> adjustTypes;
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
   * 是否上传.
   */
  private Boolean upload;
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
  private VipAdjustStatus status;
  /**
   * .
   */
  private List<VipAdjustStatus> statuses;
  /**
   * .
   */
  private List<Long> storeIds;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 调整单编码.
   */
  private String vipAdjustCode;
  /**
   * 调整单id.
   */
  private Long vipAdjustId;
  /**
   * 档期编号.
   */
  private String vipScheduleCode;
  /**
   * .
   */
  private List<String> vipScheduleCodes;
  /**
   * 档期id.
   */
  private Long vipScheduleId;


  /**
   * 调整原因.
   * @param adjustReason 调整原因
   */
  public void setAdjustReason(String adjustReason) {
    this.adjustReason = adjustReason == null ? null : adjustReason.trim();
  }

  /**
   * 调整原因.
   * @return 调整原因
   */
  public String getAdjustReason() {
      return this.adjustReason;
  }

  /**
   * 调整类型.
   * @param adjustType 调整类型
   */
  public void setAdjustType(VipAdjustType adjustType) {
    this.adjustType = adjustType;
  }

  /**
   * 调整类型.
   * @return 调整类型
   */
  public VipAdjustType getAdjustType() {
      return this.adjustType;
  }

  /**
   * .
   * @param adjustTypes 
   */
  public void setAdjustTypes(List<VipAdjustType> adjustTypes) {
    this.adjustTypes = adjustTypes;
  }

  /**
   * .
   * @return 
   */
  public List<VipAdjustType> getAdjustTypes() {
      return this.adjustTypes;
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
   * 是否上传.
   * @param upload 是否上传
   */
  public void setUpload(Boolean upload) {
    this.upload = upload;
  }

  /**
   * 是否上传.
   * @return 是否上传
   */
  public Boolean isUpload() {
      return this.upload;
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
  public void setStatus(VipAdjustStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipAdjustStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipAdjustStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipAdjustStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * .
   * @param storeIds 
   */
  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getStoreIds() {
      return this.storeIds;
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
   * 调整单编码.
   * @param vipAdjustCode 调整单编码
   */
  public void setVipAdjustCode(String vipAdjustCode) {
    this.vipAdjustCode = vipAdjustCode == null ? null : vipAdjustCode.trim();
  }

  /**
   * 调整单编码.
   * @return 调整单编码
   */
  public String getVipAdjustCode() {
      return this.vipAdjustCode;
  }

  /**
   * 调整单id.
   * @param vipAdjustId 调整单id
   */
  public void setVipAdjustId(Long vipAdjustId) {
    this.vipAdjustId = vipAdjustId;
  }

  /**
   * 调整单id.
   * @return 调整单id
   */
  public Long getVipAdjustId() {
      return this.vipAdjustId;
  }

  /**
   * 档期编号.
   * @param vipScheduleCode 档期编号
   */
  public void setVipScheduleCode(String vipScheduleCode) {
    this.vipScheduleCode = vipScheduleCode == null ? null : vipScheduleCode.trim();
  }

  /**
   * 档期编号.
   * @return 档期编号
   */
  public String getVipScheduleCode() {
      return this.vipScheduleCode;
  }

  /**
   * .
   * @param vipScheduleCodes 
   */
  public void setVipScheduleCodes(List<String> vipScheduleCodes) {
    this.vipScheduleCodes = vipScheduleCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getVipScheduleCodes() {
      return this.vipScheduleCodes;
  }

  /**
   * 档期id.
   * @param vipScheduleId 档期id
   */
  public void setVipScheduleId(Long vipScheduleId) {
    this.vipScheduleId = vipScheduleId;
  }

  /**
   * 档期id.
   * @return 档期id
   */
  public Long getVipScheduleId() {
      return this.vipScheduleId;
  }
}