package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
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
public class Label extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;


  @Override
  public void setPrimaryKey(Long pk) {
    this.labelId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.labelId;
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
   * 标签颜色.
   */
  public void setLabelColor(String labelColor) {
    this.labelColor = labelColor == null ? null : labelColor.trim();
  }

  /**
   * 标签颜色.
   */
  public String getLabelColor() {
    return this.labelColor;
  }

  /**
   * 标签id.
   */
  public void setLabelId(Long labelId) {
    this.labelId = labelId;
  }

  /**
   * 标签id.
   */
  public Long getLabelId() {
    return this.labelId;
  }

  /**
   * 标签名称.
   */
  public void setLabelName(String labelName) {
    this.labelName = labelName == null ? null : labelName.trim();
  }

  /**
   * 标签名称.
   */
  public String getLabelName() {
    return this.labelName;
  }

  /**
   * 标签类型.
   */
  public void setLabelType(Integer labelType) {
    this.labelType = labelType;
  }

  /**
   * 标签类型.
   */
  public Integer getLabelType() {
    return this.labelType;
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