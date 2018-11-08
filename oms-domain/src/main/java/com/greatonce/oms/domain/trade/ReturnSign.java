package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.trade.ReturnSignStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退货签收.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ReturnSign extends VersionDO {
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
   * 快递id.
   */
  private Long expressId;
  /**
   * 快递名称.
   */
  private String expressName;
  /**
   * 快递单号.
   */
  private String expressNo;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 退换货签收信息id.
   */
  private Long returnSignId;
  /**
   * 状态.
   */
  private ReturnSignStatus status;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 重量.
   */
  private Double weight;


  @Override
  public void setPrimaryKey(Long pk) {
    this.returnSignId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.returnSignId;
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
   * 快递id.
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   */
  public Long getExpressId() {
    return this.expressId;
  }

  /**
   * 快递名称.
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   */
  public String getExpressName() {
    return this.expressName;
  }

  /**
   * 快递单号.
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   */
  public String getExpressNo() {
    return this.expressNo;
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
   * 退换货签收信息id.
   */
  public void setReturnSignId(Long returnSignId) {
    this.returnSignId = returnSignId;
  }

  /**
   * 退换货签收信息id.
   */
  public Long getReturnSignId() {
    return this.returnSignId;
  }

  /**
   * 状态.
   */
  public void setStatus(ReturnSignStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public ReturnSignStatus getStatus() {
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
   * 重量.
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * 重量.
   */
  public Double getWeight() {
    return this.weight;
  }
}