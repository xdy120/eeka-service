package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class PrivilegeQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 菜单项id.
   */
  private Long itemId;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 权限id.
   */
  private Long privilegeId;
  /**
   * 权限类型.
   */
  private PrivilegeType privilegeType;
  /**
   * .
   */
  private List<PrivilegeType> privilegeTypes;
  /**
   * 角色id.
   */
  private Long roleId;


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
   * 菜单项id.
   * @param itemId 菜单项id
   */
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  /**
   * 菜单项id.
   * @return 菜单项id
   */
  public Long getItemId() {
      return this.itemId;
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
   * 权限id.
   * @param privilegeId 权限id
   */
  public void setPrivilegeId(Long privilegeId) {
    this.privilegeId = privilegeId;
  }

  /**
   * 权限id.
   * @return 权限id
   */
  public Long getPrivilegeId() {
      return this.privilegeId;
  }

  /**
   * 权限类型.
   * @param privilegeType 权限类型
   */
  public void setPrivilegeType(PrivilegeType privilegeType) {
    this.privilegeType = privilegeType;
  }

  /**
   * 权限类型.
   * @return 权限类型
   */
  public PrivilegeType getPrivilegeType() {
      return this.privilegeType;
  }

  /**
   * .
   * @param privilegeTypes 
   */
  public void setPrivilegeTypes(List<PrivilegeType> privilegeTypes) {
    this.privilegeTypes = privilegeTypes;
  }

  /**
   * .
   * @return 
   */
  public List<PrivilegeType> getPrivilegeTypes() {
      return this.privilegeTypes;
  }

  /**
   * 角色id.
   * @param roleId 角色id
   */
  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  /**
   * 角色id.
   * @return 角色id
   */
  public Long getRoleId() {
      return this.roleId;
  }
}