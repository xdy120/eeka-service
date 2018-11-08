package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class WmsAppQuery extends Query {
  /**
   * 仓库应用码.
   */
  private String appKey;
  /**
   * 仓库应用密钥.
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
   * 客户标识.
   */
  private String customerId;
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
   * 客户标识.
   * @param customerId 客户标识
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId == null ? null : customerId.trim();
  }

  /**
   * 客户标识.
   * @return 客户标识
   */
  public String getCustomerId() {
      return this.customerId;
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

  /**
   * 标记.
   * @param tag 标记
   */
  public void setTag(String tag) {
    this.tag = tag == null ? null : tag.trim();
  }

  /**
   * 标记.
   * @return 标记
   */
  public String getTag() {
      return this.tag;
  }

  /**
   * 仓库应用id.
   * @param wmsAppId 仓库应用id
   */
  public void setWmsAppId(Long wmsAppId) {
    this.wmsAppId = wmsAppId;
  }

  /**
   * 仓库应用id.
   * @return 仓库应用id
   */
  public Long getWmsAppId() {
      return this.wmsAppId;
  }

  /**
   * 物流接口编码.
   * @param wmsAppName 物流接口编码
   */
  public void setWmsAppName(String wmsAppName) {
    this.wmsAppName = wmsAppName == null ? null : wmsAppName.trim();
  }

  /**
   * 物流接口编码.
   * @return 物流接口编码
   */
  public String getWmsAppName() {
      return this.wmsAppName;
  }

  /**
   * 物流类型.
   * @param wmsType 物流类型
   */
  public void setWmsType(WmsType wmsType) {
    this.wmsType = wmsType;
  }

  /**
   * 物流类型.
   * @return 物流类型
   */
  public WmsType getWmsType() {
      return this.wmsType;
  }
}