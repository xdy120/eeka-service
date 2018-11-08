package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author buer
 * @version 2017-12-27 19:59
 */
public class SalesOrderModifyReceiverInfoBO extends VersionBO {

  private String contact;
  private String mobile;
  private String telephone;
  private String encryptContact;
  private String encryptMobile;
  private String encryptTelephone;
  private String address;
  private Long countryId;
  private Long provinceId;
  private Long cityId;
  private Long districtId;
  private String countryName;
  private String provinceName;
  private String cityName;
  private String districtName;

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEncryptContact() {
    return encryptContact;
  }

  public void setEncryptContact(String encryptContact) {
    this.encryptContact = encryptContact;
  }

  public String getEncryptMobile() {
    return encryptMobile;
  }

  public void setEncryptMobile(String encryptMobile) {
    this.encryptMobile = encryptMobile;
  }

  public String getEncryptTelephone() {
    return encryptTelephone;
  }

  public void setEncryptTelephone(String encryptTelephone) {
    this.encryptTelephone = encryptTelephone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public Long getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public Long getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Long districtId) {
    this.districtId = districtId;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }
}
