package com.greatonce.oms.query.purchase;

import com.greatonce.core.database.Query;
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
public class PurchaseNoticeOrderQuery extends Query {
  /**
   * 批次号.
   */
  private String batchNo;
  /**
   * .
   */
  private List<String> batchNos;
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
   * 外部单号.
   */
  private String outerCode;
  /**
   * .
   */
  private List<String> outerCodes;
  /**
   * 采购入库通知单编码.
   */
  private String purchaseNoticeOrderCode;
  /**
   * .
   */
  private List<String> purchaseNoticeOrderCodes;
  /**
   * 采购入库通知单id.
   */
  private Long purchaseNoticeOrderId;
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
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private PurchaseNoticeStatus status;
  /**
   * .
   */
  private List<PurchaseNoticeStatus> statuses;
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
   * 批次号.
   * @param batchNo 批次号
   */
  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo == null ? null : batchNo.trim();
  }

  /**
   * 批次号.
   * @return 批次号
   */
  public String getBatchNo() {
      return this.batchNo;
  }

  /**
   * .
   * @param batchNos 
   */
  public void setBatchNos(List<String> batchNos) {
    this.batchNos = batchNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getBatchNos() {
      return this.batchNos;
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
   * 采购入库通知单编码.
   * @param purchaseNoticeOrderCode 采购入库通知单编码
   */
  public void setPurchaseNoticeOrderCode(String purchaseNoticeOrderCode) {
    this.purchaseNoticeOrderCode = purchaseNoticeOrderCode == null ? null : purchaseNoticeOrderCode.trim();
  }

  /**
   * 采购入库通知单编码.
   * @return 采购入库通知单编码
   */
  public String getPurchaseNoticeOrderCode() {
      return this.purchaseNoticeOrderCode;
  }

  /**
   * .
   * @param purchaseNoticeOrderCodes 
   */
  public void setPurchaseNoticeOrderCodes(List<String> purchaseNoticeOrderCodes) {
    this.purchaseNoticeOrderCodes = purchaseNoticeOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getPurchaseNoticeOrderCodes() {
      return this.purchaseNoticeOrderCodes;
  }

  /**
   * 采购入库通知单id.
   * @param purchaseNoticeOrderId 采购入库通知单id
   */
  public void setPurchaseNoticeOrderId(Long purchaseNoticeOrderId) {
    this.purchaseNoticeOrderId = purchaseNoticeOrderId;
  }

  /**
   * 采购入库通知单id.
   * @return 采购入库通知单id
   */
  public Long getPurchaseNoticeOrderId() {
      return this.purchaseNoticeOrderId;
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
  public void setStatus(PurchaseNoticeStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public PurchaseNoticeStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<PurchaseNoticeStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<PurchaseNoticeStatus> getStatuses() {
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