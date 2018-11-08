package com.greatonce.oms.query.purchase;

import com.greatonce.core.database.Query;
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
public class SupplierQuery extends Query {
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
   * 邮箱.
   */
  private String email;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * .
   */
  private List<String> supplierCodes;
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
   * 开户帐号名称.
   * @param bankAccountName 开户帐号名称
   */
  public void setBankAccountName(String bankAccountName) {
    this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
  }

  /**
   * 开户帐号名称.
   * @return 开户帐号名称
   */
  public String getBankAccountName() {
      return this.bankAccountName;
  }

  /**
   * 开户帐号.
   * @param bankAccountNo 开户帐号
   */
  public void setBankAccountNo(String bankAccountNo) {
    this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
  }

  /**
   * 开户帐号.
   * @return 开户帐号
   */
  public String getBankAccountNo() {
      return this.bankAccountNo;
  }

  /**
   * 开户行.
   * @param bank 开户行
   */
  public void setBank(String bank) {
    this.bank = bank == null ? null : bank.trim();
  }

  /**
   * 开户行.
   * @return 开户行
   */
  public String getBank() {
      return this.bank;
  }

  /**
   * 公司名称.
   * @param companyName 公司名称
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   * @return 公司名称
   */
  public String getCompanyName() {
      return this.companyName;
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
   * 邮箱.
   * @param email 邮箱
   */
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  /**
   * 邮箱.
   * @return 邮箱
   */
  public String getEmail() {
      return this.email;
  }

  /**
   * 手机.
   * @param mobile 手机
   */
  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  /**
   * 手机.
   * @return 手机
   */
  public String getMobile() {
      return this.mobile;
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
  public void setStatus(SupplierStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public SupplierStatus getStatus() {
      return this.status;
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
   * .
   * @param supplierCodes 
   */
  public void setSupplierCodes(List<String> supplierCodes) {
    this.supplierCodes = supplierCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSupplierCodes() {
      return this.supplierCodes;
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
   * 供应商结算方式.
   * @param supplierSettlementType 供应商结算方式
   */
  public void setSupplierSettlementType(SupplierSettlementType supplierSettlementType) {
    this.supplierSettlementType = supplierSettlementType;
  }

  /**
   * 供应商结算方式.
   * @return 供应商结算方式
   */
  public SupplierSettlementType getSupplierSettlementType() {
      return this.supplierSettlementType;
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
}