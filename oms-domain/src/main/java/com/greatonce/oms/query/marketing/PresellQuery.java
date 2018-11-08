package com.greatonce.oms.query.marketing;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.marketing.PresellStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 预售.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PresellQuery extends Query {
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
   * 创建人.
   */
  private String creator;
  /**
   * 发货日期开始.
   */
  private LocalDate deliveryDateBegin;
  /**
   * 发货日期结束.
   */
  private LocalDate deliveryDateEnd;
  /**
   * 结束时间开始.
   */
  private LocalDateTime endTimeBegin;
  /**
   * 结束时间结束.
   */
  private LocalDateTime endTimeEnd;
  /**
   * 是否动态预售.
   */
  private Boolean dynamic;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 预售计划编码.
   */
  private String presellCode;
  /**
   * .
   */
  private List<String> presellCodes;
  /**
   * 预售计划id.
   */
  private Long presellId;
  /**
   * 预售计划名称.
   */
  private String presellName;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private PresellStatus status;
  /**
   * .
   */
  private List<PresellStatus> statuses;
  /**
   * .
   */
  private Long storeId;
  /**
   * 版本.
   */
  private Integer version;


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
   * 发货日期开始.
   * @param deliveryDateBegin 开始.
   */
  public void setDeliveryDateBegin(LocalDate deliveryDateBegin) {
    this.deliveryDateBegin = deliveryDateBegin;
  }

  /**
   * 发货日期开始.
   * @return 发货日期开始
   */
  public LocalDate getDeliveryDateBegin() {
    return this.deliveryDateBegin;
  }

  /**
   * 发货日期结束.
   * @param deliveryDateEnd 结束
   */
  public void setDeliveryDateEnd(LocalDate deliveryDateEnd) {
    this.deliveryDateEnd = deliveryDateEnd;
  }

  /**
   * 发货日期结束.
   * @return 发货日期结束
   */
  public LocalDate getDeliveryDateEnd() {
    return this.deliveryDateEnd;
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
   * 是否动态预售.
   * @param dynamic 是否动态预售
   */
  public void setDynamic(Boolean dynamic) {
    this.dynamic = dynamic;
  }

  /**
   * 是否动态预售.
   * @return 是否动态预售
   */
  public Boolean isDynamic() {
      return this.dynamic;
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
   * 预售计划编码.
   * @param presellCode 预售计划编码
   */
  public void setPresellCode(String presellCode) {
    this.presellCode = presellCode == null ? null : presellCode.trim();
  }

  /**
   * 预售计划编码.
   * @return 预售计划编码
   */
  public String getPresellCode() {
      return this.presellCode;
  }

  /**
   * .
   * @param presellCodes 
   */
  public void setPresellCodes(List<String> presellCodes) {
    this.presellCodes = presellCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getPresellCodes() {
      return this.presellCodes;
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
   * 预售计划名称.
   * @param presellName 预售计划名称
   */
  public void setPresellName(String presellName) {
    this.presellName = presellName == null ? null : presellName.trim();
  }

  /**
   * 预售计划名称.
   * @return 预售计划名称
   */
  public String getPresellName() {
      return this.presellName;
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
  public void setStatus(PresellStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public PresellStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<PresellStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<PresellStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * .
   * @param storeId 
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * .
   * @return 
   */
  public Long getStoreId() {
      return this.storeId;
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
}