package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.VersionDO;
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
public class StockLoanIn extends VersionDO {
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
   * 入库状态.
   */
  private InStatus inStatus;
  /**
   * 借调人.
   */
  private String loanUser;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 还入单编码.
   */
  private String stockLoanInCode;
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
   * 明细.
   */
  private List<StockLoanInDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockLoanInId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockLoanInId;
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
   * 借调人.
   */
  public void setLoanUser(String loanUser) {
    this.loanUser = loanUser == null ? null : loanUser.trim();
  }

  /**
   * 借调人.
   */
  public String getLoanUser() {
    return this.loanUser;
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
  public void setStatus(StockLoanInStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public StockLoanInStatus getStatus() {
    return this.status;
  }


  /**
   * 还入单编码.
   */
  public void setStockLoanInCode(String stockLoanInCode) {
    this.stockLoanInCode = stockLoanInCode == null ? null : stockLoanInCode.trim();
  }

  /**
   * 还入单编码.
   */
  public String getStockLoanInCode() {
    return this.stockLoanInCode;
  }


  /**
   * 还入单id.
   */
  public void setStockLoanInId(Long stockLoanInId) {
    this.stockLoanInId = stockLoanInId;
  }

  /**
   * 还入单id.
   */
  public Long getStockLoanInId() {
    return this.stockLoanInId;
  }

  /**
   * 借出单编码.
   */
  public void setStockLoanOutCode(String stockLoanOutCode) {
    this.stockLoanOutCode = stockLoanOutCode == null ? null : stockLoanOutCode.trim();
  }

  /**
   * 借出单编码.
   */
  public String getStockLoanOutCode() {
    return this.stockLoanOutCode;
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
  public void setDetails(List<StockLoanInDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<StockLoanInDetail> getDetails() {
    return this.details;
  }
}