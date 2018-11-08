package com.greatonce.oms.domain.purchase;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.purchase.PurchaseReturnStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 采购退货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PurchaseReturnOrder extends VersionDO {
  /**
   * 地址.
   */
  private String address;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 联系人.
   */
  private String contact;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 来源单号.
   */
  private String fromCode;
  /**
   * 最后出库时间.
   */
  private LocalDateTime lastOutTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 出库状态.
   */
  private OutStatus outStatus;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 采购订单编码.
   */
  private String purchaseOrderCode;
  /**
   * 采购订单id.
   */
  private Long purchaseOrderId;
  /**
   * 采购退货单编码.
   */
  private String purchaseReturnOrderCode;
  /**
   * 采购退货单id.
   */
  private Long purchaseReturnOrderId;
  /**
   * 退货类型.
   */
  private String purchaseReturnType;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private PurchaseReturnStatus status;
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
   * 电话.
   */
  private String telephone;
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
  private List<PurchaseReturnOrderDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.purchaseReturnOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.purchaseReturnOrderId;
  }


  /**
   * 地址.
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   */
  public String getAddress() {
    return this.address;
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
   * 联系人.
   */
  public void setContact(String contact) {
    this.contact = contact == null ? null : contact.trim();
  }

  /**
   * 联系人.
   */
  public String getContact() {
    return this.contact;
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
   * 最后出库时间.
   */
  public void setLastOutTime(LocalDateTime lastOutTime) {
    this.lastOutTime = lastOutTime;
  }

  /**
   * 最后出库时间.
   */
  public LocalDateTime getLastOutTime() {
    return this.lastOutTime;
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
   * 出库状态.
   */
  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  /**
   * 出库状态.
   */
  public OutStatus getOutStatus() {
    return this.outStatus;
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
   * 采购退货单编码.
   */
  public void setPurchaseReturnOrderCode(String purchaseReturnOrderCode) {
    this.purchaseReturnOrderCode = purchaseReturnOrderCode == null ? null : purchaseReturnOrderCode.trim();
  }

  /**
   * 采购退货单编码.
   */
  public String getPurchaseReturnOrderCode() {
    return this.purchaseReturnOrderCode;
  }


  /**
   * 采购退货单id.
   */
  public void setPurchaseReturnOrderId(Long purchaseReturnOrderId) {
    this.purchaseReturnOrderId = purchaseReturnOrderId;
  }

  /**
   * 采购退货单id.
   */
  public Long getPurchaseReturnOrderId() {
    return this.purchaseReturnOrderId;
  }

  /**
   * 退货类型.
   */
  public void setPurchaseReturnType(String purchaseReturnType) {
    this.purchaseReturnType = purchaseReturnType == null ? null : purchaseReturnType.trim();
  }

  /**
   * 退货类型.
   */
  public String getPurchaseReturnType() {
    return this.purchaseReturnType;
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
  public void setStatus(PurchaseReturnStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public PurchaseReturnStatus getStatus() {
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
   * 电话.
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   */
  public String getTelephone() {
    return this.telephone;
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
  public void setDetails(List<PurchaseReturnOrderDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<PurchaseReturnOrderDetail> getDetails() {
    return this.details;
  }
}