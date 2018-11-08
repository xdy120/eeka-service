package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.EnableDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 第三方应用授权.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ThirdAppAuth extends EnableDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 到期时间.
   */
  private LocalDateTime expireTime;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 组织id.
   */
  private Long organizationId;
  /**
   * 标识.
   */
  private Long thirdAppAuthId;
  /**
   * 第三方应用id.
   */
  private Long thirdAppId;
  /**
   * 授权码.
   */
  private String token;


  @Override
  public void setPrimaryKey(Long pk) {
    this.thirdAppAuthId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.thirdAppAuthId;
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
   * 到期时间.
   */
  public void setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
  }

  /**
   * 到期时间.
   */
  public LocalDateTime getExpireTime() {
    return this.expireTime;
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
   * 标识.
   */
  public void setThirdAppAuthId(Long thirdAppAuthId) {
    this.thirdAppAuthId = thirdAppAuthId;
  }

  /**
   * 标识.
   */
  public Long getThirdAppAuthId() {
    return this.thirdAppAuthId;
  }

  /**
   * 第三方应用id.
   */
  public void setThirdAppId(Long thirdAppId) {
    this.thirdAppId = thirdAppId;
  }

  /**
   * 第三方应用id.
   */
  public Long getThirdAppId() {
    return this.thirdAppId;
  }

  /**
   * 授权码.
   */
  public void setToken(String token) {
    this.token = token == null ? null : token.trim();
  }

  /**
   * 授权码.
   */
  public String getToken() {
    return this.token;
  }
}