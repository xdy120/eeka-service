package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存配货策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockDispatchStrategyQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 默认到付快递id.
   */
  private Long defaultCodExpressId;
  /**
   * 默认到付快递名称.
   */
  private String defaultCodExpressName;
  /**
   * 默认快递id.
   */
  private Long defaultExpressId;
  /**
   * 默认快递名称.
   */
  private String defaultExpressName;
  /**
   * 是否启用.
   */
  private Boolean enable;
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
   * 配货策略id.
   */
  private Long stockDispatchStrategyId;
  /**
   * 配货策略名称.
   */
  private String stockDispatchStrategyName;


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
   * 默认到付快递id.
   * @param defaultCodExpressId 默认到付快递id
   */
  public void setDefaultCodExpressId(Long defaultCodExpressId) {
    this.defaultCodExpressId = defaultCodExpressId;
  }

  /**
   * 默认到付快递id.
   * @return 默认到付快递id
   */
  public Long getDefaultCodExpressId() {
      return this.defaultCodExpressId;
  }

  /**
   * 默认到付快递名称.
   * @param defaultCodExpressName 默认到付快递名称
   */
  public void setDefaultCodExpressName(String defaultCodExpressName) {
    this.defaultCodExpressName = defaultCodExpressName == null ? null : defaultCodExpressName.trim();
  }

  /**
   * 默认到付快递名称.
   * @return 默认到付快递名称
   */
  public String getDefaultCodExpressName() {
      return this.defaultCodExpressName;
  }

  /**
   * 默认快递id.
   * @param defaultExpressId 默认快递id
   */
  public void setDefaultExpressId(Long defaultExpressId) {
    this.defaultExpressId = defaultExpressId;
  }

  /**
   * 默认快递id.
   * @return 默认快递id
   */
  public Long getDefaultExpressId() {
      return this.defaultExpressId;
  }

  /**
   * 默认快递名称.
   * @param defaultExpressName 默认快递名称
   */
  public void setDefaultExpressName(String defaultExpressName) {
    this.defaultExpressName = defaultExpressName == null ? null : defaultExpressName.trim();
  }

  /**
   * 默认快递名称.
   * @return 默认快递名称
   */
  public String getDefaultExpressName() {
      return this.defaultExpressName;
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
   * 配货策略id.
   * @param stockDispatchStrategyId 配货策略id
   */
  public void setStockDispatchStrategyId(Long stockDispatchStrategyId) {
    this.stockDispatchStrategyId = stockDispatchStrategyId;
  }

  /**
   * 配货策略id.
   * @return 配货策略id
   */
  public Long getStockDispatchStrategyId() {
      return this.stockDispatchStrategyId;
  }

  /**
   * 配货策略名称.
   * @param stockDispatchStrategyName 配货策略名称
   */
  public void setStockDispatchStrategyName(String stockDispatchStrategyName) {
    this.stockDispatchStrategyName = stockDispatchStrategyName == null ? null : stockDispatchStrategyName.trim();
  }

  /**
   * 配货策略名称.
   * @return 配货策略名称
   */
  public String getStockDispatchStrategyName() {
      return this.stockDispatchStrategyName;
  }
}