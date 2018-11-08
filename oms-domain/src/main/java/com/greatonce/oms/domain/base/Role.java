package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Role extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 角色id.
   */
  private Long roleId;
  /**
   * 角色名称.
   */
  private String roleName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.roleId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.roleId;
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
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
  }

  /**
   * 角色id.
   */
  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  /**
   * 角色id.
   */
  public Long getRoleId() {
    return this.roleId;
  }

  /**
   * 角色名称.
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName == null ? null : roleName.trim();
  }

  /**
   * 角色名称.
   */
  public String getRoleName() {
    return this.roleName;
  }
}