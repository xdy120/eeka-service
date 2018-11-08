package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class FieldQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 字段列名.
   * @param fieldColumn 字段列名
   */
  public void setFieldColumn(String fieldColumn) {
    this.fieldColumn = fieldColumn == null ? null : fieldColumn.trim();
  }

  /**
   * 字段列名.
   * @return 字段列名
   */
  public String getFieldColumn() {
      return this.fieldColumn;
  }

  /**
   * 字段id.
   * @param fieldId 字段id
   */
  public void setFieldId(Long fieldId) {
    this.fieldId = fieldId;
  }

  /**
   * 字段id.
   * @return 字段id
   */
  public Long getFieldId() {
      return this.fieldId;
  }

  /**
   * 字段名称.
   * @param fieldName 字段名称
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName == null ? null : fieldName.trim();
  }

  /**
   * 字段名称.
   * @return 字段名称
   */
  public String getFieldName() {
      return this.fieldName;
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