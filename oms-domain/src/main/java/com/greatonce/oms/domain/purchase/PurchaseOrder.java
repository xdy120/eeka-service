package com.greatonce.oms.domain.purchase;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.purchase.PurchaseOrderNoticeStatus;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.purchase.PurchaseStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 采购订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PurchaseOrder extends VersionDO {
  /**
   * 到货日期.
   */
  private LocalDate arrivalDate;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 合同号.
   */
  private String contractNo;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 快递费.
   */
  private Double expressFee;
  /**
   * 来源单号.
   */
  private String fromCode;
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
   * 通知单状态.
   */
  private PurchaseOrderNoticeStatus noticeOrderStatus;
  /**
   * 采购日期.
   */
  private LocalDate purchaseDate;
  /**
   * 采购订单编码.
   */
  private String purchaseOrderCode;
  /**
   * 采购订单id.
   */
  private Long purchaseOrderId;
  /**
   * 采购人.
   */
  private String purchaser;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private PurchaseStatus status;
  /**
   * 供应商编码.
   */
  private String supplierCode;
  /**
   * 供应商id.
   */
  private Long supplierId;
  /**
   * 供应商名称.
   */
  private String supplierName;
  /**
   * 供应商简称.
   */
  private String supplierShortName;
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
  private List<PurchaseOrderDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.purchaseOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.purchaseOrderId;
  }


  /**
   * 到货日期.
   */
  public void setArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  /**
   * 到货日期.
   */
  public LocalDate getArrivalDate() {
    return this.arrivalDate;
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
   * 合同号.
   */
  public void setContractNo(String contractNo) {
    this.contractNo = contractNo == null ? null : contractNo.trim();
  }

  /**
   * 合同号.
   */
  public String getContractNo() {
    return this.contractNo;
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
   * 快递费.
   */
  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  /**
   * 快递费.
   */
  public Double getExpressFee() {
    return this.expressFee;
  }

  /**
   * 来源单号.
   */
  public void setFromCode(String fromCode) {
    this.fromCode = fromCode == null ? null : fromCode.trim();
  }

  /**
   * 来源单号.
   */
  public String getFromCode() {
    return this.fromCode;
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
   * 通知单状态.
   */
  public void setNoticeOrderStatus(PurchaseOrderNoticeStatus noticeOrderStatus) {
    this.noticeOrderStatus = noticeOrderStatus;
  }

  /**
   * 通知单状态.
   */
  public PurchaseOrderNoticeStatus getNoticeOrderStatus() {
    return this.noticeOrderStatus;
  }


  /**
   * 采购日期.
   */
  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  /**
   * 采购日期.
   */
  public LocalDate getPurchaseDate() {
    return this.purchaseDate;
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
   * 采购人.
   */
  public void setPurchaser(String purchaser) {
    this.purchaser = purchaser == null ? null : purchaser.trim();
  }

  /**
   * 采购人.
   */
  public String getPurchaser() {
    return this.purchaser;
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
  public void setStatus(PurchaseStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public PurchaseStatus getStatus() {
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
   * 供应商id.
   */
  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  /**
   * 供应商id.
   */
  public Long getSupplierId() {
    return this.supplierId;
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
   * 供应商简称.
   */
  public void setSupplierShortName(String supplierShortName) {
    this.supplierShortName = supplierShortName == null ? null : supplierShortName.trim();
  }

  /**
   * 供应商简称.
   */
  public String getSupplierShortName() {
    return this.supplierShortName;
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
  public void setDetails(List<PurchaseOrderDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<PurchaseOrderDetail> getDetails() {
    return this.details;
  }
}