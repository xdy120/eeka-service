package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
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
public class SmsAccount extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.smsAccountId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.smsAccountId;
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
   * 短信id.
   */
  public void setSmsAccountId(Long smsAccountId) {
    this.smsAccountId = smsAccountId;
  }

  /**
   * 短信id.
   */
  public Long getSmsAccountId() {
    return this.smsAccountId;
  }

  /**
   * 短信名称.
   */
  public void setSmsAccountName(String smsAccountName) {
    this.smsAccountName = smsAccountName == null ? null : smsAccountName.trim();
  }

  /**
   * 短信名称.
   */
  public String getSmsAccountName() {
    return this.smsAccountName;
  }

  /**
   * 短信帐号.
   */
  public void setSmsAccount(String smsAccount) {
    this.smsAccount = smsAccount == null ? null : smsAccount.trim();
  }

  /**
   * 短信帐号.
   */
  public String getSmsAccount() {
    return this.smsAccount;
  }

  /**
   * 短信密码.
   */
  public void setSmsPassword(String smsPassword) {
    this.smsPassword = smsPassword == null ? null : smsPassword.trim();
  }

  /**
   * 短信密码.
   */
  public String getSmsPassword() {
    return this.smsPassword;
  }

  /**
   * 短信产品编码.
   */
  public void setSmsProduct(String smsProduct) {
    this.smsProduct = smsProduct == null ? null : smsProduct.trim();
  }

  /**
   * 短信产品编码.
   */
  public String getSmsProduct() {
    return this.smsProduct;
  }
}