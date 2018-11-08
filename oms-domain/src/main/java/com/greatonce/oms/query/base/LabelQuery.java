package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 标签.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class LabelQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 标签颜色.
   */
  private String labelColor;
  /**
   * 标签id.
   */
  private Long labelId;
  /**
   * 标签名称.
   */
  private String labelName;
  /**
   * 标签类型.
   */
  private Integer labelType;
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
   * 标签颜色.
   * @param labelColor 标签颜色
   */
  public void setLabelColor(String labelColor) {
    this.labelColor = labelColor == null ? null : labelColor.trim();
  }

  /**
   * 标签颜色.
   * @return 标签颜色
   */
  public String getLabelColor() {
      return this.labelColor;
  }

  /**
   * 标签id.
   * @param labelId 标签id
   */
  public void setLabelId(Long labelId) {
    this.labelId = labelId;
  }

  /**
   * 标签id.
   * @return 标签id
   */
  public Long getLabelId() {
      return this.labelId;
  }

  /**
   * 标签名称.
   * @param labelName 标签名称
   */
  public void setLabelName(String labelName) {
    this.labelName = labelName == null ? null : labelName.trim();
  }

  /**
   * 标签名称.
   * @return 标签名称
   */
  public String getLabelName() {
      return this.labelName;
  }

  /**
   * 标签类型.
   * @param labelType 标签类型
   */
  public void setLabelType(Integer labelType) {
    this.labelType = labelType;
  }

  /**
   * 标签类型.
   * @return 标签类型
   */
  public Integer getLabelType() {
      return this.labelType;
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