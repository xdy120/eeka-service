package com.greatonce.oms.query.purchase;

import com.greatonce.core.database.Query;
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
public class PurchaseReturnOrderQuery extends Query {
  /**
   * 地址.
   */
  private String address;
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
   * 联系人.
   */
  private String contact;
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
   * 来源单号.
   */
  private String fromCode;
  /**
   * .
   */
  private List<String> fromCodes;
  /**
   * 最后出库时间开始.
   */
  private LocalDateTime lastOutTimeBegin;
  /**
   * 最后出库时间结束.
   */
  private LocalDateTime lastOutTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 出库状态.
   */
  private OutStatus outStatus;
  /**
   * .
   */
  private List<OutStatus> outStatuses;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * .
   */
  private List<String> outerCodes;
  /**
   * 采购订单编码.
   */
  private String purchaseOrderCode;
  /**
   * .
   */
  private List<String> purchaseOrderCodes;
  /**
   * 采购订单id.
   */
  private Long purchaseOrderId;
  /**
   * 采购退货单编码.
   */
  private String purchaseReturnOrderCode;
  /**
   * .
   */
  private List<String> purchaseReturnOrderCodes;
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
   * .
   */
  private List<PurchaseReturnStatus> statuses;
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
   * 地址.
   * @param address 地址
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   * @return 地址
   */
  public String getAddress() {
      return this.address;
  }

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
   * 联系人.
   * @param contact 联系人
   */
  public void setContact(String contact) {
    this.contact = contact == null ? null : contact.trim();
  }

