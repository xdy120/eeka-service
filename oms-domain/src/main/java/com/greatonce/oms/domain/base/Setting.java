package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.SettingType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 基础配置.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Setting extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 设置id.
   */
  private Long settingId;
  /**
   * 配置内容.
   */
  private String settingJson;
  /**
   * 设置类型.
   */
  private SettingType settingType;


  @Override
  public void setPrimaryKey(Long pk) {
    this.settingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.settingId;
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
   * 设置id.
   */
  public void setSettingId(Long settingId) {
    this.settingId = settingId;
  }

  /**
   * 设置id.
   */
  public Long getSettingId() {
    return this.settingId;
  }

  /**
   * 配置内容.
   */
  public void setSettingJson(String settingJson) {
    this.settingJson = settingJson == null ? null : settingJson.trim();
  }

  /**
   * 配置内容.
   */
  public String getSettingJson() {
    return this.settingJson;
  }

  /**
   * 设置类型.
   */
  public void setSettingType(SettingType settingType) {
    this.settingType = settingType;
  }

  /**
   * 设置类型.
   */
  public SettingType getSettingType() {
    return this.settingType;
  }
}