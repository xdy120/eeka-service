package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class MallAppQuery extends Query {
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 安全令牌.
   */
  private String safetyCertificate;
  /**
   * 服务地址.
   */
  private String serviceUrl;


  /**
   * 仓库应用码.
   * @param appKey 仓库应用码
   */
  public void setAppKey(String appKey) {
    this.appKey = appKey == null ? null : appKey.trim();
  }

  /**
   * 仓库应用码.
   * @return 仓库应用码
   */
  public String getAppKey() {
      return this.appKey;
  }

  /**
   * 仓库应用密钥.
   * @param appSecret 仓库应用密钥
   */
  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret == null ? null : appSecret.trim();
  }

  /**
   * 仓库应用密钥.
   * @return 仓库应用密钥
   */
  public String getAppSecret() {
      return this.appSecret;
  }

  /**
   * 回调地址.
   * @param callbackUrl 回调地址
   */
  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
  }

  /**
   * 回调地址.
   * @return 回调地址
   */
  public String getCallbackUrl() {
      return this.callbackUrl;
  }

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
   * 应用id.
   * @param mallAppId 应用id
   */
  public void setMallAppId(Long mallAppId) {
    this.mallAppId = mallAppId;
  }

  /**
   * 应用id.
   * @return 应用id
   */
  public Long getMallAppId() {
      return this.mallAppId;
  }

  /**
   * 应用名称.
   * @param mallAppName 应用名称
   */
  public void setMallAppName(String mallAppName) {
    this.mallAppName = mallAppName == null ? null : mallAppName.trim();
  }

  /**
   * 应用名称.
   * @return 应用名称
   */
  public String getMallAppName() {
      return this.mallAppName;
  }

  /**
   * 商城类型.
   * @param mallType 商城类型
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   * @return 商城类型
   */
  public MallType getMallType() {
      return this.mallType;
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
   * 安全令牌.
   * @param safetyCertificate 安全令牌
   */
  public void setSafetyCertificate(String safetyCertificate) {
    this.safetyCertificate = safetyCertificate == null ? null : safetyCertificate.trim();
  }

  /**
   * 安全令牌.
   * @return 安全令牌
   */
  public String getSafetyCertificate() {
      return this.safetyCertificate;
  }

  /**
   * 服务地址.
   * @param serviceUrl 服务地址
   */
  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl == null ? null : serviceUrl.trim();
  }

  /**
   * 服务地址.
   * @return 服务地址
   */
  public String getServiceUrl() {
      return this.serviceUrl;
  }
}