package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.stock.StockVirtualAllotStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 虚拟调拨.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockVirtualAllot extends VersionDO {
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
   * 入库虚拟仓id.
   */
  private Long inVirtualWarehouseId;
  /**
   * 入库虚拟仓名称.
   */
  private String inVirtualWarehouseName;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 出库虚拟仓id.
   */
  private Long outVirtualWarehouseId;
  /**
   * 出库虚拟仓名称.
   */
  private String outVirtualWarehouseName;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private StockVirtualAllotStatus status;
  /**
   * 虚拟调拨单编码.
   */
  private String stockVirtualAllotCode;
  /**
   * 虚拟调拨id.
   */
  private Long stockVirtualAllotId;
  /**
   * 版本.
   */
  private Integer version;

  /**
   * 明细.
   */
  private List<StockVirtualAllotDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockVirtualAllotId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockVirtualAllotId;
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
   * 入库虚拟仓id.
   */
  public void setInVirtualWarehouseId(Long inVirtualWarehouseId) {
    this.inVirtualWarehouseId = inVirtualWarehouseId;
  }

  /**
   * 入库虚拟仓id.
   */
  public Long getInVirtualWarehouseId() {
    return this.inVirtualWarehouseId;
  }

  /**
   * 入库虚拟仓名称.
   */
  public void setInVirtualWarehouseName(String inVirtualWarehouseName) {
    this.inVirtualWarehouseName = inVirtualWarehouseName == null ? null : inVirtualWarehouseName.trim();
  }

  /**
   * 入库虚拟仓名称.
   */
  public String getInVirtualWarehouseName() {
    return this.inVirtualWarehouseName;
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
   * 出库虚拟仓id.
   */
  public void setOutVirtualWarehouseId(Long outVirtualWarehouseId) {
    this.outVirtualWarehouseId = outVirtualWarehouseId;
  }

  /**
   * 出库虚拟仓id.
   */
  public Long getOutVirtualWarehouseId() {
    return this.outVirtualWarehouseId;
  }


  /**
   * 出库虚拟仓名称.
   */
  public void setOutVirtualWarehouseName(String outVirtualWarehouseName) {
    this.outVirtualWarehouseName = outVirtualWarehouseName == null ? null : outVirtualWarehouseName.trim();
  }

  /**
   * 出库虚拟仓名称.
   */
  public String getOutVirtualWarehouseName() {
    return this.outVirtualWarehouseName;
  }

  /**
   * 计划数量.
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   */
  public Integer getPlanQuantity() {
    return this.planQuantity;
  }

  /**
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
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
  public void setStatus(StockVirtualAllotStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public StockVirtualAllotStatus getStatus() {
    return this.status;
  }


  /**
   * 虚拟调拨单编码.
   */
  public void setStockVirtualAllotCode(String stockVirtualAllotCode) {
    this.stockVirtualAllotCode = stockVirtualAllotCode == null ? null : stockVirtualAllotCode.trim();
  }

  /**
   * 虚拟调拨单编码.
   */
  public String getStockVirtualAllotCode() {
    return this.stockVirtualAllotCode;
  }


  /**
   * 虚拟调拨id.
   */
  public void setStockVirtualAllotId(Long stockVirtualAllotId) {
    this.stockVirtualAllotId = stockVirtualAllotId;
  }

  /**
   * 虚拟调拨id.
   */
  public Long getStockVirtualAllotId() {
    return this.stockVirtualAllotId;
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
   * 明细.
   */
  public void setDetails(List<StockVirtualAllotDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<StockVirtualAllotDetail> getDetails() {
    return this.details;
  }
}