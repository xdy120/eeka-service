package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 快递策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ExpressStrategy extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 快递策略id.
   */
  private Long expressStrategyId;
  /**
   * 快递策略名称.
   */
  private String expressStrategyName;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 策略设置.
   */
  private String settingJson;

  /**
   * 配置规则.
   */
  private ExpressStrategyRule rule;

  @Override
  public void setPrimaryKey(Long pk) {
    this.expressStrategyId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.expressStrategyId;
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
   * 快递策略id.
   */
  public void setExpressStrategyId(Long expressStrategyId) {
    this.expressStrategyId = expressStrategyId;
  }

  /**
   * 快递策略id.
   */
  public Long getExpressStrategyId() {
    return this.expressStrategyId;
  }

  /**
   * 快递策略名称.
   */
  public void setExpressStrategyName(String expressStrategyName) {
    this.expressStrategyName = expressStrategyName == null ? null : expressStrategyName.trim();
  }

  /**
   * 快递策略名称.
   */
  public String getExpressStrategyName() {
    return this.expressStrategyName;
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
   * 配置规则.
   */
  public void setRule(ExpressStrategyRule rule) {
    this.rule = rule;
  }

  /**
   * 配置规则.
   */
  public ExpressStrategyRule getRule() {
    return this.rule;
  }
}