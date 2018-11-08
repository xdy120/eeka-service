package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.enums.MallType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商城应用.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class MallApp extends EnableDO {
  /**
   * 仓库应用码.
   */
  private String appKey;
  /**
   * 仓库应用密钥.
   */
  private String appSecret;
  /**
   * 回调地址.
   */
  private String callbackUrl;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 应用id.
   */
  private Long mallAppId;
  /**
   * 应用名称.
   */
  private String mallAppName;
  /**
   * 商城类型.
   */
  private MallType mallType;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 安全令牌.
   */
  private String safetyCertificate;
  /**
   * 服务地址.
   */
  private String serviceUrl;


  @Override
  public void setPrimaryKey(Long pk) {
    this.mallAppId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.mallAppId;
  }


  /**
   * 仓库应用码.
   */
  public void setAppKey(String appKey) {
    this.appKey = appKey == null ? null : appKey.trim();
  }

  /**
   * 仓库应用码.
   */
  public String getAppKey() {
    return this.appKey;
  }

  /**
   * 仓库应用密钥.
   */
  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret == null ? null : appSecret.trim();
  }

  /**
   * 仓库应用密钥.
   */
  public String getAppSecret() {
    return this.appSecret;
  }

  /**
   * 回调地址.
   */
  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
  }

  /**
   * 回调地址.
   */
  public String getCallbackUrl() {
    return this.callbackUrl;
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
   * 应用id.
   */
  public void setMallAppId(Long mallAppId) {
    this.mallAppId = mallAppId;
  }

  /**
   * 应用id.
   */
  public Long getMallAppId() {
    return this.mallAppId;
  }

  /**
   * 应用名称.
   */
  public void setMallAppName(String mallAppName) {
    this.mallAppName = mallAppName == null ? null : mallAppName.trim();
  }

  /**
   * 应用名称.
   */
  public String getMallAppName() {
    return this.mallAppName;
  }

  /**
   * 商城类型.
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   */
  public MallType getMallType() {
    return this.mallType;
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
   * 安全令牌.
   */
  public void setSafetyCertificate(String safetyCertificate) {
    this.safetyCertificate = safetyCertificate == null ? null : safetyCertificate.trim();
  }

  /**
   * 安全令牌.
   */
  public String getSafetyCertificate() {
    return this.safetyCertificate;
  }

  /**
   * 服务地址.
   */
  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl == null ? null : serviceUrl.trim();
  }

  /**
   * 服务地址.
   */
  public String getServiceUrl() {
    return this.serviceUrl;
  }
}