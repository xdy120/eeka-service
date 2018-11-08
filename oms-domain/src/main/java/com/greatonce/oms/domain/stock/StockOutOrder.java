package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.stock.StockOutOrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 出库单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockOutOrder extends VersionDO {
  /**
   * 地址.
   */
  private String address;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
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
   * 创建人.
   */
  private String creator;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 来源单号.
   */
  private String fromCode;
  /**
   * 最后出库时间.
   */
  private LocalDateTime lastOutTime;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 出库状态.
   */
  private OutStatus outStatus;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private StockOutOrderStatus status;
  /**
   * 出库单编码.
   */
  private String stockOutOrderCode;
  /**
   * 出库单id.
   */
  private Long stockOutOrderId;
  /**
   * 出库类型.
   */
  private String stockOutOrderType;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;
  /**
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;

  /**
   * 明细.
   */
  private List<StockOutOrderDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockOutOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockOutOrderId;
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
   * 审核时间.
   */
  public void setAuditedTime(LocalDateTime auditedTime) {
    this.auditedTime = auditedTime;
  }

  /**
   * 审核时间.
   */
  public LocalDateTime getAuditedTime() {
    return this.auditedTime;
  }

  /**
   * 审核人.
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   */
  public String getAuditor() {
    return this.auditor;
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
   * 创建人.
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   */
  public String getCreator() {
    return this.creator;
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
   * 来源单号.
   */
  public void setFromCode(String fromCode) {
    this.fromCode = fromCode == null ? null : fromCode.trim();
  }

  /**
   * 来源单号.
   */
  public String getFromCode() {
    return this.fromCode;
  }


  /**
   * 最后出库时间.
   */
  public void setLastOutTime(LocalDateTime lastOutTime) {
    this.lastOutTime = lastOutTime;
  }

  /**
   * 最后出库时间.
   */
  public LocalDateTime getLastOutTime() {
    return this.lastOutTime;
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
   * 出库状态.
   */
  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  /**
   * 出库状态.
   */
  public OutStatus getOutStatus() {
    return this.outStatus;
  }


  /**
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
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
   * 状态.
   */
  public void setStatus(StockOutOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public StockOutOrderStatus getStatus() {
    return this.status;
  }


  /**
   * 出库单编码.
   */
  public void setStockOutOrderCode(String stockOutOrderCode) {
    this.stockOutOrderCode = stockOutOrderCode == null ? null : stockOutOrderCode.trim();
  }

  /**
   * 出库单编码.
   */
  public String getStockOutOrderCode() {
    return this.stockOutOrderCode;
  }


  /**
   * 出库单id.
   */
  public void setStockOutOrderId(Long stockOutOrderId) {
    this.stockOutOrderId = stockOutOrderId;
  }

  /**
   * 出库单id.
   */
  public Long getStockOutOrderId() {
    return this.stockOutOrderId;
  }

  /**
   * 出库类型.
   */
  public void setStockOutOrderType(String stockOutOrderType) {
    this.stockOutOrderType = stockOutOrderType == null ? null : stockOutOrderType.trim();
  }

  /**
   * 出库类型.
   */
  public String getStockOutOrderType() {
    return this.stockOutOrderType;
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
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }

  /**
   * 虚拟仓id.
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   */
  public Long getVirtualWarehouseId() {
    return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   */
  public String getVirtualWarehouseName() {
    return this.virtualWarehouseName;
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
   * 明细.
   */
  public void setDetails(List<StockOutOrderDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<StockOutOrderDetail> getDetails() {
    return this.details;
  }
}