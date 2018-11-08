package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
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
public class StockOutOrderQuery extends Query {
  /**
   * 地址.
   */
  private String address;
  /**
   * 审核时间开始.
   */
  private LocalDateTime auditedTimeBegin;
  /**
   * 审核时间结束.
   */
  private LocalDateTime auditedTimeEnd;
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
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * .
   */
  private List<String> fromCodes;
  /**
   * 最后出库时间开始.
   */
  private LocalDateTime lastOutTimeBegin;
  /**
   * 最后出库时间结束.
   */
  private LocalDateTime lastOutTimeEnd;
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
   * 出库状态.
   */
  private OutStatus outStatus;
  /**
   * .
   */
  private List<OutStatus> outStatuses;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * .
   */
  private List<String> outerCodes;
  /**
   * .
   */
  private List<String> productCodes;
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
   * .
   */
  private List<String> skuCodes;
  /**
   * 状态.
   */
  private StockOutOrderStatus status;
  /**
   * .
   */
  private List<StockOutOrderStatus> statuses;
  /**
   * 出库单编码.
   */
  private String stockOutOrderCode;
  /**
   * .
   */
  private List<String> stockOutOrderCodes;
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
   * 审核时间开始.
   * @param auditedTimeBegin 开始.
   */
  public void setAuditedTimeBegin(LocalDateTime auditedTimeBegin) {
    this.auditedTimeBegin = auditedTimeBegin;
  }

  /**
   * 审核时间开始.
   * @return 审核时间开始
   */
  public LocalDateTime getAuditedTimeBegin() {
    return this.auditedTimeBegin;
  }

  /**
   * 审核时间结束.
   * @param auditedTimeEnd 结束
   */
  public void setAuditedTimeEnd(LocalDateTime auditedTimeEnd) {
    this.auditedTimeEnd = auditedTimeEnd;
  }

  /**
   * 审核时间结束.
   * @return 审核时间结束
   */
  public LocalDateTime getAuditedTimeEnd() {
    return this.auditedTimeEnd;
  }

  /**
   * 审核人.
   * @param auditor 审核人
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   * @return 审核人
   */
  public String getAuditor() {
      return this.auditor;
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
   * 创建人.
   * @param creator 创建人
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   * @return 创建人
   */
  public String getCreator() {
      return this.creator;
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
   * 来源单号.
   * @param fromCode 来源单号
   */
  public void setFromCode(String fromCode) {
    this.fromCode = fromCode == null ? null : fromCode.trim();
  }

  /**
   * 来源单号.
   * @return 来源单号
   */
  public String getFromCode() {
      return this.fromCode;
  }

  /**
   * .
   * @param fromCodes 
   */
  public void setFromCodes(List<String> fromCodes) {
    this.fromCodes = fromCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getFromCodes() {
      return this.fromCodes;
  }

  /**
   * 最后出库时间开始.
   * @param lastOutTimeBegin 开始.
   */
  public void setLastOutTimeBegin(LocalDateTime lastOutTimeBegin) {
    this.lastOutTimeBegin = lastOutTimeBegin;
  }

  /**
   * 最后出库时间开始.
   * @return 最后出库时间开始
   */
  public LocalDateTime getLastOutTimeBegin() {
    return this.lastOutTimeBegin;
  }

  /**
   * 最后出库时间结束.
   * @param lastOutTimeEnd 结束
   */
  public void setLastOutTimeEnd(LocalDateTime lastOutTimeEnd) {
    this.lastOutTimeEnd = lastOutTimeEnd;
  }

  /**
   * 最后出库时间结束.
   * @return 最后出库时间结束
   */
  public LocalDateTime getLastOutTimeEnd() {
    return this.lastOutTimeEnd;
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
   * 出库状态.
   * @param outStatus 出库状态
   */
  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  /**
   * 出库状态.
   * @return 出库状态
   */
  public OutStatus getOutStatus() {
      return this.outStatus;
  }

  /**
   * .
   * @param outStatuses 
   */
  public void setOutStatuses(List<OutStatus> outStatuses) {
    this.outStatuses = outStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<OutStatus> getOutStatuses() {
      return this.outStatuses;
  }

  /**
   * 外部单号.
   * @param outerCode 外部单号
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   * @return 外部单号
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * .
   * @param outerCodes 
   */
  public void setOuterCodes(List<String> outerCodes) {
    this.outerCodes = outerCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOuterCodes() {
      return this.outerCodes;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
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
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(StockOutOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public StockOutOrderStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<StockOutOrderStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<StockOutOrderStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 出库单编码.
   * @param stockOutOrderCode 出库单编码
   */
  public void setStockOutOrderCode(String stockOutOrderCode) {
    this.stockOutOrderCode = stockOutOrderCode == null ? null : stockOutOrderCode.trim();
  }

  /**
   * 出库单编码.
   * @return 出库单编码
   */
  public String getStockOutOrderCode() {
      return this.stockOutOrderCode;
  }

  /**
   * .
   * @param stockOutOrderCodes 
   */
  public void setStockOutOrderCodes(List<String> stockOutOrderCodes) {
    this.stockOutOrderCodes = stockOutOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getStockOutOrderCodes() {
      return this.stockOutOrderCodes;
  }

  /**
   * 出库单id.
   * @param stockOutOrderId 出库单id
   */
  public void setStockOutOrderId(Long stockOutOrderId) {
    this.stockOutOrderId = stockOutOrderId;
  }

  /**
   * 出库单id.
   * @return 出库单id
   */
  public Long getStockOutOrderId() {
      return this.stockOutOrderId;
  }

  /**
   * 出库类型.
   * @param stockOutOrderType 出库类型
   */
  public void setStockOutOrderType(String stockOutOrderType) {
    this.stockOutOrderType = stockOutOrderType == null ? null : stockOutOrderType.trim();
  }

  /**
   * 出库类型.
   * @return 出库类型
   */
  public String getStockOutOrderType() {
      return this.stockOutOrderType;
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
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }

  /**
   * 虚拟仓id.
   * @param virtualWarehouseId 虚拟仓id
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   * @return 虚拟仓id
   */
  public Long getVirtualWarehouseId() {
      return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   * @param virtualWarehouseName 虚拟仓名称
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   * @return 虚拟仓名称
   */
  public String getVirtualWarehouseName() {
      return this.virtualWarehouseName;
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
}