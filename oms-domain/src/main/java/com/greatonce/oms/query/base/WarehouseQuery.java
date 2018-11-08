package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.WarehouseType;
import com.greatonce.oms.domain.enums.WaybillStrategy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class WarehouseQuery extends Query {
  /**
   * 地址.
   */
  private String address;
  /**
   * 品牌编码.
   */
  private String brandCodes;
  /**
   * 品牌名称.
   */
  private String brandNames;
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
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 是否匹配区域.
   */
  private Boolean matchRegion;
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
   * 外部编码.
   */
  private String outerCode;
  /**
   * 货主编码.
   */
  private String ownerCode;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 仓库代码.
   */
  private String warehouseCode;
  /**
   * .
   */
  private List<String> warehouseCodes;
  /**
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * .
   */
  private List<Long> warehouseIds;
  /**
   * 仓库名称.
   */
  private String warehouseName;
  /**
   * 仓库类型.
   */
  private WarehouseType warehouseType;
  /**
   * .
   */
  private List<WarehouseType> warehouseTypes;
  /**
   * 面单策略.
   */
  private WaybillStrategy waybillStrategy;
  /**
   * 仓库应用id.
   */
  private Long wmsAppId;


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
   * 品牌编码.
   * @param brandCodes 品牌编码
   */
  public void setBrandCodes(String brandCodes) {
    this.brandCodes = brandCodes == null ? null : brandCodes.trim();
  }

  /**
   * 品牌编码.
   * @return 品牌编码
   */
  public String getBrandCodes() {
      return this.brandCodes;
  }

  /**
   * 品牌名称.
   * @param brandNames 品牌名称
   */
  public void setBrandNames(String brandNames) {
    this.brandNames = brandNames == null ? null : brandNames.trim();
  }

  /**
   * 品牌名称.
   * @return 品牌名称
   */
  public String getBrandNames() {
      return this.brandNames;
  }

  /**
   * 市id.
   * @param cityId 市id
   */
  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  /**
   * 市id.
   * @return 市id
   */
  public Long getCityId() {
      return this.cityId;
  }

  /**
   * 市.
   * @param cityName 市
   */
  public void setCityName(String cityName) {
    this.cityName = cityName == null ? null : cityName.trim();
  }

  /**
   * 市.
   * @return 市
   */
  public String getCityName() {
      return this.cityName;
  }

  /**
   * 联系人.
   * @param contact 联系人
   */
  public void setContact(String contact) {
    this.contact = contact == null ? null : contact.trim();
  }

  /**
   * 联系人.
   * @return 联系人
   */
  public String getContact() {
      return this.contact;
  }

  /**
   * 国家id.
   * @param countryId 国家id
   */
  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  /**
   * 国家id.
   * @return 国家id
   */
  public Long getCountryId() {
      return this.countryId;
  }

  /**
   * 国家.
   * @param countryName 国家
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName == null ? null : countryName.trim();
  }

  /**
   * 国家.
   * @return 国家
   */
  public String getCountryName() {
      return this.countryName;
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
   * 区id.
   * @param districtId 区id
   */
  public void setDistrictId(Long districtId) {
    this.districtId = districtId;
  }

  /**
   * 区id.
   * @return 区id
   */
  public Long getDistrictId() {
      return this.districtId;
  }

  /**
   * 区.
   * @param districtName 区
   */
  public void setDistrictName(String districtName) {
    this.districtName = districtName == null ? null : districtName.trim();
  }

  /**
   * 区.
   * @return 区
   */
  public String getDistrictName() {
      return this.districtName;
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
   * 是否匹配区域.
   * @param matchRegion 是否匹配区域
   */
  public void setMatchRegion(Boolean matchRegion) {
    this.matchRegion = matchRegion;
  }

  /**
   * 是否匹配区域.
   * @return 是否匹配区域
   */
  public Boolean isMatchRegion() {
      return this.matchRegion;
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
   * 外部编码.
   * @param outerCode 外部编码
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部编码.
   * @return 外部编码
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * 货主编码.
   * @param ownerCode 货主编码
   */
  public void setOwnerCode(String ownerCode) {
    this.ownerCode = ownerCode == null ? null : ownerCode.trim();
  }

  /**
   * 货主编码.
   * @return 货主编码
   */
  public String getOwnerCode() {
      return this.ownerCode;
  }

  /**
   * 省id.
   * @param provinceId 省id
   */
  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  /**
   * 省id.
   * @return 省id
   */
  public Long getProvinceId() {
      return this.provinceId;
  }

  /**
   * 省.
   * @param provinceName 省
   */
  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName == null ? null : provinceName.trim();
  }

  /**
   * 省.
   * @return 省
   */
  public String getProvinceName() {
      return this.provinceName;
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
   * 仓库代码.
   * @param warehouseCode 仓库代码
   */
  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
  }

  /**
   * 仓库代码.
   * @return 仓库代码
   */
  public String getWarehouseCode() {
      return this.warehouseCode;
  }

  /**
   * .
   * @param warehouseCodes 
   */
  public void setWarehouseCodes(List<String> warehouseCodes) {
    this.warehouseCodes = warehouseCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getWarehouseCodes() {
      return this.warehouseCodes;
  }

  /**
   * 仓库id.
   * @param warehouseId 仓库id
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 仓库id.
   * @return 仓库id
   */
  public Long getWarehouseId() {
      return this.warehouseId;
  }

  /**
   * .
   * @param warehouseIds 
   */
  public void setWarehouseIds(List<Long> warehouseIds) {
    this.warehouseIds = warehouseIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getWarehouseIds() {
      return this.warehouseIds;
  }

  /**
   * 仓库名称.
   * @param warehouseName 仓库名称
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 仓库名称.
   * @return 仓库名称
   */
  public String getWarehouseName() {
      return this.warehouseName;
  }

  /**
   * 仓库类型.
   * @param warehouseType 仓库类型
   */
  public void setWarehouseType(WarehouseType warehouseType) {
    this.warehouseType = warehouseType;
  }

  /**
   * 仓库类型.
   * @return 仓库类型
   */
  public WarehouseType getWarehouseType() {
      return this.warehouseType;
  }

  /**
   * .
   * @param warehouseTypes 
   */
  public void setWarehouseTypes(List<WarehouseType> warehouseTypes) {
    this.warehouseTypes = warehouseTypes;
  }

  /**
   * .
   * @return 
   */
  public List<WarehouseType> getWarehouseTypes() {
      return this.warehouseTypes;
  }

  /**
   * 面单策略.
   * @param waybillStrategy 面单策略
   */
  public void setWaybillStrategy(WaybillStrategy waybillStrategy) {
    this.waybillStrategy = waybillStrategy;
  }

  /**
   * 面单策略.
   * @return 面单策略
   */
  public WaybillStrategy getWaybillStrategy() {
      return this.waybillStrategy;
  }

  /**
   * 仓库应用id.
   * @param wmsAppId 仓库应用id
   */
  public void setWmsAppId(Long wmsAppId) {
    this.wmsAppId = wmsAppId;
  }

  /**
   * 仓库应用id.
   * @return 仓库应用id
   */
  public Long getWmsAppId() {
      return this.wmsAppId;
  }
}