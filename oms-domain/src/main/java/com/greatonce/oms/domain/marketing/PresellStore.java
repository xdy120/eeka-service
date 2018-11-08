package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 预售店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PresellStore extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 预售计划id.
   */
  private Long presellId;
  /**
   * 预售店铺id.
   */
  private Long presellStoreId;
  /**
   * 上传比例.
   */
  private Integer rate;
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
    this.presellStoreId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.presellStoreId;
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
   * 预售计划id.
   */
  public void setPresellId(Long presellId) {
    this.presellId = presellId;
  }

  /**
   * 预售计划id.
   */
  public Long getPresellId() {
    return this.presellId;
  }

  /**
   * 预售店铺id.
   */
  public void setPresellStoreId(Long presellStoreId) {
    this.presellStoreId = presellStoreId;
  }

  /**
   * 预售店铺id.
   */
  public Long getPresellStoreId() {
    return this.presellStoreId;
  }

  /**
   * 上传比例.
   */
  public void setRate(Integer rate) {
    this.rate = rate;
  }

  /**
   * 上传比例.
   */
  public Integer getRate() {
    return this.rate;
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