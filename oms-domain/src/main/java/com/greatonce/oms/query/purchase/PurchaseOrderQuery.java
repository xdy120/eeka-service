package com.greatonce.oms.query.purchase;

import com.greatonce.core.database.Query;
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
public class PurchaseOrderQuery extends Query {
  /**
   * 到货日期开始.
   */
  private LocalDate arrivalDateBegin;
  /**
   * 到货日期结束.
   */
  private LocalDate arrivalDateEnd;
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
   * 合同号.
   */
  private String contractNo;
  /**
   * .
   */
  private List<String> contractNos;
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
   * 快递费.
   */
  private Double expressFee;
  /**
   * 来源单号.
   */
  private String fromCode;
  /**
   * .
   */
  private List<String> fromCodes;
  /**
   * 入库状态.
   */
  private InStatus inStatus;
  /**
   * .
   */
  private List<InStatus> inStatuses;
  /**
   * 最后入库时间开始.
   */
  private LocalDateTime lastInTimeBegin;
  /**
   * 最后入库时间结束.
   */
  private LocalDateTime lastInTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 通知单状态.
   */
  private PurchaseOrderNoticeStatus noticeOrderStatus;
  /**
   * .
   */
  private List<PurchaseOrderNoticeStatus> noticeOrderStatuses;
  /**
   * 采购日期开始.
   */
  private LocalDate purchaseDateBegin;
  /**
   * 采购日期结束.
   */
  private LocalDate purchaseDateEnd;
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
   * 采购人.
   */
  private String purchaser;
  /**
   * 备注.
   */
  private String remark;
  /**
   * .
   */
  private String skuCode;
  /**
   * 状态.
   */
  private PurchaseStatus status;
  /**
   * .
   */
  private List<PurchaseStatus> statuses;
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
   * 到货日期开始.
   * @param arrivalDateBegin 开始.
   */
  public void setArrivalDateBegin(LocalDate arrivalDateBegin) {
    this.arrivalDateBegin = arrivalDateBegin;
  }

  /**
   * 到货日期开始.
   * @return 到货日期开始
   */
  public LocalDate getArrivalDateBegin() {
    return this.arrivalDateBegin;
  }

  /**
   * 到货日期结束.
   * @param arrivalDateEnd 结束
   */
  public void setArrivalDateEnd(LocalDate arrivalDateEnd) {
    this.arrivalDateEnd = arrivalDateEnd;
  }

  /**
   * 到货日期结束.
   * @return 到货日期结束
   */
  public LocalDate getArrivalDateEnd() {
    return this.arrivalDateEnd;
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
   * 合同号.
   * @param contractNo 合同号
   */
  public void setContractNo(String contractNo) {
    this.contractNo = contractNo == null ? null : contractNo.trim();
  }

  /**
   * 合同号.
   * @return 合同号
   */
  public String getContractNo() {
      return this.contractNo;
  }

  /**
   * .
   * @param contractNos 
   */
  public void setContractNos(List<String> contractNos) {
    this.contractNos = contractNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getContractNos() {
      return this.contractNos;
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
   * 快递费.
   * @param expressFee 快递费
   */
  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  /**
   * 快递费.
   * @return 快递费
   */
  public Double getExpressFee() {
      return this.expressFee;
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
   * 入库状态.
   * @param inStatus 入库状态
   */
  public void setInStatus(InStatus inStatus) {
    this.inStatus = inStatus;
  }

  /**
   * 入库状态.
   * @return 入库状态
   */
  public InStatus getInStatus() {
      return this.inStatus;
  }

  /**
   * .
   * @param inStatuses 
   */
  public void setInStatuses(List<InStatus> inStatuses) {
    this.inStatuses = inStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<InStatus> getInStatuses() {
      return this.inStatuses;
  }

  /**
   * 最后入库时间开始.
   * @param lastInTimeBegin 开始.
   */
  public void setLastInTimeBegin(LocalDateTime lastInTimeBegin) {
    this.lastInTimeBegin = lastInTimeBegin;
  }

  /**
   * 最后入库时间开始.
   * @return 最后入库时间开始
   */
  public LocalDateTime getLastInTimeBegin() {
    return this.lastInTimeBegin;
  }

  /**
   * 最后入库时间结束.
   * @param lastInTimeEnd 结束
   */
  public void setLastInTimeEnd(LocalDateTime lastInTimeEnd) {
    this.lastInTimeEnd = lastInTimeEnd;
  }

  /**
   * 最后入库时间结束.
   * @return 最后入库时间结束
   */
  public LocalDateTime getLastInTimeEnd() {
    return this.lastInTimeEnd;
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
   * 通知单状态.
   * @param noticeOrderStatus 通知单状态
   */
  public void setNoticeOrderStatus(PurchaseOrderNoticeStatus noticeOrderStatus) {
    this.noticeOrderStatus = noticeOrderStatus;
  }

  /**
   * 通知单状态.
   * @return 通知单状态
   */
  public PurchaseOrderNoticeStatus getNoticeOrderStatus() {
      return this.noticeOrderStatus;
  }

  /**
   * .
   * @param noticeOrderStatuses 
   */
  public void setNoticeOrderStatuses(List<PurchaseOrderNoticeStatus> noticeOrderStatuses) {
    this.noticeOrderStatuses = noticeOrderStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<PurchaseOrderNoticeStatus> getNoticeOrderStatuses() {
      return this.noticeOrderStatuses;
  }

  /**
   * 采购日期开始.
   * @param purchaseDateBegin 开始.
   */
  public void setPurchaseDateBegin(LocalDate purchaseDateBegin) {
    this.purchaseDateBegin = purchaseDateBegin;
  }

  /**
   * 采购日期开始.
   * @return 采购日期开始
   */
  public LocalDate getPurchaseDateBegin() {
    return this.purchaseDateBegin;
  }

  /**
   * 采购日期结束.
   * @param purchaseDateEnd 结束
   */
  public void setPurchaseDateEnd(LocalDate purchaseDateEnd) {
    this.purchaseDateEnd = purchaseDateEnd;
  }

  /**
   * 采购日期结束.
   * @return 采购日期结束
   */
  public LocalDate getPurchaseDateEnd() {
    return this.purchaseDateEnd;
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
   * 采购人.
   * @param purchaser 采购人
   */
  public void setPurchaser(String purchaser) {
    this.purchaser = purchaser == null ? null : purchaser.trim();
  }

  /**
   * 采购人.
   * @return 采购人
   */
  public String getPurchaser() {
      return this.purchaser;
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
   * .
   * @param skuCode 
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * .
   * @return 
   */
  public String getSkuCode() {
      return this.skuCode;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(PurchaseStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public PurchaseStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<PurchaseStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<PurchaseStatus> getStatuses() {
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