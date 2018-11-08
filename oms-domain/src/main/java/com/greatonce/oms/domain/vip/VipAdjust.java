package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.VersionDO;
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
public class VipAdjust extends VersionDO {
  /**
   * 调整原因.
   */
  private String adjustReason;
  /**
   * 调整类型.
   */
  private VipAdjustType adjustType;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 是否上传.
   */
  private Boolean upload;
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
  private VipAdjustStatus status;
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
   * 档期id.
   */
  private Long vipScheduleId;

  /**
   * 明细.
   */
  private List<VipAdjustDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.vipAdjustId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipAdjustId;
  }


  /**
   * 调整原因.
   */
  public void setAdjustReason(String adjustReason) {
    this.adjustReason = adjustReason == null ? null : adjustReason.trim();
  }

  /**
   * 调整原因.
   */
  public String getAdjustReason() {
    return this.adjustReason;
  }

  /**
   * 调整类型.
   */
  public void setAdjustType(VipAdjustType adjustType) {
    this.adjustType = adjustType;
  }

  /**
   * 调整类型.
   */
  public VipAdjustType getAdjustType() {
    return this.adjustType;
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
   * 是否上传.
   */
  public void setUpload(Boolean upload) {
    this.upload = upload;
  }

  /**
   * 是否上传.
   */
  public Boolean isUpload() {
    return this.upload;
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
  public void setStatus(VipAdjustStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipAdjustStatus getStatus() {
    return this.status;
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
   * 调整单编码.
   */
  public void setVipAdjustCode(String vipAdjustCode) {
    this.vipAdjustCode = vipAdjustCode == null ? null : vipAdjustCode.trim();
  }

  /**
   * 调整单编码.
   */
  public String getVipAdjustCode() {
    return this.vipAdjustCode;
  }

  /**
   * 调整单id.
   */
  public void setVipAdjustId(Long vipAdjustId) {
    this.vipAdjustId = vipAdjustId;
  }

  /**
   * 调整单id.
   */
  public Long getVipAdjustId() {
    return this.vipAdjustId;
  }

  /**
   * 档期编号.
   */
  public void setVipScheduleCode(String vipScheduleCode) {
    this.vipScheduleCode = vipScheduleCode == null ? null : vipScheduleCode.trim();
  }

  /**
   * 档期编号.
   */
  public String getVipScheduleCode() {
    return this.vipScheduleCode;
  }


  /**
   * 档期id.
   */
  public void setVipScheduleId(Long vipScheduleId) {
    this.vipScheduleId = vipScheduleId;
  }

  /**
   * 档期id.
   */
  public Long getVipScheduleId() {
    return this.vipScheduleId;
  }

  /**
   * 明细.
   */
  public void setDetails(List<VipAdjustDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<VipAdjustDetail> getDetails() {
    return this.details;
  }
}