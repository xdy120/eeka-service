package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.stock.StockLoanOutStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 借出单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockLoanOut extends VersionDO {
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
   * 品牌编码.
   */
  private String brandCode;
  /**
   * 品牌名称.
   */
  private String brandName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 预计归还时间.
   */
  private LocalDate expectReturnDate;
  /**
   * 是否需要归还.
   */
  private Boolean needReturn;
  /**
   * 借调类型.
   */
  private String loanType;
  /**
   * 借调人.
   */
  private String loanUser;
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
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private StockLoanOutStatus status;
  /**
   * 借出单编码.
   */
  private String stockLoanOutCode;
  /**
   * 借出单id.
   */
  private Long stockLoanOutId;
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
  private List<StockLoanOutDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockLoanOutId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockLoanOutId;
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
   * 品牌编码.
   */
  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode == null ? null : brandCode.trim();
  }

  /**
   * 品牌编码.
   */
  public String getBrandCode() {
    return this.brandCode;
  }

  /**
   * 品牌名称.
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName == null ? null : brandName.trim();
  }

  /**
   * 品牌名称.
   */
  public String getBrandName() {
    return this.brandName;
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
   * 预计归还时间.
   */
  public void setExpectReturnDate(LocalDate expectReturnDate) {
    this.expectReturnDate = expectReturnDate;
  }

  /**
   * 预计归还时间.
   */
  public LocalDate getExpectReturnDate() {
    return this.expectReturnDate;
  }

  /**
   * 是否需要归还.
   */
  public void setNeedReturn(Boolean needReturn) {
    this.needReturn = needReturn;
  }

  /**
   * 是否需要归还.
   */
  public Boolean isNeedReturn() {
    return this.needReturn;
  }

  /**
   * 借调类型.
   */
  public void setLoanType(String loanType) {
    this.loanType = loanType == null ? null : loanType.trim();
  }

  /**
   * 借调类型.
   */
  public String getLoanType() {
    return this.loanType;
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
  public void setStatus(StockLoanOutStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public StockLoanOutStatus getStatus() {
    return this.status;
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
   * 借出单id.
   */
  public void setStockLoanOutId(Long stockLoanOutId) {
    this.stockLoanOutId = stockLoanOutId;
  }

  /**
   * 借出单id.
   */
  public Long getStockLoanOutId() {
    return this.stockLoanOutId;
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
  public void setDetails(List<StockLoanOutDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<StockLoanOutDetail> getDetails() {
    return this.details;
  }
}