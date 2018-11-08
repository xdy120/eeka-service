package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class GlobalSettingQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;


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
   * 全局设置id.
   * @param globalSettingId 全局设置id
   */
  public void setGlobalSettingId(Long globalSettingId) {
    this.globalSettingId = globalSettingId;
  }

  /**
   * 全局设置id.
   * @return 全局设置id
   */
  public Long getGlobalSettingId() {
      return this.globalSettingId;
  }

  /**
   * 全局配置内容.
   * @param globalSettingJson 全局配置内容
   */
  public void setGlobalSettingJson(String globalSettingJson) {
    this.globalSettingJson = globalSettingJson == null ? null : globalSettingJson.trim();
  }

  /**
   * 全局配置内容.
   * @return 全局配置内容
   */
  public String getGlobalSettingJson() {
      return this.globalSettingJson;
  }

  /**
   * 全局设置类型.
   * @param globalSettingType 全局设置类型
   */
  public void setGlobalSettingType(Integer globalSettingType) {
    this.globalSettingType = globalSettingType;
  }

  /**
   * 全局设置类型.
   * @return 全局设置类型
   */
  public Integer getGlobalSettingType() {
      return this.globalSettingType;
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
}