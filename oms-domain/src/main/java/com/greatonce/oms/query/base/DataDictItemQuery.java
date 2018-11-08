package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据字典项.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class DataDictItemQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 字典id.
   */
  private Long dataDictId;
  /**
   * 字典项编码.
   */
  private String dataDictItemCode;
  /**
   * 字典项id.
   */
  private Long dataDictItemId;
  /**
   * 字典项名称.
   */
  private String dataDictItemName;
  /**
   * 是否系统.
   */
  private Boolean system;
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
   * 字典id.
   * @param dataDictId 字典id
   */
  public void setDataDictId(Long dataDictId) {
    this.dataDictId = dataDictId;
  }

  /**
   * 字典id.
   * @return 字典id
   */
  public Long getDataDictId() {
      return this.dataDictId;
  }

  /**
   * 字典项编码.
   * @param dataDictItemCode 字典项编码
   */
  public void setDataDictItemCode(String dataDictItemCode) {
    this.dataDictItemCode = dataDictItemCode == null ? null : dataDictItemCode.trim();
  }

  /**
   * 字典项编码.
   * @return 字典项编码
   */
  public String getDataDictItemCode() {
      return this.dataDictItemCode;
  }

  /**
   * 字典项id.
   * @param dataDictItemId 字典项id
   */
  public void setDataDictItemId(Long dataDictItemId) {
    this.dataDictItemId = dataDictItemId;
  }

  /**
   * 字典项id.
   * @return 字典项id
   */
  public Long getDataDictItemId() {
      return this.dataDictItemId;
  }

  /**
   * 字典项名称.
   * @param dataDictItemName 字典项名称
   */
  public void setDataDictItemName(String dataDictItemName) {
    this.dataDictItemName = dataDictItemName == null ? null : dataDictItemName.trim();
  }

  /**
   * 字典项名称.
   * @return 字典项名称
   */
  public String getDataDictItemName() {
      return this.dataDictItemName;
  }

  /**
   * 是否系统.
   * @param system 是否系统
   */
  public void setSystem(Boolean system) {
    this.system = system;
  }

  /**
   * 是否系统.
   * @return 是否系统
   */
  public Boolean isSystem() {
      return this.system;
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