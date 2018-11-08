package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色用户关系.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class RoleUser extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 关系id.
   */
  private Long relationId;
  /**
   * 角色id.
   */
  private Long roleId;
  /**
   * 用户id.
   */
  private Long userId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.relationId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.relationId;
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
   * 关系id.
   */
  public void setRelationId(Long relationId) {
    this.relationId = relationId;
  }

  /**
   * 关系id.
   */
  public Long getRelationId() {
    return this.relationId;
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
   * 用户id.
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * 用户id.
   */
  public Long getUserId() {
    return this.userId;
  }
}