package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
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
public class DataDictItem extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;


  @Override
  public void setPrimaryKey(Long pk) {
    this.dataDictItemId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.dataDictItemId;
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
   * 字典id.
   */
  public void setDataDictId(Long dataDictId) {
    this.dataDictId = dataDictId;
  }

  /**
   * 字典id.
   */
  public Long getDataDictId() {
    return this.dataDictId;
  }

  /**
   * 字典项编码.
   */
  public void setDataDictItemCode(String dataDictItemCode) {
    this.dataDictItemCode = dataDictItemCode == null ? null : dataDictItemCode.trim();
  }

  /**
   * 字典项编码.
   */
  public String getDataDictItemCode() {
    return this.dataDictItemCode;
  }

  /**
   * 字典项id.
   */
  public void setDataDictItemId(Long dataDictItemId) {
    this.dataDictItemId = dataDictItemId;
  }

  /**
   * 字典项id.
   */
  public Long getDataDictItemId() {
    return this.dataDictItemId;
  }

  /**
   * 字典项名称.
   */
  public void setDataDictItemName(String dataDictItemName) {
    this.dataDictItemName = dataDictItemName == null ? null : dataDictItemName.trim();
  }

  /**
   * 字典项名称.
   */
  public String getDataDictItemName() {
    return this.dataDictItemName;
  }

  /**
   * 是否系统.
   */
  public void setSystem(Boolean system) {
    this.system = system;
  }

  /**
   * 是否系统.
   */
  public Boolean isSystem() {
    return this.system;
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