package com.greatonce.oms.domain.purchase;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.purchase.SupplierSettlementType;
import com.greatonce.oms.domain.enums.purchase.SupplierStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 供应商.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Supplier extends BaseDO {
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
   * 开户帐号名称.
   */
  private String bankAccountName;
  /**
   * 开户帐号.
   */
  private String bankAccountNo;
  /**
   * 开户行.
   */
  private String bank;
  /**
   * 公司名称.
   */
  private String companyName;
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
   * 邮箱.
   */
  private String email;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private SupplierStatus status;
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
   * 供应商结算方式.
   */
  private SupplierSettlementType supplierSettlementType;
  /**
   * 供应商简称.
   */
  private String supplierShortName;
  /**
   * 电话.
   */
  private String telephone;


  @Override
  public void setPrimaryKey(Long pk) {
    this.supplierId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.supplierId;
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
   * 开户帐号名称.
   */
  public void setBankAccountName(String bankAccountName) {
    this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
  }

  /**
   * 开户帐号名称.
   */
  public String getBankAccountName() {
    return this.bankAccountName;
  }

  /**
   * 开户帐号.
   */
  public void setBankAccountNo(String bankAccountNo) {
    this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
  }

  /**
   * 开户帐号.
   */
  public String getBankAccountNo() {
    return this.bankAccountNo;
  }

  /**
   * 开户行.
   */
  public void setBank(String bank) {
    this.bank = bank == null ? null : bank.trim();
  }

  /**
   * 开户行.
   */
  public String getBank() {
    return this.bank;
  }

  /**
   * 公司名称.
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   */
  public String getCompanyName() {
    return this.companyName;
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
   * 邮箱.
   */
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  /**
   * 邮箱.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * 手机.
   */
  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  /**
   * 手机.
   */
  public String getMobile() {
    return this.mobile;
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
  public void setStatus(SupplierStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public SupplierStatus getStatus() {
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
   * 供应商结算方式.
   */
  public void setSupplierSettlementType(SupplierSettlementType supplierSettlementType) {
    this.supplierSettlementType = supplierSettlementType;
  }

  /**
   * 供应商结算方式.
   */
  public SupplierSettlementType getSupplierSettlementType() {
    return this.supplierSettlementType;
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
}