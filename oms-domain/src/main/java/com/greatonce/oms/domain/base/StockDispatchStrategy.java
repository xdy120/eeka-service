package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
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
public class StockDispatchStrategy extends EnableDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 配货配置规则.
   */
  private StockDispatchRule rule;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockDispatchStrategyId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockDispatchStrategyId;
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
   * 默认到付快递id.
   */
  public void setDefaultCodExpressId(Long defaultCodExpressId) {
    this.defaultCodExpressId = defaultCodExpressId;
  }

  /**
   * 默认到付快递id.
   */
  public Long getDefaultCodExpressId() {
    return this.defaultCodExpressId;
  }

  /**
   * 默认到付快递名称.
   */
  public void setDefaultCodExpressName(String defaultCodExpressName) {
    this.defaultCodExpressName = defaultCodExpressName == null ? null : defaultCodExpressName.trim();
  }

  /**
   * 默认到付快递名称.
   */
  public String getDefaultCodExpressName() {
    return this.defaultCodExpressName;
  }

  /**
   * 默认快递id.
   */
  public void setDefaultExpressId(Long defaultExpressId) {
    this.defaultExpressId = defaultExpressId;
  }

  /**
   * 默认快递id.
   */
  public Long getDefaultExpressId() {
    return this.defaultExpressId;
  }

  /**
   * 默认快递名称.
   */
  public void setDefaultExpressName(String defaultExpressName) {
    this.defaultExpressName = defaultExpressName == null ? null : defaultExpressName.trim();
  }

  /**
   * 默认快递名称.
   */
  public String getDefaultExpressName() {
    return this.defaultExpressName;
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
   * 配货策略id.
   */
  public void setStockDispatchStrategyId(Long stockDispatchStrategyId) {
    this.stockDispatchStrategyId = stockDispatchStrategyId;
  }

  /**
   * 配货策略id.
   */
  public Long getStockDispatchStrategyId() {
    return this.stockDispatchStrategyId;
  }

  /**
   * 配货策略名称.
   */
  public void setStockDispatchStrategyName(String stockDispatchStrategyName) {
    this.stockDispatchStrategyName = stockDispatchStrategyName == null ? null : stockDispatchStrategyName.trim();
  }

  /**
   * 配货策略名称.
   */
  public String getStockDispatchStrategyName() {
    return this.stockDispatchStrategyName;
  }

  /**
   * 配货配置规则.
   */
  public void setRule(StockDispatchRule rule) {
    this.rule = rule;
  }

  /**
   * 配货配置规则.
   */
  public StockDispatchRule getRule() {
    return this.rule;
  }
}