package com.greatonce.oms.domain.purchase;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.purchase.PurchaseNoticeStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 采购入库通知单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PurchaseNoticeOrder extends VersionDO {
  /**
   * 批次号.
   */
  private String batchNo;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 入库状态.
   */
  private InStatus inStatus;
  /**
   * 最后入库时间.
   */
  private LocalDateTime lastInTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 采购入库通知单编码.
   */
  private String purchaseNoticeOrderCode;
  /**
   * 采购入库通知单id.
   */
  private Long purchaseNoticeOrderId;
  /**
   * 采购订单编码.
   */
  private String purchaseOrderCode;
  /**
   * 采购订单id.
   */
  private Long purchaseOrderId;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private PurchaseNoticeStatus status;
  /**
   * 供应商编码.
   */
  private String supplierCode;
  /**
   * 供应商名称.
   */
  private String supplierName;
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
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;

  /**
   * 明细.
   */
  private List<PurchaseNoticeOrderDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.purchaseNoticeOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.purchaseNoticeOrderId;
  }


  /**
   * 批次号.
   */
  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo == null ? null : batchNo.trim();
  }

  /**
   * 批次号.
   */
  public String getBatchNo() {
    return this.batchNo;
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
   * 入库状态.
   */
  public void setInStatus(InStatus inStatus) {
    this.inStatus = inStatus;
  }

  /**
   * 入库状态.
   */
  public InStatus getInStatus() {
    return this.inStatus;
  }


  /**
   * 最后入库时间.
   */
  public void setLastInTime(LocalDateTime lastInTime) {
    this.lastInTime = lastInTime;
  }

  /**
   * 最后入库时间.
   */
  public LocalDateTime getLastInTime() {
    return this.lastInTime;
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
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
  }


  /**
   * 采购入库通知单编码.
   */
  public void setPurchaseNoticeOrderCode(String purchaseNoticeOrderCode) {
    this.purchaseNoticeOrderCode = purchaseNoticeOrderCode == null ? null : purchaseNoticeOrderCode.trim();
  }

  /**
   * 采购入库通知单编码.
   */
  public String getPurchaseNoticeOrderCode() {
    return this.purchaseNoticeOrderCode;
  }


  /**
   * 采购入库通知单id.
   */
  public void setPurchaseNoticeOrderId(Long purchaseNoticeOrderId) {
    this.purchaseNoticeOrderId = purchaseNoticeOrderId;
  }

  /**
   * 采购入库通知单id.
   */
  public Long getPurchaseNoticeOrderId() {
    return this.purchaseNoticeOrderId;
  }

  /**
   * 采购订单编码.
   */
  public void setPurchaseOrderCode(String purchaseOrderCode) {
    this.purchaseOrderCode = purchaseOrderCode == null ? null : purchaseOrderCode.trim();
  }

  /**
   * 采购订单编码.
   */
  public String getPurchaseOrderCode() {
    return this.purchaseOrderCode;
  }


  /**
   * 采购订单id.
   */
  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  /**
   * 采购订单id.
   */
  public Long getPurchaseOrderId() {
    return this.purchaseOrderId;
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
  public void setStatus(PurchaseNoticeStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public PurchaseNoticeStatus getStatus() {
    return this.status;
  }


  /**
   * 供应商编码.
   */
  public void setSupplierCode(String supplierCode) {
    this.supplierCode = supplierCode == null ? null : supplierCode.trim();
  }

  /**
   * 供应商编码.
   */
  public String getSupplierCode() {
    return this.supplierCode;
  }

  /**
   * 供应商名称.
   */
  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName == null ? null : supplierName.trim();
  }

  /**
   * 供应商名称.
   */
  public String getSupplierName() {
    return this.supplierName;
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
   * 仓库id.
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 仓库id.
   */
  public Long getWarehouseId() {
    return this.warehouseId;
  }

  /**
   * 仓库名称.
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 仓库名称.
   */
  public String getWarehouseName() {
    return this.warehouseName;
  }

  /**
   * 明细.
   */
  public void setDetails(List<PurchaseNoticeOrderDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<PurchaseNoticeOrderDetail> getDetails() {
    return this.details;
  }
}