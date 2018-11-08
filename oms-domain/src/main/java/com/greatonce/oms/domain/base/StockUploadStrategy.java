package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存上传策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockUploadStrategy extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 自动上传.
   */
  private Boolean autoUpload;
  /**
   * 手工上传.
   */
  private Boolean manualUpload;
  /**
   * 商城仓库.
   */
  private String mallWarehouse;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 策略设置.
   */
  private String settingJson;
  /**
   * 库存上传策略id.
   */
  private Long stockUploadStrategyId;
  /**
   * 库存上传策略名称.
   */
  private String stockUploadStrategyName;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;

  /**
   * 上传配置规则.
   */
  private StockUploadRule rule;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockUploadStrategyId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockUploadStrategyId;
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
   * 自动上传.
   */
  public void setAutoUpload(Boolean autoUpload) {
    this.autoUpload = autoUpload;
  }

  /**
   * 自动上传.
   */
  public Boolean isAutoUpload() {
    return this.autoUpload;
  }

  /**
   * 手工上传.
   */
  public void setManualUpload(Boolean manualUpload) {
    this.manualUpload = manualUpload;
  }

  /**
   * 手工上传.
   */
  public Boolean isManualUpload() {
    return this.manualUpload;
  }

  /**
   * 商城仓库.
   */
  public void setMallWarehouse(String mallWarehouse) {
    this.mallWarehouse = mallWarehouse == null ? null : mallWarehouse.trim();
  }

  /**
   * 商城仓库.
   */
  public String getMallWarehouse() {
    return this.mallWarehouse;
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
   * 策略设置.
   */
  public void setSettingJson(String settingJson) {
    this.settingJson = settingJson == null ? null : settingJson.trim();
  }

  /**
   * 策略设置.
   */
  public String getSettingJson() {
    return this.settingJson;
  }

  /**
   * 库存上传策略id.
   */
  public void setStockUploadStrategyId(Long stockUploadStrategyId) {
    this.stockUploadStrategyId = stockUploadStrategyId;
  }

  /**
   * 库存上传策略id.
   */
  public Long getStockUploadStrategyId() {
    return this.stockUploadStrategyId;
  }

  /**
   * 库存上传策略名称.
   */
  public void setStockUploadStrategyName(String stockUploadStrategyName) {
    this.stockUploadStrategyName = stockUploadStrategyName == null ? null : stockUploadStrategyName.trim();
  }

  /**
   * 库存上传策略名称.
   */
  public String getStockUploadStrategyName() {
    return this.stockUploadStrategyName;
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

  /**
   * 上传配置规则.
   */
  public void setRule(StockUploadRule rule) {
    this.rule = rule;
  }

  /**
   * 上传配置规则.
   */
  public StockUploadRule getRule() {
    return this.rule;
  }
}