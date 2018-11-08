package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class DepartmentQuery extends Query {
  /**
   * 子节点数量.
   */
  private Integer childrenQuantity;
  /**
   * 链路id.
   */
  private String cid;
  /**
   * 公司id.
   */
  private Long companyId;
  /**
   * 公司名称.
   */
  private String companyName;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 部门编码.
   */
  private String departmentCode;
  /**
   * 部门id.
   */
  private Long departmentId;
  /**
   * 部门名称.
   */
  private String departmentName;
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
   * 公司id.
   * @param companyId 公司id
   */
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  /**
   * 公司id.
   * @return 公司id
   */
  public Long getCompanyId() {
      return this.companyId;
  }

  /**
   * 公司名称.
   * @param companyName 公司名称
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   * @return 公司名称
   */
  public String getCompanyName() {
      return this.companyName;
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
   * 部门编码.
   * @param departmentCode 部门编码
   */
  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode == null ? null : departmentCode.trim();
  }

  /**
   * 部门编码.
   * @return 部门编码
   */
  public String getDepartmentCode() {
      return this.departmentCode;
  }

  /**
   * 部门id.
   * @param departmentId 部门id
   */
  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  /**
   * 部门id.
   * @return 部门id
   */
  public Long getDepartmentId() {
      return this.departmentId;
  }

  /**
   * 部门名称.
   * @param departmentName 部门名称
   */
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName == null ? null : departmentName.trim();
  }

  /**
   * 部门名称.
   * @return 部门名称
   */
  public String getDepartmentName() {
      return this.departmentName;
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