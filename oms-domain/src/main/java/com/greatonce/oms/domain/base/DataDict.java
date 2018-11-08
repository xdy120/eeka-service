package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.DataDictType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据字典.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class DataDict extends BaseDO {
  /**
   * 子节点数量.
   */
  private Integer childrenQuantity;
  /**
   * 链路id.
   */
  private String cid;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 字典id.
   */
  private Long dataDictId;
  /**
   * 字典名称.
   */
  private String dataDictName;
  /**
   * 字典类型.
   */
  private DataDictType dataDictType;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 父id.
   */
  private Long parentId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.dataDictId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.dataDictId;
  }


  /**
   * 子节点数量.
   */
  public void setChildrenQuantity(Integer childrenQuantity) {
    this.childrenQuantity = childrenQuantity;
  }

  /**
   * 子节点数量.
   */
  public Integer getChildrenQuantity() {
    return this.childrenQuantity;
  }

  /**
   * 链路id.
   */
  public void setCid(String cid) {
    this.cid = cid == null ? null : cid.trim();
  }

  /**
   * 链路id.
   */
  public String getCid() {
    return this.cid;
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
   * 字典名称.
   */
  public void setDataDictName(String dataDictName) {
    this.dataDictName = dataDictName == null ? null : dataDictName.trim();
  }

  /**
   * 字典名称.
   */
  public String getDataDictName() {
    return this.dataDictName;
  }

  /**
   * 字典类型.
   */
  public void setDataDictType(DataDictType dataDictType) {
    this.dataDictType = dataDictType;
  }

  /**
   * 字典类型.
   */
  public DataDictType getDataDictType() {
    return this.dataDictType;
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

  /**
   * 父id.
   */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  /**
   * 父id.
   */
  public Long getParentId() {
    return this.parentId;
  }
}