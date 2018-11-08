package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.EnableDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 第三方应用.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ThirdApp extends EnableDO {
  /**
   * 第三方应用key.
   */
  private String appKey;
  /**
   * 第三方应用密钥.
   */
  private String appSecret;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 服务商.
   */
  private Boolean v;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 第三方应用id.
   */
  private Long thirdAppId;
  /**
   * 第三方应用名称.
   */
  private String thirdAppName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.thirdAppId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.thirdAppId;
  }


  /**
   * 第三方应用key.
   */
  public void setAppKey(String appKey) {
    this.appKey = appKey == null ? null : appKey.trim();
  }

  /**
   * 第三方应用key.
   */
  public String getAppKey() {
    return this.appKey;
  }

  /**
   * 第三方应用密钥.
   */
  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret == null ? null : appSecret.trim();
  }

  /**
   * 第三方应用密钥.
   */
  public String getAppSecret() {
    return this.appSecret;
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
   * 服务商.
   */
  public void setv(Boolean v) {
    this.v = v;
  }

  /**
   * 服务商.
   */
  public Boolean isv() {
    return this.v;
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
   * 第三方应用名称.
   */
  public void setThirdAppName(String thirdAppName) {
    this.thirdAppName = thirdAppName == null ? null : thirdAppName.trim();
  }

  /**
   * 第三方应用名称.
   */
  public String getThirdAppName() {
    return this.thirdAppName;
  }
}