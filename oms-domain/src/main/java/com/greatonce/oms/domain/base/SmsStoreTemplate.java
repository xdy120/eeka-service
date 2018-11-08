package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 短信店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SmsStoreTemplate extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 店铺短信模版id.
   */
  private Long smsStoreTemplateId;
  /**
   * 短信模版id.
   */
  private Long smsTemplateId;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.smsStoreTemplateId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.smsStoreTemplateId;
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
   * 店铺短信模版id.
   */
  public void setSmsStoreTemplateId(Long smsStoreTemplateId) {
    this.smsStoreTemplateId = smsStoreTemplateId;
  }

  /**
   * 店铺短信模版id.
   */
  public Long getSmsStoreTemplateId() {
    return this.smsStoreTemplateId;
  }

  /**
   * 短信模版id.
   */
  public void setSmsTemplateId(Long smsTemplateId) {
    this.smsTemplateId = smsTemplateId;
  }

  /**
   * 短信模版id.
   */
  public Long getSmsTemplateId() {
    return this.smsTemplateId;
  }

  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }

  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }
}