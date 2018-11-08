package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class SettingQuery extends Query {
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
   * 设置id.
   * @param settingId 设置id
   */
  public void setSettingId(Long settingId) {
    this.settingId = settingId;
  }

  /**
   * 设置id.
   * @return 设置id
   */
  public Long getSettingId() {
      return this.settingId;
  }

  /**
   * 配置内容.
   * @param settingJson 配置内容
   */
  public void setSettingJson(String settingJson) {
    this.settingJson = settingJson == null ? null : settingJson.trim();
  }

  /**
   * 配置内容.
   * @return 配置内容
   */
  public String getSettingJson() {
      return this.settingJson;
  }

  /**
   * 设置类型.
   * @param settingType 设置类型
   */
  public void setSettingType(SettingType settingType) {
    this.settingType = settingType;
  }

  /**
   * 设置类型.
   * @return 设置类型
   */
  public SettingType getSettingType() {
      return this.settingType;
  }
}