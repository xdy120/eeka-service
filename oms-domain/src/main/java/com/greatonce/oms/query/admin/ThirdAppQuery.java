package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class ThirdAppQuery extends Query {
  /**
   * 第三方应用key.
   */
  private String appKey;
  /**
   * 第三方应用密钥.
   */
  private String appSecret;
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
   * 服务商.
   */
  private Boolean v;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 第三方应用id.
   */
  private Long thirdAppId;
  /**
   * 第三方应用名称.
   */
  private String thirdAppName;


  /**
   * 第三方应用key.
   * @param appKey 第三方应用key
   */
  public void setAppKey(String appKey) {
    this.appKey = appKey == null ? null : appKey.trim();
  }

  /**
   * 第三方应用key.
   * @return 第三方应用key
   */
  public String getAppKey() {
      return this.appKey;
  }

  /**
   * 第三方应用密钥.
   * @param appSecret 第三方应用密钥
   */
  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret == null ? null : appSecret.trim();
  }

  /**
   * 第三方应用密钥.
   * @return 第三方应用密钥
   */
  public String getAppSecret() {
      return this.appSecret;
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
   * 服务商.
   * @param v 服务商
   */
  public void setv(Boolean v) {
    this.v = v;
  }

  /**
   * 服务商.
   * @return 服务商
   */
  public Boolean isv() {
      return this.v;
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
   * 第三方应用名称.
   * @param thirdAppName 第三方应用名称
   */
  public void setThirdAppName(String thirdAppName) {
    this.thirdAppName = thirdAppName == null ? null : thirdAppName.trim();
  }

  /**
   * 第三方应用名称.
   * @return 第三方应用名称
   */
  public String getThirdAppName() {
      return this.thirdAppName;
  }
}