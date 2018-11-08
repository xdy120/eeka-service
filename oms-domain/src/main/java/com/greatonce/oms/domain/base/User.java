package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class User extends EnableDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 部门id.
   */
  private Long departmentId;
  /**
   * 部门名称.
   */
  private String departmentName;
  /**
   * 邮箱.
   */
  private String email;
  /**
   * 头像链接.
   */
  private String headUrl;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 登录名.
   */
  private String loginName;
  /**
   * 登录密码.
   */
  private String loginPassword;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 昵称.
   */
  private String nickname;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 用户id.
   */
  private Long userId;
  /**
   * 用户姓名.
   */
  private String userName;

  /**
   * 角色.
   */
  private List<Role> roles;

  @Override
  public void setPrimaryKey(Long pk) {
    this.userId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.userId;
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
   * 邮箱.
   */
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  /**
   * 邮箱.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * 头像链接.
   */
  public void setHeadUrl(String headUrl) {
    this.headUrl = headUrl == null ? null : headUrl.trim();
  }

  /**
   * 头像链接.
   */
  public String getHeadUrl() {
    return this.headUrl;
  }

  /**
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
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
   * 登录名.
   */
  public void setLoginName(String loginName) {
    this.loginName = loginName == null ? null : loginName.trim();
  }

  /**
   * 登录名.
   */
  public String getLoginName() {
    return this.loginName;
  }

  /**
   * 登录密码.
   */
  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword == null ? null : loginPassword.trim();
  }

  /**
   * 登录密码.
   */
  public String getLoginPassword() {
    return this.loginPassword;
  }

  /**
   * 手机.
   */
  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  /**
   * 手机.
   */
  public String getMobile() {
    return this.mobile;
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
   * 昵称.
   */
  public void setNickname(String nickname) {
    this.nickname = nickname == null ? null : nickname.trim();
  }

  /**
   * 昵称.
   */
  public String getNickname() {
    return this.nickname;
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
   * 电话.
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   */
  public String getTelephone() {
    return this.telephone;
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

  /**
   * 用户姓名.
   */
  public void setUserName(String userName) {
    this.userName = userName == null ? null : userName.trim();
  }

  /**
   * 用户姓名.
   */
  public String getUserName() {
    return this.userName;
  }

  /**
   * 角色.
   */
  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  /**
   * 角色.
   */
  public List<Role> getRoles() {
    return this.roles;
  }
}