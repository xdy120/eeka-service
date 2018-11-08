package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Company extends BaseDO {
  /**
   * 公司编码.
   */
  private String companyCode;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 电话.
   */
  private String telephone;

  /**
   * 部门列表.
   */
  private List<Department> departments;

  @Override
  public void setPrimaryKey(Long pk) {
    this.companyId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.companyId;
  }


  /**
   * 公司编码.
   */
  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode == null ? null : companyCode.trim();
  }

  /**
   * 公司编码.
   */
  public String getCompanyCode() {
    return this.companyCode;
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
   * 部门列表.
   */
  public void setDepartments(List<Department> departments) {
    this.departments = departments;
  }

  /**
   * 部门列表.
   */
  public List<Department> getDepartments() {
    return this.departments;
  }
}