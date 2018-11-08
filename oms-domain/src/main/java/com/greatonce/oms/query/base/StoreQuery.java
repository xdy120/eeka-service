package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class StoreQuery extends Query {
  /**
   * 授权码过期时间开始.
   */
  private LocalDateTime accessTokenExpirationTimeBegin;
  /**
   * 授权码过期时间结束.
   */
  private LocalDateTime accessTokenExpirationTimeEnd;
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
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 刷新码过期时间开始.
   */
  private LocalDateTime refreshTokenExpirationTimeBegin;
  /**
   * 刷新码过期时间结束.
   */
  private LocalDateTime refreshTokenExpirationTimeEnd;
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
   * .
   */
  private List<String> storeCodes;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * .
   */
  private List<Long> storeIds;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 电话.
   */
  private String telephone;


  /**
   * 授权码过期时间开始.
   * @param accessTokenExpirationTimeBegin 开始.
   */
  public void setAccessTokenExpirationTimeBegin(LocalDateTime accessTokenExpirationTimeBegin) {
    this.accessTokenExpirationTimeBegin = accessTokenExpirationTimeBegin;
  }

  /**
   * 授权码过期时间开始.
   * @return 授权码过期时间开始
   */
  public LocalDateTime getAccessTokenExpirationTimeBegin() {
    return this.accessTokenExpirationTimeBegin;
  }

  /**
   * 授权码过期时间结束.
   * @param accessTokenExpirationTimeEnd 结束
   */
  public void setAccessTokenExpirationTimeEnd(LocalDateTime accessTokenExpirationTimeEnd) {
    this.accessTokenExpirationTimeEnd = accessTokenExpirationTimeEnd;
  }

  /**
   * 授权码过期时间结束.
   * @return 授权码过期时间结束
   */
  public LocalDateTime getAccessTokenExpirationTimeEnd() {
    return this.accessTokenExpirationTimeEnd;
  }

  /**
   * 授权码.
   * @param accessToken 授权码
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken == null ? null : accessToken.trim();
  }

  /**
   * 授权码.
   * @return 授权码
   */
  public String getAccessToken() {
      return this.accessToken;
  }

  /**
   * 地址.
   * @param address 地址
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   * @return 地址
   */
  public String getAddress() {
      return this.address;
  }

  /**
   * 公司id.
   * @param companyId 公司id
   */
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  /**
   * 公司id.
   * @return 公司id
   */
  public Long getCompanyId() {
      return this.companyId;
  }

  /**
   * 公司名称.
   * @param companyName 公司名称
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   * @return 公司名称
   */
  public String getCompanyName() {
      return this.companyName;
  }

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
   * 商城应用id.
   * @param mallAppId 商城应用id
   */
  public void setMallAppId(Long mallAppId) {
    this.mallAppId = mallAppId;
  }

  /**
   * 商城应用id.
   * @return 商城应用id
   */
  public Long getMallAppId() {
      return this.mallAppId;
  }

  /**
   * 商城类型.
   * @param mallType 商城类型
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   * @return 商城类型
   */
  public MallType getMallType() {
      return this.mallType;
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
   * 刷新码过期时间开始.
   * @param refreshTokenExpirationTimeBegin 开始.
   */
  public void setRefreshTokenExpirationTimeBegin(LocalDateTime refreshTokenExpirationTimeBegin) {
    this.refreshTokenExpirationTimeBegin = refreshTokenExpirationTimeBegin;
  }

  /**
   * 刷新码过期时间开始.
   * @return 刷新码过期时间开始
   */
  public LocalDateTime getRefreshTokenExpirationTimeBegin() {
    return this.refreshTokenExpirationTimeBegin;
  }

  /**
   * 刷新码过期时间结束.
   * @param refreshTokenExpirationTimeEnd 结束
   */
  public void setRefreshTokenExpirationTimeEnd(LocalDateTime refreshTokenExpirationTimeEnd) {
    this.refreshTokenExpirationTimeEnd = refreshTokenExpirationTimeEnd;
  }

  /**
   * 刷新码过期时间结束.
   * @return 刷新码过期时间结束
   */
  public LocalDateTime getRefreshTokenExpirationTimeEnd() {
    return this.refreshTokenExpirationTimeEnd;
  }

  /**
   * 刷新码.
   * @param refreshToken 刷新码
   */
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken == null ? null : refreshToken.trim();
  }

  /**
   * 刷新码.
   * @return 刷新码
   */
  public String getRefreshToken() {
      return this.refreshToken;
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
   * 店铺配置.
   * @param settingJson 店铺配置
   */
  public void setSettingJson(String settingJson) {
    this.settingJson = settingJson == null ? null : settingJson.trim();
  }

  /**
   * 店铺配置.
   * @return 店铺配置
   */
  public String getSettingJson() {
      return this.settingJson;
  }

  /**
   * 店铺编码.
   * @param storeCode 店铺编码
   */
  public void setStoreCode(String storeCode) {
    this.storeCode = storeCode == null ? null : storeCode.trim();
  }

  /**
   * 店铺编码.
   * @return 店铺编码
   */
  public String getStoreCode() {
      return this.storeCode;
  }

  /**
   * .
   * @param storeCodes 
   */
  public void setStoreCodes(List<String> storeCodes) {
    this.storeCodes = storeCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getStoreCodes() {
      return this.storeCodes;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * .
   * @param storeIds 
   */
  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getStoreIds() {
      return this.storeIds;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
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
}