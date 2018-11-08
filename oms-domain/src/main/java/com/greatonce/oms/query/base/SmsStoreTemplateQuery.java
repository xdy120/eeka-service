package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class SmsStoreTemplateQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 店铺短信模版id.
   * @param smsStoreTemplateId 店铺短信模版id
   */
  public void setSmsStoreTemplateId(Long smsStoreTemplateId) {
    this.smsStoreTemplateId = smsStoreTemplateId;
  }

  /**
   * 店铺短信模版id.
   * @return 店铺短信模版id
   */
  public Long getSmsStoreTemplateId() {
      return this.smsStoreTemplateId;
  }

  /**
   * 短信模版id.
   * @param smsTemplateId 短信模版id
   */
  public void setSmsTemplateId(Long smsTemplateId) {
    this.smsTemplateId = smsTemplateId;
  }

  /**
   * 短信模版id.
   * @return 短信模版id
   */
  public Long getSmsTemplateId() {
      return this.smsTemplateId;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }
}