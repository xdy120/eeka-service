package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.enums.marketing.MemberType;
import com.greatonce.oms.domain.enums.SmsTemplateType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 短信模板.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SmsTemplate extends EnableDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 会员类型.
   */
  private MemberType memberType;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 短信帐号id.
   */
  private Long smsAccountId;
  /**
   * 短信名称.
   */
  private String smsAccountName;
  /**
   * 短信内容.
   */
  private String smsContent;
  /**
   * 短信模版id.
   */
  private Long smsTemplateId;
  /**
   * 短信名称.
   */
  private String smsTemplateName;
  /**
   * 模板类型.
   */
  private SmsTemplateType smsTemplateType;


  @Override
  public void setPrimaryKey(Long pk) {
    this.smsTemplateId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.smsTemplateId;
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
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
  }

  /**
   * 会员类型.
   */
  public void setMemberType(MemberType memberType) {
    this.memberType = memberType;
  }

  /**
   * 会员类型.
   */
  public MemberType getMemberType() {
    return this.memberType;
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
   * 短信帐号id.
   */
  public void setSmsAccountId(Long smsAccountId) {
    this.smsAccountId = smsAccountId;
  }

  /**
   * 短信帐号id.
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
   * 短信内容.
   */
  public void setSmsContent(String smsContent) {
    this.smsContent = smsContent == null ? null : smsContent.trim();
  }

  /**
   * 短信内容.
   */
  public String getSmsContent() {
    return this.smsContent;
  }

  /**
   * 短信模版id.
   */
  public void setSmsTemplateId(Long smsTemplateId) {
    this.smsTemplateId = smsTemplateId;
  }

  /**
   * 短信模版id.
   */
  public Long getSmsTemplateId() {
    return this.smsTemplateId;
  }

  /**
   * 短信名称.
   */
  public void setSmsTemplateName(String smsTemplateName) {
    this.smsTemplateName = smsTemplateName == null ? null : smsTemplateName.trim();
  }

  /**
   * 短信名称.
   */
  public String getSmsTemplateName() {
    return this.smsTemplateName;
  }

  /**
   * 模板类型.
   */
  public void setSmsTemplateType(SmsTemplateType smsTemplateType) {
    this.smsTemplateType = smsTemplateType;
  }

  /**
   * 模板类型.
   */
  public SmsTemplateType getSmsTemplateType() {
    return this.smsTemplateType;
  }
}