package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.admin.WmsApp;
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
public class Warehouse extends EnableDO {
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;
  /**
   * 仓库类型.
   */
  private WarehouseType warehouseType;
  /**
   * 面单策略.
   */
  private WaybillStrategy waybillStrategy;
  /**
   * 仓库应用id.
   */
  private Long wmsAppId;

  /**
   * 虚拟仓.
   */
  private List<VirtualWarehouse> virtualWarehouses;
  /**
   * 仓库应用.
   */
  private WmsApp wmsApp;

  @Override
  public void setPrimaryKey(Long pk) {
    this.warehouseId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.warehouseId;
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
   * 品牌编码.
   */
  public void setBrandCodes(String brandCodes) {
    this.brandCodes = brandCodes == null ? null : brandCodes.trim();
  }

  /**
   * 品牌编码.
   */
  public String getBrandCodes() {
    return this.brandCodes;
  }

  /**
   * 品牌名称.
   */
  public void setBrandNames(String brandNames) {
    this.brandNames = brandNames == null ? null : brandNames.trim();
  }

  /**
   * 品牌名称.
   */
  public String getBrandNames() {
    return this.brandNames;
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
   * 是否匹配区域.
   */
  public void setMatchRegion(Boolean matchRegion) {
    this.matchRegion = matchRegion;
  }

  /**
   * 是否匹配区域.
   */
  public Boolean isMatchRegion() {
    return this.matchRegion;
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
   * 外部编码.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部编码.
   */
  public String getOuterCode() {
    return this.outerCode;
  }

  /**
   * 货主编码.
   */
  public void setOwnerCode(String ownerCode) {
    this.ownerCode = ownerCode == null ? null : ownerCode.trim();
  }

  /**
   * 货主编码.
   */
  public String getOwnerCode() {
    return this.ownerCode;
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
   * 仓库代码.
   */
  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
  }

  /**
   * 仓库代码.
   */
  public String getWarehouseCode() {
    return this.warehouseCode;
  }


  /**
   * 仓库id.
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 仓库id.
   */
  public Long getWarehouseId() {
    return this.warehouseId;
  }


  /**
   * 仓库名称.
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 仓库名称.
   */
  public String getWarehouseName() {
    return this.warehouseName;
  }

  /**
   * 仓库类型.
   */
  public void setWarehouseType(WarehouseType warehouseType) {
    this.warehouseType = warehouseType;
  }

  /**
   * 仓库类型.
   */
  public WarehouseType getWarehouseType() {
    return this.warehouseType;
  }


  /**
   * 面单策略.
   */
  public void setWaybillStrategy(WaybillStrategy waybillStrategy) {
    this.waybillStrategy = waybillStrategy;
  }

  /**
   * 面单策略.
   */
  public WaybillStrategy getWaybillStrategy() {
    return this.waybillStrategy;
  }

  /**
   * 仓库应用id.
   */
  public void setWmsAppId(Long wmsAppId) {
    this.wmsAppId = wmsAppId;
  }

  /**
   * 仓库应用id.
   */
  public Long getWmsAppId() {
    return this.wmsAppId;
  }

  /**
   * 虚拟仓.
   */
  public void setVirtualWarehouses(List<VirtualWarehouse> virtualWarehouses) {
    this.virtualWarehouses = virtualWarehouses;
  }

  /**
   * 虚拟仓.
   */
  public List<VirtualWarehouse> getVirtualWarehouses() {
    return this.virtualWarehouses;
  }

  /**
   * 仓库应用.
   */
  public void setWmsApp(WmsApp wmsApp) {
    this.wmsApp = wmsApp;
  }

  /**
   * 仓库应用.
   */
  public WmsApp getWmsApp() {
    return this.wmsApp;
  }
}