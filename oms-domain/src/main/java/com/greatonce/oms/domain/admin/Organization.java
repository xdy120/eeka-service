package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.BaseDO;
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
public class Organization extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 域名.
   */
  private String domain;
  /**
   * 邮箱.
   */
  private String email;
  /**
   * 过期时间.
   */
  private LocalDate expireDate;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 上线时间.
   */
  private LocalDate onlineDate;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.organizationId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.organizationId;
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
   * 域名.
   */
  public void setDomain(String domain) {
    this.domain = domain == null ? null : domain.trim();
  }

  /**
   * 域名.
   */
  public String getDomain() {
    return this.domain;
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
   * 过期时间.
   */
  public void setExpireDate(LocalDate expireDate) {
    this.expireDate = expireDate;
  }

  /**
   * 过期时间.
   */
  public LocalDate getExpireDate() {
    return this.expireDate;
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
   * 上线时间.
   */
  public void setOnlineDate(LocalDate onlineDate) {
    this.onlineDate = onlineDate;
  }

  /**
   * 上线时间.
   */
  public LocalDate getOnlineDate() {
    return this.onlineDate;
  }

  /**
   * 组织id.
   */
  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  /**
   * 组织id.
   */
  public Long getOrganizationId() {
    return this.organizationId;
  }

  /**
   * 组织名称.
   */
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName == null ? null : organizationName.trim();
  }

  /**
   * 组织名称.
   */
  public String getOrganizationName() {
    return this.organizationName;
  }

  /**
   * 状态.
   */
  public void setStatus(OrganizationStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public OrganizationStatus getStatus() {
    return this.status;
  }
}