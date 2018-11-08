package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 字段.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Field extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 字段列名.
   */
  private String fieldColumn;
  /**
   * 字段id.
   */
  private Long fieldId;
  /**
   * 字段名称.
   */
  private String fieldName;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;


  @Override
  public void setPrimaryKey(Long pk) {
    this.fieldId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.fieldId;
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
   * 字段列名.
   */
  public void setFieldColumn(String fieldColumn) {
    this.fieldColumn = fieldColumn == null ? null : fieldColumn.trim();
  }

  /**
   * 字段列名.
   */
  public String getFieldColumn() {
    return this.fieldColumn;
  }

  /**
   * 字段id.
   */
  public void setFieldId(Long fieldId) {
    this.fieldId = fieldId;
  }

  /**
   * 字段id.
   */
  public Long getFieldId() {
    return this.fieldId;
  }

  /**
   * 字段名称.
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName == null ? null : fieldName.trim();
  }

  /**
   * 字段名称.
   */
  public String getFieldName() {
    return this.fieldName;
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