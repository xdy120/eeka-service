package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class DataDictQuery extends Query {
  /**
   * 子节点数量.
   */
  private Integer childrenQuantity;
  /**
   * 链路id.
   */
  private String cid;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 父id.
   */
  private Long parentId;


  /**
   * 子节点数量.
   * @param childrenQuantity 子节点数量
   */
  public void setChildrenQuantity(Integer childrenQuantity) {
    this.childrenQuantity = childrenQuantity;
  }

  /**
   * 子节点数量.
   * @return 子节点数量
   */
  public Integer getChildrenQuantity() {
      return this.childrenQuantity;
  }

  /**
   * 链路id.
   * @param cid 链路id
   */
  public void setCid(String cid) {
    this.cid = cid == null ? null : cid.trim();
  }

  /**
   * 链路id.
   * @return 链路id
   */
  public String getCid() {
      return this.cid;
  }

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
   * 字典名称.
   * @param dataDictName 字典名称
   */
  public void setDataDictName(String dataDictName) {
    this.dataDictName = dataDictName == null ? null : dataDictName.trim();
  }

  /**
   * 字典名称.
   * @return 字典名称
   */
  public String getDataDictName() {
      return this.dataDictName;
  }

  /**
   * 字典类型.
   * @param dataDictType 字典类型
   */
  public void setDataDictType(DataDictType dataDictType) {
    this.dataDictType = dataDictType;
  }

  /**
   * 字典类型.
   * @return 字典类型
   */
  public DataDictType getDataDictType() {
      return this.dataDictType;
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

  /**
   * 父id.
   * @param parentId 父id
   */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  /**
   * 父id.
   * @return 父id
   */
  public Long getParentId() {
      return this.parentId;
  }
}