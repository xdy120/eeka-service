package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
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
public class StockLoanOutQuery extends Query {
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
   * 品牌编码.
   */
  private String brandCode;
  /**
   * 品牌名称.
   */
  private String brandName;
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
   * 预计归还时间开始.
   */
  private LocalDate expectReturnDateBegin;
  /**
   * 预计归还时间结束.
   */
  private LocalDate expectReturnDateEnd;
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
  private Boolean overdue;
  /**
   * .
   */
  private List<String> productCodes;
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
  private StockLoanOutStatus status;
  /**
   * .
   */
  private List<StockLoanOutStatus> statuses;
  /**
   * 借出单编码.
   */
  private String stockLoanOutCode;
  /**
   * .
   */
  private List<String> stockLoanOutCodes;
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
   * 品牌编码.
   * @param brandCode 品牌编码
   */
  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode == null ? null : brandCode.trim();
  }

  /**
   * 品牌编码.
   * @return 品牌编码
   */
  public String getBrandCode() {
      return this.brandCode;
  }

  /**
   * 品牌名称.
   * @param brandName 品牌名称
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName == null ? null : brandName.trim();
  }

  /**
   * 品牌名称.
   * @return 品牌名称
   */
  public String getBrandName() {
      return this.brandName;
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
   * 预计归还时间开始.
   * @param expectReturnDateBegin 开始.
   */
  public void setExpectReturnDateBegin(LocalDate expectReturnDateBegin) {
    this.expectReturnDateBegin = expectReturnDateBegin;
  }

  /**
   * 预计归还时间开始.
   * @return 预计归还时间开始
   */
  public LocalDate getExpectReturnDateBegin() {
    return this.expectReturnDateBegin;
  }

  /**
   * 预计归还时间结束.
   * @param expectReturnDateEnd 结束
   */
  public void setExpectReturnDateEnd(LocalDate expectReturnDateEnd) {
    this.expectReturnDateEnd = expectReturnDateEnd;
  }

  /**
   * 预计归还时间结束.
   * @return 预计归还时间结束
   */
  public LocalDate getExpectReturnDateEnd() {
    return this.expectReturnDateEnd;
  }

  /**
   * 是否需要归还.
   * @param needReturn 是否需要归还
   */
  public void setNeedReturn(Boolean needReturn) {
    this.needReturn = needReturn;
  }

  /**
   * 是否需要归还.
   * @return 是否需要归还
   */
  public Boolean isNeedReturn() {
      return this.needReturn;
  }

  /**
   * 借调类型.
   * @param loanType 借调类型
   */
  public void setLoanType(String loanType) {
    this.loanType = loanType == null ? null : loanType.trim();
  }

  /**
   * 借调类型.
   * @return 借调类型
   */
  public String getLoanType() {
      return this.loanType;
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
   * @param overdue 
   */
  public void setOverdue(Boolean overdue) {
    this.overdue = overdue;
  }

  /**
   * .
   * @return 
   */
  public Boolean getOverdue() {
      return this.overdue;
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
  public void setStatus(StockLoanOutStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public StockLoanOutStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<StockLoanOutStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<StockLoanOutStatus> getStatuses() {
      return this.statuses;
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
   * .
   * @param stockLoanOutCodes 
   */
  public void setStockLoanOutCodes(List<String> stockLoanOutCodes) {
    this.stockLoanOutCodes = stockLoanOutCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getStockLoanOutCodes() {
      return this.stockLoanOutCodes;
  }

  /**
   * 借出单id.
   * @param stockLoanOutId 借出单id
   */
  public void setStockLoanOutId(Long stockLoanOutId) {
    this.stockLoanOutId = stockLoanOutId;
  }

  /**
   * 借出单id.
   * @return 借出单id
   */
  public Long getStockLoanOutId() {
      return this.stockLoanOutId;
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