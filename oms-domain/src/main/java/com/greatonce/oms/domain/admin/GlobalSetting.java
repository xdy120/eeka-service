package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 全局配置.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GlobalSetting extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 全局设置id.
   */
  private Long globalSettingId;
  /**
   * 全局配置内容.
   */
  private String globalSettingJson;
  /**
   * 全局设置类型.
   */
  private Integer globalSettingType;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;


  @Override
  public void setPrimaryKey(Long pk) {
    this.globalSettingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.globalSettingId;
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
   * 全局设置id.
   */
  public void setGlobalSettingId(Long globalSettingId) {
    this.globalSettingId = globalSettingId;
  }

  /**
   * 全局设置id.
   */
  public Long getGlobalSettingId() {
    return this.globalSettingId;
  }

  /**
   * 全局配置内容.
   */
  public void setGlobalSettingJson(String globalSettingJson) {
    this.globalSettingJson = globalSettingJson == null ? null : globalSettingJson.trim();
  }

  /**
   * 全局配置内容.
   */
  public String getGlobalSettingJson() {
    return this.globalSettingJson;
  }

  /**
   * 全局设置类型.
   */
  public void setGlobalSettingType(Integer globalSettingType) {
    this.globalSettingType = globalSettingType;
  }

  /**
   * 全局设置类型.
   */
  public Integer getGlobalSettingType() {
    return this.globalSettingType;
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
}