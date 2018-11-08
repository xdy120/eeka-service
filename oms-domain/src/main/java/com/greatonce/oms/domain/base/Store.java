package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.domain.enums.MallType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Store extends EnableDO {
  /**
   * 授权码过期时间.
   */
  private LocalDateTime accessTokenExpirationTime;
  /**
   * 授权码.
   */
  private String accessToken;
  /**
   * 地址.
   */
  private String address;
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
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 商城应用id.
   */
  private Long mallAppId;
  /**
   * 商城类型.
   */
  private MallType mallType;
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
   * 刷新码过期时间.
   */
  private LocalDateTime refreshTokenExpirationTime;
  /**
   * 刷新码.
   */
  private String refreshToken;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 店铺配置.
   */
  private String settingJson;
  /**
   * 店铺编码.
   */
  private String storeCode;
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
   * 商城应用.
   */
  private MallApp mallApp;
  /**
   * 店铺配置.
   */
  private StoreSetting setting;

  @Override
  public void setPrimaryKey(Long pk) {
    this.storeId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.storeId;
  }


  /**
   * 授权码过期时间.
   */
  public void setAccessTokenExpirationTime(LocalDateTime accessTokenExpirationTime) {
    this.accessTokenExpirationTime = accessTokenExpirationTime;
  }

  /**
   * 授权码过期时间.
   */
  public LocalDateTime getAccessTokenExpirationTime() {
    return this.accessTokenExpirationTime;
  }

  /**
   * 授权码.
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken == null ? null : accessToken.trim();
  }

  /**
   * 授权码.
   */
  public String getAccessToken() {
    return this.accessToken;
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
   * 商城应用id.
   */
  public void setMallAppId(Long mallAppId) {
    this.mallAppId = mallAppId;
  }

  /**
   * 商城应用id.
   */
  public Long getMallAppId() {
    return this.mallAppId;
  }

  /**
   * 商城类型.
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   */
  public MallType getMallType() {
    return this.mallType;
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
   * 刷新码过期时间.
   */
  public void setRefreshTokenExpirationTime(LocalDateTime refreshTokenExpirationTime) {
    this.refreshTokenExpirationTime = refreshTokenExpirationTime;
  }

  /**
   * 刷新码过期时间.
   */
  public LocalDateTime getRefreshTokenExpirationTime() {
    return this.refreshTokenExpirationTime;
  }

  /**
   * 刷新码.
   */
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken == null ? null : refreshToken.trim();
  }

  /**
   * 刷新码.
   */
  public String getRefreshToken() {
    return this.refreshToken;
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
   * 店铺配置.
   */
  public void setSettingJson(String settingJson) {
    this.settingJson = settingJson == null ? null : settingJson.trim();
  }

  /**
   * 店铺配置.
   */
  public String getSettingJson() {
    return this.settingJson;
  }

  /**
   * 店铺编码.
   */
  public void setStoreCode(String storeCode) {
    this.storeCode = storeCode == null ? null : storeCode.trim();
  }

  /**
   * 店铺编码.
   */
  public String getStoreCode() {
    return this.storeCode;
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
   * 商城应用.
   */
  public void setMallApp(MallApp mallApp) {
    this.mallApp = mallApp;
  }

  /**
   * 商城应用.
   */
  public MallApp getMallApp() {
    return this.mallApp;
  }

  /**
   * 店铺配置.
   */
  public void setSetting(StoreSetting setting) {
    this.setting = setting;
  }

  /**
   * 店铺配置.
   */
  public StoreSetting getSetting() {
    return this.setting;
  }
}