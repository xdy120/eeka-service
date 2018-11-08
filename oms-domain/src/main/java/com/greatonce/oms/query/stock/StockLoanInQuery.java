package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.stock.StockLoanInStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 还入单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockLoanInQuery extends Query {
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
   * 入库状态.
   */
  private InStatus inStatus;
  /**
   * .
   */
  private List<InStatus> inStatuses;
  /**
   * 借调人.
   */
  private String loanUser;
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
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private StockLoanInStatus status;
  /**
   * .
   */
  private List<StockLoanInStatus> statuses;
  /**
   * 还入单编码.
   */
  private String stockLoanInCode;
  /**
   * .
   */
  private List<String> stockLoanInCodes;
  /**
   * 还入单id.
   */
  private Long stockLoanInId;
  /**
   * 借出单编码.
   */
  private String stockLoanOutCode;
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
   * 借调人.
   * @param loanUser 借调人
   */
  public void setLoanUser(String loanUser) {
    this.loanUser = loanUser == null ? null : loanUser.trim();
  }

  /**
   * 借调人.
   * @return 借调人
   */
  public String getLoanUser() {
      return this.loanUser;
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
  public void setStatus(StockLoanInStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public StockLoanInStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<StockLoanInStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<StockLoanInStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 还入单编码.
   * @param stockLoanInCode 还入单编码
   */
  public void setStockLoanInCode(String stockLoanInCode) {
    this.stockLoanInCode = stockLoanInCode == null ? null : stockLoanInCode.trim();
  }

  /**
   * 还入单编码.
   * @return 还入单编码
   */
  public String getStockLoanInCode() {
      return this.stockLoanInCode;
  }

  /**
   * .
   * @param stockLoanInCodes 
   */
  public void setStockLoanInCodes(List<String> stockLoanInCodes) {
    this.stockLoanInCodes = stockLoanInCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getStockLoanInCodes() {
      return this.stockLoanInCodes;
  }

  /**
   * 还入单id.
   * @param stockLoanInId 还入单id
   */
  public void setStockLoanInId(Long stockLoanInId) {
    this.stockLoanInId = stockLoanInId;
  }

  /**
   * 还入单id.
   * @return 还入单id
   */
  public Long getStockLoanInId() {
      return this.stockLoanInId;
  }

  /**
   * 借出单编码.
   * @param stockLoanOutCode 借出单编码
   */
  public void setStockLoanOutCode(String stockLoanOutCode) {
    this.stockLoanOutCode = stockLoanOutCode == null ? null : stockLoanOutCode.trim();
  }

  /**
   * 借出单编码.
   * @return 借出单编码
   */
  public String getStockLoanOutCode() {
      return this.stockLoanOutCode;
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