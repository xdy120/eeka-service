package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.enums.mall.JdWaybillProviderType;
import java.io.Serializable;

/**
 *
 * 电子面单配置.
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/15
 */
public class WaybillSetting implements Serializable {

  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名.
   */
  private String storeName;
  /**
   * 发件人.
   */
  private String senderContact;
  /**
   * 发件人手机.
   */
  private String senderMobile;
  /**
   * 发件人电话.
   */
  private String senderTelephone;
  /**
   * 发件人地址.
   */
  private String senderAddress;
  /**
   * 国家id.
   */
  private Long countryId;
  /**
   * 国家.
   */
  private String countryName;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * 市id.
   */
  private Long cityId;
  /**
   * 市.
   */
  private String cityName;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 快递公司类型（京东面单）.
   */
  private JdWaybillProviderType providerType;
  /**
   * 发货网点编码（京东面单）.
   */
  private String branchCode;
  /**
   * 财务结算编码（京东面单）.
   */
  private String settlementCode;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public JdWaybillProviderType getProviderType() {
    return providerType;
  }

  public void setProviderType(JdWaybillProviderType providerType) {
    this.providerType = providerType;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getSettlementCode() {
    return settlementCode;
  }

  public void setSettlementCode(String settlementCode) {
    this.settlementCode = settlementCode;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getSenderContact() {
    return senderContact;
  }

  public void setSenderContact(String senderContact) {
    this.senderContact = senderContact;
  }

  public String getSenderMobile() {
    return senderMobile;
  }

  public void setSenderMobile(String senderMobile) {
    this.senderMobile = senderMobile;
  }

  public String getSenderTelephone() {
    return senderTelephone;
  }

  public void setSenderTelephone(String senderTelephone) {
    this.senderTelephone = senderTelephone;
  }

  public String getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(String senderAddress) {
    this.senderAddress = senderAddress;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public Long getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Long getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Long districtId) {
    this.districtId = districtId;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }
}
