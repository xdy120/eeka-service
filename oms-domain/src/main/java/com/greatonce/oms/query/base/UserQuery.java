package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class UserQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 邮箱.
   * @param email 邮箱
   */
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  /**
   * 邮箱.
   * @return 邮箱
   */
  public String getEmail() {
      return this.email;
  }

  /**
   * 头像链接.
   * @param headUrl 头像链接
   */
  public void setHeadUrl(String headUrl) {
    this.headUrl = headUrl == null ? null : headUrl.trim();
  }

  /**
   * 头像链接.
   * @return 头像链接
   */
  public String getHeadUrl() {
      return this.headUrl;
  }

  /**
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
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
   * 登录名.
   * @param loginName 登录名
   */
  public void setLoginName(String loginName) {
    this.loginName = loginName == null ? null : loginName.trim();
  }

  /**
   * 登录名.
   * @return 登录名
   */
  public String getLoginName() {
      return this.loginName;
  }

  /**
   * 登录密码.
   * @param loginPassword 登录密码
   */
  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword == null ? null : loginPassword.trim();
  }

  /**
   * 登录密码.
   * @return 登录密码
   */
  public String getLoginPassword() {
      return this.loginPassword;
  }

  /**
   * 手机.
   * @param mobile 手机
   */
  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  /**
   * 手机.
   * @return 手机
   */
  public String getMobile() {
      return this.mobile;
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
   * 昵称.
   * @param nickname 昵称
   */
  public void setNickname(String nickname) {
    this.nickname = nickname == null ? null : nickname.trim();
  }

  /**
   * 昵称.
   * @return 昵称
   */
  public String getNickname() {
      return this.nickname;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * 电话.
   * @param telephone 电话
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   * @return 电话
   */
  public String getTelephone() {
      return this.telephone;
  }

  /**
   * 用户id.
   * @param userId 用户id
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * 用户id.
   * @return 用户id
   */
  public Long getUserId() {
      return this.userId;
  }

  /**
   * 用户姓名.
   * @param userName 用户姓名
   */
  public void setUserName(String userName) {
    this.userName = userName == null ? null : userName.trim();
  }

  /**
   * 用户姓名.
   * @return 用户姓名
   */
  public String getUserName() {
      return this.userName;
  }
}