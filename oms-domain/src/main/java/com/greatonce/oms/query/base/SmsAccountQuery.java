package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 短信帐号.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SmsAccountQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 短信id.
   */
  private Long smsAccountId;
  /**
   * 短信名称.
   */
  private String smsAccountName;
  /**
   * 短信帐号.
   */
  private String smsAccount;
  /**
   * 短信密码.
   */
  private String smsPassword;
  /**
   * 短信产品编码.
   */
  private String smsProduct;


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
   * 短信id.
   * @param smsAccountId 短信id
   */
  public void setSmsAccountId(Long smsAccountId) {
    this.smsAccountId = smsAccountId;
  }

  /**
   * 短信id.
   * @return 短信id
   */
  public Long getSmsAccountId() {
      return this.smsAccountId;
  }

  /**
   * 短信名称.
   * @param smsAccountName 短信名称
   */
  public void setSmsAccountName(String smsAccountName) {
    this.smsAccountName = smsAccountName == null ? null : smsAccountName.trim();
  }

  /**
   * 短信名称.
   * @return 短信名称
   */
  public String getSmsAccountName() {
      return this.smsAccountName;
  }

  /**
   * 短信帐号.
   * @param smsAccount 短信帐号
   */
  public void setSmsAccount(String smsAccount) {
    this.smsAccount = smsAccount == null ? null : smsAccount.trim();
  }

  /**
   * 短信帐号.
   * @return 短信帐号
   */
  public String getSmsAccount() {
      return this.smsAccount;
  }

  /**
   * 短信密码.
   * @param smsPassword 短信密码
   */
  public void setSmsPassword(String smsPassword) {
    this.smsPassword = smsPassword == null ? null : smsPassword.trim();
  }

  /**
   * 短信密码.
   * @return 短信密码
   */
  public String getSmsPassword() {
      return this.smsPassword;
  }

  /**
   * 短信产品编码.
   * @param smsProduct 短信产品编码
   */
  public void setSmsProduct(String smsProduct) {
    this.smsProduct = smsProduct == null ? null : smsProduct.trim();
  }

  /**
   * 短信产品编码.
   * @return 短信产品编码
   */
  public String getSmsProduct() {
      return this.smsProduct;
  }
}