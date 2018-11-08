package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import java.util.List;

/**
 * @author buer
 * @version 2018-01-06 10:31
 */
public class DispatchBO<T extends DispatchDetailBO> extends VersionBO {

  private List<T> details;
  private String contact;
  private String mobile;
  private String telephone;
  private Long countryId;
  private Long provinceId;
  private Long cityId;
  private Long districtId;
  private String countryName;
  private String provinceName;
  private String cityName;
  private String districtName;
  private String address;
  private String remark;
  /**
   * 是否线下发货
   */
  private Boolean offlineDelivery;

  public List<T> getDetails() {
    return details;
  }

  public void setDetails(List<T> details) {
    this.details = details;
  }

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean getOfflineDelivery() {
    return offlineDelivery;
  }

  public void setOfflineDelivery(Boolean offlineDelivery) {
    this.offlineDelivery = offlineDelivery;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
