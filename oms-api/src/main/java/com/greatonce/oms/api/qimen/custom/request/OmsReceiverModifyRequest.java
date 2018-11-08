package com.greatonce.oms.api.qimen.custom.request;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class OmsReceiverModifyRequest implements Serializable {

  @JSONField(name = "tid")
  private String tid;
  @JSONField(name = "storeId")
  private Long storeId;
  @JSONField(name = "country")
  private String country;
  @JSONField(name = "provinceName")
  private String provinceName;
  @JSONField(name = "cityName")
  private String cityName;
  @JSONField(name = "countyName")
  private String countyName;
  @JSONField(name = "town")
  private String town;
  @JSONField(name = "address")
  private String address;
  @JSONField(name = "postCode")
  private String postCode;
  @JSONField(name = "consignee")
  private String consignee;
  @JSONField(name = "mobile")
  private String mobile;

  public String getTid() {
    return tid;
  }

  public void setTid(String tid) {
    this.tid = tid;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
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

  public String getCountyName() {
    return countyName;
  }

  public void setCountyName(String countyName) {
    this.countyName = countyName;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