  /**
   * 联系人.
   * @return 联系人
   */
  public String getContact() {
      return this.contact;
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
   * 来源单号.
   * @param fromCode 来源单号
   */
  public void setFromCode(String fromCode) {
    this.fromCode = fromCode == null ? null : fromCode.trim();
  }

  /**
   * 来源单号.
   * @return 来源单号
   */
  public String getFromCode() {
      return this.fromCode;
  }

  /**
   * .
   * @param fromCodes 
   */
  public void setFromCodes(List<String> fromCodes) {
    this.fromCodes = fromCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getFromCodes() {
      return this.fromCodes;
  }

  /**
   * 最后出库时间开始.
   * @param lastOutTimeBegin 开始.
   */
  public void setLastOutTimeBegin(LocalDateTime lastOutTimeBegin) {
    this.lastOutTimeBegin = lastOutTimeBegin;
  }

  /**
   * 最后出库时间开始.
   * @return 最后出库时间开始
   */
  public LocalDateTime getLastOutTimeBegin() {
    return this.lastOutTimeBegin;
  }

  /**
   * 最后出库时间结束.
   * @param lastOutTimeEnd 结束
   */
  public void setLastOutTimeEnd(LocalDateTime lastOutTimeEnd) {
    this.lastOutTimeEnd = lastOutTimeEnd;
  }

  /**
   * 最后出库时间结束.
   * @return 最后出库时间结束
   */
  public LocalDateTime getLastOutTimeEnd() {
    return this.lastOutTimeEnd;
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
   * 出库状态.
   * @param outStatus 出库状态
   */
  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  /**
   * 出库状态.
   * @return 出库状态
   */
  public OutStatus getOutStatus() {
      return this.outStatus;
  }

  /**
   * .
   * @param outStatuses 
   */
  public void setOutStatuses(List<OutStatus> outStatuses) {
    this.outStatuses = outStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<OutStatus> getOutStatuses() {
      return this.outStatuses;
  }

  /**
   * 外部单号.
   * @param outerCode 外部单号
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   * @return 外部单号
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * .
   * @param outerCodes 
   */
  public void setOuterCodes(List<String> outerCodes) {
    this.outerCodes = outerCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOuterCodes() {
      return this.outerCodes;
  }

  /**
   * 采购订单编码.
   * @param purchaseOrderCode 采购订单编码
   */
  public void setPurchaseOrderCode(String purchaseOrderCode) {
    this.purchaseOrderCode = purchaseOrderCode == null ? null : purchaseOrderCode.trim();
  }

  /**
   * 采购订单编码.
   * @return 采购订单编码
   */
  public String getPurchaseOrderCode() {
      return this.purchaseOrderCode;
  }

  /**
   * .
   * @param purchaseOrderCodes 
   */
  public void setPurchaseOrderCodes(List<String> purchaseOrderCodes) {
    this.purchaseOrderCodes = purchaseOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getPurchaseOrderCodes() {
      return this.purchaseOrderCodes;
  }

  /**
   * 采购订单id.
   * @param purchaseOrderId 采购订单id
   */
  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  /**
   * 采购订单id.
   * @return 采购订单id
   */
  public Long getPurchaseOrderId() {
      return this.purchaseOrderId;
  }

  /**
   * 采购退货单编码.
   * @param purchaseReturnOrderCode 采购退货单编码
   */
  public void setPurchaseReturnOrderCode(String purchaseReturnOrderCode) {
    this.purchaseReturnOrderCode = purchaseReturnOrderCode == null ? null : purchaseReturnOrderCode.trim();
  }

  /**
   * 采购退货单编码.
   * @return 采购退货单编码
   */
  public String getPurchaseReturnOrderCode() {
      return this.purchaseReturnOrderCode;
  }

  /**
   * .
   * @param purchaseReturnOrderCodes 
   */
  public void setPurchaseReturnOrderCodes(List<String> purchaseReturnOrderCodes) {
    this.purchaseReturnOrderCodes = purchaseReturnOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getPurchaseReturnOrderCodes() {
      return this.purchaseReturnOrderCodes;
  }

  /**
   * 采购退货单id.
   * @param purchaseReturnOrderId 采购退货单id
   */
  public void setPurchaseReturnOrderId(Long purchaseReturnOrderId) {
    this.purchaseReturnOrderId = purchaseReturnOrderId;
  }

  /**
   * 采购退货单id.
   * @return 采购退货单id
   */
  public Long getPurchaseReturnOrderId() {
      return this.purchaseReturnOrderId;
  }

  /**
   * 退货类型.
   * @param purchaseReturnType 退货类型
   */
  public void setPurchaseReturnType(String purchaseReturnType) {
    this.purchaseReturnType = purchaseReturnType == null ? null : purchaseReturnType.trim();
  }

  /**
   * 退货类型.
   * @return 退货类型
   */
  public String getPurchaseReturnType() {
      return this.purchaseReturnType;
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
  public void setStatus(PurchaseReturnStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public PurchaseReturnStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<PurchaseReturnStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<PurchaseReturnStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 供应商编码.
   * @param supplierCode 供应商编码
   */
  public void setSupplierCode(String supplierCode) {
    this.supplierCode = supplierCode == null ? null : supplierCode.trim();
  }

  /**
   * 供应商编码.
   * @return 供应商编码
   */
  public String getSupplierCode() {
      return this.supplierCode;
  }

  /**
   * 供应商id.
   * @param supplierId 供应商id
   */
  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  /**
   * 供应商id.
   * @return 供应商id
   */
  public Long getSupplierId() {
      return this.supplierId;
  }

  /**
   * 供应商名称.
   * @param supplierName 供应商名称
   */
  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName == null ? null : supplierName.trim();
  }

  /**
   * 供应商名称.
   * @return 供应商名称
   */
  public String getSupplierName() {
      return this.supplierName;
  }

  /**
   * 供应商简称.
   * @param supplierShortName 供应商简称
   */
  public void setSupplierShortName(String supplierShortName) {
    this.supplierShortName = supplierShortName == null ? null : supplierShortName.trim();
  }

  /**
   * 供应商简称.
   * @return 供应商简称
   */
  public String getSupplierShortName() {
      return this.supplierShortName;
  }

  /**
   * 电话.
   * @param telephone 电话
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   * @return 电话
   */
  public String getTelephone() {
      return this.telephone;
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

  /**
   * 仓库id.
   * @param warehouseId 仓库id
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 仓库id.
   * @return 仓库id
   */
  public Long getWarehouseId() {
      return this.warehouseId;
  }

  /**
   * 仓库名称.
   * @param warehouseName 仓库名称
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 仓库名称.
   * @return 仓库名称
   */
  public String getWarehouseName() {
      return this.warehouseName;
  }
}