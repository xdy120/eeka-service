package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class SmsTemplateQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 会员类型.
   */
  private MemberType memberType;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
  }

  /**
   * 会员类型.
   * @param memberType 会员类型
   */
  public void setMemberType(MemberType memberType) {
    this.memberType = memberType;
  }

  /**
   * 会员类型.
   * @return 会员类型
   */
  public MemberType getMemberType() {
      return this.memberType;
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
   * 短信帐号id.
   * @param smsAccountId 短信帐号id
   */
  public void setSmsAccountId(Long smsAccountId) {
    this.smsAccountId = smsAccountId;
  }

  /**
   * 短信帐号id.
   * @return 短信帐号id
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
   * 短信内容.
   * @param smsContent 短信内容
   */
  public void setSmsContent(String smsContent) {
    this.smsContent = smsContent == null ? null : smsContent.trim();
  }

  /**
   * 短信内容.
   * @return 短信内容
   */
  public String getSmsContent() {
      return this.smsContent;
  }

  /**
   * 短信模版id.
   * @param smsTemplateId 短信模版id
   */
  public void setSmsTemplateId(Long smsTemplateId) {
    this.smsTemplateId = smsTemplateId;
  }

  /**
   * 短信模版id.
   * @return 短信模版id
   */
  public Long getSmsTemplateId() {
      return this.smsTemplateId;
  }

  /**
   * 短信名称.
   * @param smsTemplateName 短信名称
   */
  public void setSmsTemplateName(String smsTemplateName) {
    this.smsTemplateName = smsTemplateName == null ? null : smsTemplateName.trim();
  }

  /**
   * 短信名称.
   * @return 短信名称
   */
  public String getSmsTemplateName() {
      return this.smsTemplateName;
  }

  /**
   * 模板类型.
   * @param smsTemplateType 模板类型
   */
  public void setSmsTemplateType(SmsTemplateType smsTemplateType) {
    this.smsTemplateType = smsTemplateType;
  }

  /**
   * 模板类型.
   * @return 模板类型
   */
  public SmsTemplateType getSmsTemplateType() {
      return this.smsTemplateType;
  }
}