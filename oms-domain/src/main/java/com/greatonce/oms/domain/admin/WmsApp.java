package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.enums.WmsType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库应用.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class WmsApp extends EnableDO {
  /**
   * 仓库应用码.
   */
  private String appKey;
  /**
   * 仓库应用密钥.
   */
  private String appSecret;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 客户标识.
   */
  private String customerId;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 服务地址.
   */
  private String serviceUrl;
  /**
   * 标记.
   */
  private String tag;
  /**
   * 仓库应用id.
   */
  private Long wmsAppId;
  /**
   * 物流接口编码.
   */
  private String wmsAppName;
  /**
   * 物流类型.
   */
  private WmsType wmsType;


  @Override
  public void setPrimaryKey(Long pk) {
    this.wmsAppId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.wmsAppId;
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
   * 客户标识.
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId == null ? null : customerId.trim();
  }

  /**
   * 客户标识.
   */
  public String getCustomerId() {
    return this.customerId;
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

  /**
   * 标记.
   */
  public void setTag(String tag) {
    this.tag = tag == null ? null : tag.trim();
  }

  /**
   * 标记.
   */
  public String getTag() {
    return this.tag;
  }

  /**
   * 仓库应用id.
   */
  public void setWmsAppId(Long wmsAppId) {
    this.wmsAppId = wmsAppId;
  }

  /**
   * 仓库应用id.
   */
  public Long getWmsAppId() {
    return this.wmsAppId;
  }

  /**
   * 物流接口编码.
   */
  public void setWmsAppName(String wmsAppName) {
    this.wmsAppName = wmsAppName == null ? null : wmsAppName.trim();
  }

  /**
   * 物流接口编码.
   */
  public String getWmsAppName() {
    return this.wmsAppName;
  }

  /**
   * 物流类型.
   */
  public void setWmsType(WmsType wmsType) {
    this.wmsType = wmsType;
  }

  /**
   * 物流类型.
   */
  public WmsType getWmsType() {
    return this.wmsType;
  }
}