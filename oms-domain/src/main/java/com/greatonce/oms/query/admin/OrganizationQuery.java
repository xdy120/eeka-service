package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.OrganizationStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 组织.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class OrganizationQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 域名.
   */
  private String domain;
  /**
   * 邮箱.
   */
  private String email;
  /**
   * 过期时间开始.
   */
  private LocalDate expireDateBegin;
  /**
   * 过期时间结束.
   */
  private LocalDate expireDateEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 上线时间开始.
   */
  private LocalDate onlineDateBegin;
  /**
   * 上线时间结束.
   */
  private LocalDate onlineDateEnd;
  /**
   * 组织id.
   */
  private Long organizationId;
  /**
   * 组织名称.
   */
  private String organizationName;
  /**
   * 状态.
   */
  private OrganizationStatus status;


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
   * 域名.
   * @param domain 域名
   */
  public void setDomain(String domain) {
    this.domain = domain == null ? null : domain.trim();
  }

  /**
   * 域名.
   * @return 域名
   */
  public String getDomain() {
      return this.domain;
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
   * 过期时间开始.
   * @param expireDateBegin 开始.
   */
  public void setExpireDateBegin(LocalDate expireDateBegin) {
    this.expireDateBegin = expireDateBegin;
  }

  /**
   * 过期时间开始.
   * @return 过期时间开始
   */
  public LocalDate getExpireDateBegin() {
    return this.expireDateBegin;
  }

  /**
   * 过期时间结束.
   * @param expireDateEnd 结束
   */
  public void setExpireDateEnd(LocalDate expireDateEnd) {
    this.expireDateEnd = expireDateEnd;
  }

  /**
   * 过期时间结束.
   * @return 过期时间结束
   */
  public LocalDate getExpireDateEnd() {
    return this.expireDateEnd;
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
   * 上线时间开始.
   * @param onlineDateBegin 开始.
   */
  public void setOnlineDateBegin(LocalDate onlineDateBegin) {
    this.onlineDateBegin = onlineDateBegin;
  }

  /**
   * 上线时间开始.
   * @return 上线时间开始
   */
  public LocalDate getOnlineDateBegin() {
    return this.onlineDateBegin;
  }

  /**
   * 上线时间结束.
   * @param onlineDateEnd 结束
   */
  public void setOnlineDateEnd(LocalDate onlineDateEnd) {
    this.onlineDateEnd = onlineDateEnd;
  }

  /**
   * 上线时间结束.
   * @return 上线时间结束
   */
  public LocalDate getOnlineDateEnd() {
    return this.onlineDateEnd;
  }

  /**
   * 组织id.
   * @param organizationId 组织id
   */
  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  /**
   * 组织id.
   * @return 组织id
   */
  public Long getOrganizationId() {
      return this.organizationId;
  }

  /**
   * 组织名称.
   * @param organizationName 组织名称
   */
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName == null ? null : organizationName.trim();
  }

  /**
   * 组织名称.
   * @return 组织名称
   */
  public String getOrganizationName() {
      return this.organizationName;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(OrganizationStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public OrganizationStatus getStatus() {
      return this.status;
  }
}