package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.SexType;
import com.greatonce.oms.domain.enums.marketing.MemberStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 会员.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Member extends BaseDO {
  /**
   * 地址.
   */
  private String address;
  /**
   * 市id.
   */
  private Long cityId;
  /**
   * 市.
   */
  private String cityName;
  /**
   * 联系人.
   */
  private String contact;
  /**
   * 国家id.
   */
  private Long countryId;
  /**
   * 国家.
   */
  private String countryName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 邮箱.
   */
  private String email;
  /**
   * 是否默认加急.
   */
  private Boolean urgent;
  /**
   * 会员id.
   */
  private Long memberId;
  /**
   * 会员名称.
   */
  private String memberName;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 支付帐号.
   */
  private String paymentAccount;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * 性别.
   */
  private SexType sex;
  /**
   * 状态.
   */
  private MemberStatus status;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 邮编.
   */
  private String zipcode;


  @Override
  public void setPrimaryKey(Long pk) {
    this.memberId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.memberId;
  }


  /**
   * 地址.
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * 市id.
   */
  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  /**
   * 市id.
   */
  public Long getCityId() {
    return this.cityId;
  }

  /**
   * 市.
   */
  public void setCityName(String cityName) {
    this.cityName = cityName == null ? null : cityName.trim();
  }

  /**
   * 市.
   */
  public String getCityName() {
    return this.cityName;
  }

  /**
   * 联系人.
   */
  public void setContact(String contact) {
    this.contact = contact == null ? null : contact.trim();
  }

  /**
   * 联系人.
   */
  public String getContact() {
    return this.contact;
  }


  /**
   * 国家id.
   */
  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  /**
   * 国家id.
   */
  public Long getCountryId() {
    return this.countryId;
  }

  /**
   * 国家.
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName == null ? null : countryName.trim();
  }

  /**
   * 国家.
   */
  public String getCountryName() {
    return this.countryName;
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
   * 区id.
   */
  public void setDistrictId(Long districtId) {
    this.districtId = districtId;
  }

  /**
   * 区id.
   */
  public Long getDistrictId() {
    return this.districtId;
  }

  /**
   * 区.
   */
  public void setDistrictName(String districtName) {
    this.districtName = districtName == null ? null : districtName.trim();
  }

  /**
   * 区.
   */
  public String getDistrictName() {
    return this.districtName;
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
   * 是否默认加急.
   */
  public void setUrgent(Boolean urgent) {
    this.urgent = urgent;
  }

  /**
   * 是否默认加急.
   */
  public Boolean isUrgent() {
    return this.urgent;
  }

  /**
   * 会员id.
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 会员id.
   */
  public Long getMemberId() {
    return this.memberId;
  }

  /**
   * 会员名称.
   */
  public void setMemberName(String memberName) {
    this.memberName = memberName == null ? null : memberName.trim();
  }

  /**
   * 会员名称.
   */
  public String getMemberName() {
    return this.memberName;
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
   * 支付帐号.
   */
  public void setPaymentAccount(String paymentAccount) {
    this.paymentAccount = paymentAccount == null ? null : paymentAccount.trim();
  }

  /**
   * 支付帐号.
   */
  public String getPaymentAccount() {
    return this.paymentAccount;
  }

  /**
   * 省id.
   */
  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  /**
   * 省id.
   */
  public Long getProvinceId() {
    return this.provinceId;
  }

  /**
   * 省.
   */
  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName == null ? null : provinceName.trim();
  }

  /**
   * 省.
   */
  public String getProvinceName() {
    return this.provinceName;
  }

  /**
   * 性别.
   */
  public void setSex(SexType sex) {
    this.sex = sex;
  }

  /**
   * 性别.
   */
  public SexType getSex() {
    return this.sex;
  }

  /**
   * 状态.
   */
  public void setStatus(MemberStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public MemberStatus getStatus() {
    return this.status;
  }

  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }


  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
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
   * 邮编.
   */
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode == null ? null : zipcode.trim();
  }

  /**
   * 邮编.
   */
  public String getZipcode() {
    return this.zipcode;
  }
}