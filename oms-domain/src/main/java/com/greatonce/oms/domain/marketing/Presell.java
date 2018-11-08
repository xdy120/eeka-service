package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.VersionDO;
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
public class Presell extends VersionDO {
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 开始时间.
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
   * 发货日期.
   */
  private LocalDate deliveryDate;
  /**
   * 结束时间.
   */
  private LocalDateTime endTime;
  /**
   * 是否动态预售.
   */
  private Boolean dynamic;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 预售计划编码.
   */
  private String presellCode;
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
   * 版本.
   */
  private Integer version;

  /**
   * 商品明细.
   */
  private List<PresellDetail> details;
  /**
   * 店铺明细.
   */
  private List<PresellStore> stores;

  @Override
  public void setPrimaryKey(Long pk) {
    this.presellId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.presellId;
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
   * 发货日期.
   */
  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  /**
   * 发货日期.
   */
  public LocalDate getDeliveryDate() {
    return this.deliveryDate;
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
   * 是否动态预售.
   */
  public void setDynamic(Boolean dynamic) {
    this.dynamic = dynamic;
  }

  /**
   * 是否动态预售.
   */
  public Boolean isDynamic() {
    return this.dynamic;
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
   * 预售计划编码.
   */
  public void setPresellCode(String presellCode) {
    this.presellCode = presellCode == null ? null : presellCode.trim();
  }

  /**
   * 预售计划编码.
   */
  public String getPresellCode() {
    return this.presellCode;
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
   * 预售计划名称.
   */
  public void setPresellName(String presellName) {
    this.presellName = presellName == null ? null : presellName.trim();
  }

  /**
   * 预售计划名称.
   */
  public String getPresellName() {
    return this.presellName;
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
  public void setStatus(PresellStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public PresellStatus getStatus() {
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
   * 商品明细.
   */
  public void setDetails(List<PresellDetail> details) {
    this.details = details;
  }

  /**
   * 商品明细.
   */
  public List<PresellDetail> getDetails() {
    return this.details;
  }

  /**
   * 店铺明细.
   */
  public void setStores(List<PresellStore> stores) {
    this.stores = stores;
  }

  /**
   * 店铺明细.
   */
  public List<PresellStore> getStores() {
    return this.stores;
  }
}