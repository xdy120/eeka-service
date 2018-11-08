package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 赠品策略店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GiftStrategyStore extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 赠品策略id.
   */
  private Long giftStrategyId;
  /**
   * 赠品策略店铺关联id.
   */
  private Long giftStrategyStoreId;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
    this.giftStrategyStoreId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.giftStrategyStoreId;
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
   * 赠品策略id.
   */
  public void setGiftStrategyId(Long giftStrategyId) {
    this.giftStrategyId = giftStrategyId;
  }

  /**
   * 赠品策略id.
   */
  public Long getGiftStrategyId() {
    return this.giftStrategyId;
  }

  /**
   * 赠品策略店铺关联id.
   */
  public void setGiftStrategyStoreId(Long giftStrategyStoreId) {
    this.giftStrategyStoreId = giftStrategyStoreId;
  }

  /**
   * 赠品策略店铺关联id.
   */
  public Long getGiftStrategyStoreId() {
    return this.giftStrategyStoreId;
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