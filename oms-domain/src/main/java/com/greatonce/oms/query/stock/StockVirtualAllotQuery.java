package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
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
public class StockVirtualAllotQuery extends Query {
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
   * 入库虚拟仓id.
   */
  private Long inVirtualWarehouseId;
  /**
   * .
   */
  private List<Long> inVirtualWarehouseIds;
  /**
   * 入库虚拟仓名称.
   */
  private String inVirtualWarehouseName;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 出库虚拟仓id.
   */
  private Long outVirtualWarehouseId;
  /**
   * .
   */
  private List<Long> outVirtualWarehouseIds;
  /**
   * 出库虚拟仓名称.
   */
  private String outVirtualWarehouseName;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * .
   */
  private List<String> productCodes;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 备注.
   */
  private String remark;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * 状态.
   */
  private StockVirtualAllotStatus status;
  /**
   * .
   */
  private List<StockVirtualAllotStatus> statuses;
  /**
   * 虚拟调拨单编码.
   */
  private String stockVirtualAllotCode;
  /**
   * .
   */
  private List<String> stockVirtualAllotCodes;
  /**
   * 虚拟调拨id.
   */
  private Long stockVirtualAllotId;
  /**
   * 版本.
   */
  private Integer version;


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
   * 入库虚拟仓id.
   * @param inVirtualWarehouseId 入库虚拟仓id
   */
  public void setInVirtualWarehouseId(Long inVirtualWarehouseId) {
    this.inVirtualWarehouseId = inVirtualWarehouseId;
  }

  /**
   * 入库虚拟仓id.
   * @return 入库虚拟仓id
   */
  public Long getInVirtualWarehouseId() {
      return this.inVirtualWarehouseId;
  }

  /**
   * .
   * @param inVirtualWarehouseIds 
   */
  public void setInVirtualWarehouseIds(List<Long> inVirtualWarehouseIds) {
    this.inVirtualWarehouseIds = inVirtualWarehouseIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getInVirtualWarehouseIds() {
      return this.inVirtualWarehouseIds;
  }

  /**
   * 入库虚拟仓名称.
   * @param inVirtualWarehouseName 入库虚拟仓名称
   */
  public void setInVirtualWarehouseName(String inVirtualWarehouseName) {
    this.inVirtualWarehouseName = inVirtualWarehouseName == null ? null : inVirtualWarehouseName.trim();
  }

  /**
   * 入库虚拟仓名称.
   * @return 入库虚拟仓名称
   */
  public String getInVirtualWarehouseName() {
      return this.inVirtualWarehouseName;
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
   * 出库虚拟仓id.
   * @param outVirtualWarehouseId 出库虚拟仓id
   */
  public void setOutVirtualWarehouseId(Long outVirtualWarehouseId) {
    this.outVirtualWarehouseId = outVirtualWarehouseId;
  }

  /**
   * 出库虚拟仓id.
   * @return 出库虚拟仓id
   */
  public Long getOutVirtualWarehouseId() {
      return this.outVirtualWarehouseId;
  }

  /**
   * .
   * @param outVirtualWarehouseIds 
   */
  public void setOutVirtualWarehouseIds(List<Long> outVirtualWarehouseIds) {
    this.outVirtualWarehouseIds = outVirtualWarehouseIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getOutVirtualWarehouseIds() {
      return this.outVirtualWarehouseIds;
  }

  /**
   * 出库虚拟仓名称.
   * @param outVirtualWarehouseName 出库虚拟仓名称
   */
  public void setOutVirtualWarehouseName(String outVirtualWarehouseName) {
    this.outVirtualWarehouseName = outVirtualWarehouseName == null ? null : outVirtualWarehouseName.trim();
  }

  /**
   * 出库虚拟仓名称.
   * @return 出库虚拟仓名称
   */
  public String getOutVirtualWarehouseName() {
      return this.outVirtualWarehouseName;
  }

  /**
   * 计划数量.
   * @param planQuantity 计划数量
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   * @return 计划数量
   */
  public Integer getPlanQuantity() {
      return this.planQuantity;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
  }

  /**
   * 数量.
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   * @return 数量
   */
  public Integer getQuantity() {
      return this.quantity;
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
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(StockVirtualAllotStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public StockVirtualAllotStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<StockVirtualAllotStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<StockVirtualAllotStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 虚拟调拨单编码.
   * @param stockVirtualAllotCode 虚拟调拨单编码
   */
  public void setStockVirtualAllotCode(String stockVirtualAllotCode) {
    this.stockVirtualAllotCode = stockVirtualAllotCode == null ? null : stockVirtualAllotCode.trim();
  }

  /**
   * 虚拟调拨单编码.
   * @return 虚拟调拨单编码
   */
  public String getStockVirtualAllotCode() {
      return this.stockVirtualAllotCode;
  }

  /**
   * .
   * @param stockVirtualAllotCodes 
   */
  public void setStockVirtualAllotCodes(List<String> stockVirtualAllotCodes) {
    this.stockVirtualAllotCodes = stockVirtualAllotCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getStockVirtualAllotCodes() {
      return this.stockVirtualAllotCodes;
  }

  /**
   * 虚拟调拨id.
   * @param stockVirtualAllotId 虚拟调拨id
   */
  public void setStockVirtualAllotId(Long stockVirtualAllotId) {
    this.stockVirtualAllotId = stockVirtualAllotId;
  }

  /**
   * 虚拟调拨id.
   * @return 虚拟调拨id
   */
  public Long getStockVirtualAllotId() {
      return this.stockVirtualAllotId;
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
}