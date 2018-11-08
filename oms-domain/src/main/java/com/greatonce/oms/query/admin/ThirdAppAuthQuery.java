package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class ThirdAppAuthQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 到期时间开始.
   */
  private LocalDateTime expireTimeBegin;
  /**
   * 到期时间结束.
   */
  private LocalDateTime expireTimeEnd;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 到期时间开始.
   * @param expireTimeBegin 开始.
   */
  public void setExpireTimeBegin(LocalDateTime expireTimeBegin) {
    this.expireTimeBegin = expireTimeBegin;
  }

  /**
   * 到期时间开始.
   * @return 到期时间开始
   */
  public LocalDateTime getExpireTimeBegin() {
    return this.expireTimeBegin;
  }

  /**
   * 到期时间结束.
   * @param expireTimeEnd 结束
   */
  public void setExpireTimeEnd(LocalDateTime expireTimeEnd) {
    this.expireTimeEnd = expireTimeEnd;
  }

  /**
   * 到期时间结束.
   * @return 到期时间结束
   */
  public LocalDateTime getExpireTimeEnd() {
    return this.expireTimeEnd;
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
   * 标识.
   * @param thirdAppAuthId 标识
   */
  public void setThirdAppAuthId(Long thirdAppAuthId) {
    this.thirdAppAuthId = thirdAppAuthId;
  }

  /**
   * 标识.
   * @return 标识
   */
  public Long getThirdAppAuthId() {
      return this.thirdAppAuthId;
  }

  /**
   * 第三方应用id.
   * @param thirdAppId 第三方应用id
   */
  public void setThirdAppId(Long thirdAppId) {
    this.thirdAppId = thirdAppId;
  }

  /**
   * 第三方应用id.
   * @return 第三方应用id
   */
  public Long getThirdAppId() {
      return this.thirdAppId;
  }

  /**
   * 授权码.
   * @param token 授权码
   */
  public void setToken(String token) {
    this.token = token == null ? null : token.trim();
  }

  /**
   * 授权码.
   * @return 授权码
   */
  public String getToken() {
      return this.token;
  }
}