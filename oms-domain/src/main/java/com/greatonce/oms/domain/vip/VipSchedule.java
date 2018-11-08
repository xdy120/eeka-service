package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.VersionDO;
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
public class VipSchedule extends VersionDO {
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
   * 结束时间.
   */
  private LocalDateTime endTime;
  /**
   * 货值.
   */
  private Double goodsValue;
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
  private VipScheduleStatus status;
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
   * 档期编号.
   */
  private String vipScheduleCode;
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
   * 明细.
   */
  private List<VipScheduleDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.vipScheduleId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipScheduleId;
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
  public void setStatus(VipScheduleStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipScheduleStatus getStatus() {
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
   * 档期名称.
   */
  public void setVipScheduleName(String vipScheduleName) {
    this.vipScheduleName = vipScheduleName == null ? null : vipScheduleName.trim();
  }

  /**
   * 档期名称.
   */
  public String getVipScheduleName() {
    return this.vipScheduleName;
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
  public void setDetails(List<VipScheduleDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<VipScheduleDetail> getDetails() {
    return this.details;
  }
}