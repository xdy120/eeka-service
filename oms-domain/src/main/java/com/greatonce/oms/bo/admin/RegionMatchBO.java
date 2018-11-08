package com.greatonce.oms.bo.admin;

import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.domain.admin.Region;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/9/27
 */
public class RegionMatchBO {

  /**
   * 国家
   */
  private Region country;
  /**
   * 省
   */
  private Region province;
  /**
   * 市
   */
  private Region city;
  /**
   * 区
   */
  private Region district;
  /**
   * 地址映射
   */
  private MallRegionMapping mapping;
  /**
   * 是否匹配成功.
   */
  private boolean isMatch;
  /**
   * 是否匹配映射.
   */
  private boolean isMatchMapping;

  public Region getCountry() {
    return country;
  }

  public void setCountry(Region country) {
    this.country = country;
  }

  public Region getProvince() {
    return province;
  }

  public void setProvince(Region province) {
    this.province = province;
  }

  public Region getCity() {
    return city;
  }

  public void setCity(Region city) {
    this.city = city;
  }

  public Region getDistrict() {
    return district;
  }

  public void setDistrict(Region district) {
    this.district = district;
  }

  public MallRegionMapping getMapping() {
    return mapping;
  }

  public void setMapping(MallRegionMapping mapping) {
    this.mapping = mapping;
  }

  public boolean isMatch() {
    return isMatch;
  }

  public void setMatch(boolean match) {
    isMatch = match;
  }

  public boolean isMatchMapping() {
    return isMatchMapping;
  }

  public void setMatchMapping(boolean matchMapping) {
    isMatchMapping = matchMapping;
  }
}
