package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class StockUploadStrategyQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 自动上传.
   * @param autoUpload 自动上传
   */
  public void setAutoUpload(Boolean autoUpload) {
    this.autoUpload = autoUpload;
  }

  /**
   * 自动上传.
   * @return 自动上传
   */
  public Boolean isAutoUpload() {
      return this.autoUpload;
  }

  /**
   * 手工上传.
   * @param manualUpload 手工上传
   */
  public void setManualUpload(Boolean manualUpload) {
    this.manualUpload = manualUpload;
  }

  /**
   * 手工上传.
   * @return 手工上传
   */
  public Boolean isManualUpload() {
      return this.manualUpload;
  }

  /**
   * 商城仓库.
   * @param mallWarehouse 商城仓库
   */
  public void setMallWarehouse(String mallWarehouse) {
    this.mallWarehouse = mallWarehouse == null ? null : mallWarehouse.trim();
  }

  /**
   * 商城仓库.
   * @return 商城仓库
   */
  public String getMallWarehouse() {
      return this.mallWarehouse;
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
   * 策略设置.
   * @param settingJson 策略设置
   */
  public void setSettingJson(String settingJson) {
    this.settingJson = settingJson == null ? null : settingJson.trim();
  }

  /**
   * 策略设置.
   * @return 策略设置
   */
  public String getSettingJson() {
      return this.settingJson;
  }

  /**
   * 库存上传策略id.
   * @param stockUploadStrategyId 库存上传策略id
   */
  public void setStockUploadStrategyId(Long stockUploadStrategyId) {
    this.stockUploadStrategyId = stockUploadStrategyId;
  }

  /**
   * 库存上传策略id.
   * @return 库存上传策略id
   */
  public Long getStockUploadStrategyId() {
      return this.stockUploadStrategyId;
  }

  /**
   * 库存上传策略名称.
   * @param stockUploadStrategyName 库存上传策略名称
   */
  public void setStockUploadStrategyName(String stockUploadStrategyName) {
    this.stockUploadStrategyName = stockUploadStrategyName == null ? null : stockUploadStrategyName.trim();
  }

  /**
   * 库存上传策略名称.
   * @return 库存上传策略名称
   */
  public String getStockUploadStrategyName() {
      return this.stockUploadStrategyName;
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