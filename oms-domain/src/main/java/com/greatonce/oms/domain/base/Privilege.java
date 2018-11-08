package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.PrivilegeType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Privilege extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 菜单项id.
   */
  private Long itemId;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 权限id.
   */
  private Long privilegeId;
  /**
   * 权限类型.
   */
  private PrivilegeType privilegeType;
  /**
   * 角色id.
   */
  private Long roleId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.privilegeId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.privilegeId;
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
   * 菜单项id.
   */
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  /**
   * 菜单项id.
   */
  public Long getItemId() {
    return this.itemId;
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
   * 权限id.
   */
  public void setPrivilegeId(Long privilegeId) {
    this.privilegeId = privilegeId;
  }

  /**
   * 权限id.
   */
  public Long getPrivilegeId() {
    return this.privilegeId;
  }

  /**
   * 权限类型.
   */
  public void setPrivilegeType(PrivilegeType privilegeType) {
    this.privilegeType = privilegeType;
  }

  /**
   * 权限类型.
   */
  public PrivilegeType getPrivilegeType() {
    return this.privilegeType;
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
}