package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.vip.VipScheduleStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品档期.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipScheduleQuery extends Query {
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
   * 结束时间开始.
   */
  private LocalDateTime endTimeBegin;
  /**
   * 结束时间结束.
   */
  private LocalDateTime endTimeEnd;
  /**
   * 货值.
   */
  private Double goodsValue;
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
  private VipScheduleStatus status;
  /**
   * .
   */
  private List<VipScheduleStatus> statuses;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * .
   */
  private List<Long> storeIds;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 版本.
   */
  private Integer version;
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
   * 档期名称.
   */
  private String vipScheduleName;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;


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
  public void setStatus(VipScheduleStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipScheduleStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipScheduleStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipScheduleStatus> getStatuses() {
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

  /**
   * 档期名称.
   * @param vipScheduleName 档期名称
   */
  public void setVipScheduleName(String vipScheduleName) {
    this.vipScheduleName = vipScheduleName == null ? null : vipScheduleName.trim();
  }

  /**
   * 档期名称.
   * @return 档期名称
   */
  public String getVipScheduleName() {
      return this.vipScheduleName;
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