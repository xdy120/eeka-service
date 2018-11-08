package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
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
public class Department extends BaseDO {
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
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 父id.
   */
  private Long parentId;

  /**
   * 用户列表.
   */
  private List<User> users;

  @Override
  public void setPrimaryKey(Long pk) {
    this.departmentId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.departmentId;
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
   * 公司id.
   */
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  /**
   * 公司id.
   */
  public Long getCompanyId() {
    return this.companyId;
  }

  /**
   * 公司名称.
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   */
  public String getCompanyName() {
    return this.companyName;
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
   * 部门编码.
   */
  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode == null ? null : departmentCode.trim();
  }

  /**
   * 部门编码.
   */
  public String getDepartmentCode() {
    return this.departmentCode;
  }

  /**
   * 部门id.
   */
  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  /**
   * 部门id.
   */
  public Long getDepartmentId() {
    return this.departmentId;
  }

  /**
   * 部门名称.
   */
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName == null ? null : departmentName.trim();
  }

  /**
   * 部门名称.
   */
  public String getDepartmentName() {
    return this.departmentName;
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

  /**
   * 用户列表.
   */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * 用户列表.
   */
  public List<User> getUsers() {
    return this.users;
  }
}