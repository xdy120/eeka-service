package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WmsOrderStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 配货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class DispatchOrder extends VersionDO {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 地址.
   */
  private String address;
  /**
   * 买家备注.
   */
  private String buyerMemo;
  /**
   * 菜鸟订单号.
   */
  private String cainiaoOrderId;
  /**
   * 市id.
   */
  private Long cityId;
  /**
   * 市.
   */
  private String cityName;
  /**
   * 到付金额.
   */
  private Double codAmount;
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
   * 优惠金额.
   */
  private Double discountAmount;
  /**
   * 配货单编号.
   */
  private String dispatchOrderCode;
  /**
   * 配货单id.
   */
  private Long dispatchOrderId;
  /**
   * 分销金额.
   */
  private Double distributionAmount;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 发票信息.
   */
  private String invoiceInfoJson;
  /**
   * 是否货到付款.
   */
  private Boolean cod;
  /**
   * 是否发货完成.
   */
  private Boolean deliveryFinish;
  /**
   * 是否获取面单.
   */
  private Boolean getWaybill;
  /**
   * 是否包含换货.
   */
  private Boolean hasExchange;
  /**
   * 是否合单.
   */
  private Boolean merge;
  /**
   * 是否开发票.
   */
  private Boolean needInvoice;
  /**
   * 是否线下发货.
   */
  private Boolean offlineDelivery;
  /**
   * 是否加急.
   */
  private Boolean urgent;
  /**
   * 是否wms取消.
   */
  private Boolean wmsCancel;
  /**
   * 最后一次发货时间.
   */
  private LocalDateTime lastDeliveryTime;
  /**
   * 集结地编码.
   */
  private String massCode;
  /**
   * 集结地名称.
   */
  private String massName;
  /**
   * 集结地短名称.
   */
  private String massShortName;
  /**
   * 会员id.
   */
  private Long memberId;
  /**
   * 会员名称.
   */
  private String memberName;
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
   * 包裹编号.
   */
  private String packageNo;
  /**
   * 过账状态.
   */
  private PostStatus postStatus;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 卖家备注.
   */
  private String sellerMemo;
  /**
   * 销售金额.
   */
  private Double sellingAmount;
  /**
   * 结算金额.
   */
  private Double settlementAmount;
  /**
   * 状态.
   */
  private DispatchOrderStatus status;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 建议快递id.
   */
  private Long suggestExpressId;
  /**
   * 建议快递名称.
   */
  private String suggestExpressName;
  /**
   * 建议快递单号.
   */
  private String suggestExpressNo;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 仓库id.
   */
  private Long warehouseId;
  /**
   * 仓库名称.
   */
  private String warehouseName;
  /**
   * 面单信息.
   */
  private String waybillInfoJson;
  /**
   * 重量.
   */
  private Double weight;
  /**
   * 仓库状态.
   */
  private WmsOrderStatus wmsStatus;
  /**
   * 邮编.
   */
  private String zipcode;

  /**
   * 配货单明细.
   */
  private List<DispatchOrderDetail> details;
  /**
   * 发票信息.
   */
  private InvoiceInfo invoiceInfo;
  /**
   * 电子面单信息.
   */
  private WaybillInfo waybillInfo;

  @Override
  public void setPrimaryKey(Long pk) {
    this.dispatchOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.dispatchOrderId;
  }


  /**
   * 实际金额.
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 实际金额.
   */
  public Double getActualAmount() {
    return this.actualAmount;
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
   * 买家备注.
   */
  public void setBuyerMemo(String buyerMemo) {
    this.buyerMemo = buyerMemo == null ? null : buyerMemo.trim();
  }

  /**
   * 买家备注.
   */
  public String getBuyerMemo() {
    return this.buyerMemo;
  }

  /**
   * 菜鸟订单号.
   */
  public void setCainiaoOrderId(String cainiaoOrderId) {
    this.cainiaoOrderId = cainiaoOrderId == null ? null : cainiaoOrderId.trim();
  }

  /**
   * 菜鸟订单号.
   */
  public String getCainiaoOrderId() {
    return this.cainiaoOrderId;
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
   * 到付金额.
   */
  public void setCodAmount(Double codAmount) {
    this.codAmount = codAmount;
  }

  /**
   * 到付金额.
   */
  public Double getCodAmount() {
    return this.codAmount;
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
   * 优惠金额.
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   */
  public Double getDiscountAmount() {
    return this.discountAmount;
  }

  /**
   * 配货单编号.
   */
  public void setDispatchOrderCode(String dispatchOrderCode) {
    this.dispatchOrderCode = dispatchOrderCode == null ? null : dispatchOrderCode.trim();
  }

  /**
   * 配货单编号.
   */
  public String getDispatchOrderCode() {
    return this.dispatchOrderCode;
  }


  /**
   * 配货单id.
   */
  public void setDispatchOrderId(Long dispatchOrderId) {
    this.dispatchOrderId = dispatchOrderId;
  }

  /**
   * 配货单id.
   */
  public Long getDispatchOrderId() {
    return this.dispatchOrderId;
  }

  /**
   * 分销金额.
   */
  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  /**
   * 分销金额.
   */
  public Double getDistributionAmount() {
    return this.distributionAmount;
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
   * 发票信息.
   */
  public void setInvoiceInfoJson(String invoiceInfoJson) {
    this.invoiceInfoJson = invoiceInfoJson == null ? null : invoiceInfoJson.trim();
  }

  /**
   * 发票信息.
   */
  public String getInvoiceInfoJson() {
    return this.invoiceInfoJson;
  }

  /**
   * 是否货到付款.
   */
  public void setCod(Boolean cod) {
    this.cod = cod;
  }

  /**
   * 是否货到付款.
   */
  public Boolean isCod() {
    return this.cod;
  }

  /**
   * 是否发货完成.
   */
  public void setDeliveryFinish(Boolean deliveryFinish) {
    this.deliveryFinish = deliveryFinish;
  }

  /**
   * 是否发货完成.
   */
  public Boolean isDeliveryFinish() {
    return this.deliveryFinish;
  }

  /**
   * 是否获取面单.
   */
  public void setGetWaybill(Boolean getWaybill) {
    this.getWaybill = getWaybill;
  }

  /**
   * 是否获取面单.
   */
  public Boolean isGetWaybill() {
    return this.getWaybill;
  }

  /**
   * 是否包含换货.
   */
  public void setHasExchange(Boolean hasExchange) {
    this.hasExchange = hasExchange;
  }

  /**
   * 是否包含换货.
   */
  public Boolean isHasExchange() {
    return this.hasExchange;
  }

  /**
   * 是否合单.
   */
  public void setMerge(Boolean merge) {
    this.merge = merge;
  }

  /**
   * 是否合单.
   */
  public Boolean isMerge() {
    return this.merge;
  }

  /**
   * 是否开发票.
   */
  public void setNeedInvoice(Boolean needInvoice) {
    this.needInvoice = needInvoice;
  }

  /**
   * 是否开发票.
   */
  public Boolean isNeedInvoice() {
    return this.needInvoice;
  }

  /**
   * 是否线下发货.
   */
  public void setOfflineDelivery(Boolean offlineDelivery) {
    this.offlineDelivery = offlineDelivery;
  }

  /**
   * 是否线下发货.
   */
  public Boolean isOfflineDelivery() {
    return this.offlineDelivery;
  }

  /**
   * 是否加急.
   */
  public void setUrgent(Boolean urgent) {
    this.urgent = urgent;
  }

  /**
   * 是否加急.
   */
  public Boolean isUrgent() {
    return this.urgent;
  }

  /**
   * 是否wms取消.
   */
  public void setWmsCancel(Boolean wmsCancel) {
    this.wmsCancel = wmsCancel;
  }

  /**
   * 是否wms取消.
   */
  public Boolean isWmsCancel() {
    return this.wmsCancel;
  }

  /**
   * 最后一次发货时间.
   */
  public void setLastDeliveryTime(LocalDateTime lastDeliveryTime) {
    this.lastDeliveryTime = lastDeliveryTime;
  }

  /**
   * 最后一次发货时间.
   */
  public LocalDateTime getLastDeliveryTime() {
    return this.lastDeliveryTime;
  }

  /**
   * 集结地编码.
   */
  public void setMassCode(String massCode) {
    this.massCode = massCode == null ? null : massCode.trim();
  }

  /**
   * 集结地编码.
   */
  public String getMassCode() {
    return this.massCode;
  }

  /**
   * 集结地名称.
   */
  public void setMassName(String massName) {
    this.massName = massName == null ? null : massName.trim();
  }

  /**
   * 集结地名称.
   */
  public String getMassName() {
    return this.massName;
  }

  /**
   * 集结地短名称.
   */
  public void setMassShortName(String massShortName) {
    this.massShortName = massShortName == null ? null : massShortName.trim();
  }

  /**
   * 集结地短名称.
   */
  public String getMassShortName() {
    return this.massShortName;
  }

  /**
   * 会员id.
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 会员id.
   */
  public Long getMemberId() {
    return this.memberId;
  }

  /**
   * 会员名称.
   */
  public void setMemberName(String memberName) {
    this.memberName = memberName == null ? null : memberName.trim();
  }

  /**
   * 会员名称.
   */
  public String getMemberName() {
    return this.memberName;
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
   * 包裹编号.
   */
  public void setPackageNo(String packageNo) {
    this.packageNo = packageNo == null ? null : packageNo.trim();
  }

  /**
   * 包裹编号.
   */
  public String getPackageNo() {
    return this.packageNo;
  }

  /**
   * 过账状态.
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   */
  public PostStatus getPostStatus() {
    return this.postStatus;
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
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
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
   * 卖家备注.
   */
  public void setSellerMemo(String sellerMemo) {
    this.sellerMemo = sellerMemo == null ? null : sellerMemo.trim();
  }

  /**
   * 卖家备注.
   */
  public String getSellerMemo() {
    return this.sellerMemo;
  }

  /**
   * 销售金额.
   */
  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  /**
   * 销售金额.
   */
  public Double getSellingAmount() {
    return this.sellingAmount;
  }

  /**
   * 结算金额.
   */
  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  /**
   * 结算金额.
   */
  public Double getSettlementAmount() {
    return this.settlementAmount;
  }


  /**
   * 状态.
   */
  public void setStatus(DispatchOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public DispatchOrderStatus getStatus() {
    return this.status;
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
   * 建议快递id.
   */
  public void setSuggestExpressId(Long suggestExpressId) {
    this.suggestExpressId = suggestExpressId;
  }

  /**
   * 建议快递id.
   */
  public Long getSuggestExpressId() {
    return this.suggestExpressId;
  }

  /**
   * 建议快递名称.
   */
  public void setSuggestExpressName(String suggestExpressName) {
    this.suggestExpressName = suggestExpressName == null ? null : suggestExpressName.trim();
  }

  /**
   * 建议快递名称.
   */
  public String getSuggestExpressName() {
    return this.suggestExpressName;
  }

  /**
   * 建议快递单号.
   */
  public void setSuggestExpressNo(String suggestExpressNo) {
    this.suggestExpressNo = suggestExpressNo == null ? null : suggestExpressNo.trim();
  }

  /**
   * 建议快递单号.
   */
  public String getSuggestExpressNo() {
    return this.suggestExpressNo;
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
   * 面单信息.
   */
  public void setWaybillInfoJson(String waybillInfoJson) {
    this.waybillInfoJson = waybillInfoJson == null ? null : waybillInfoJson.trim();
  }

  /**
   * 面单信息.
   */
  public String getWaybillInfoJson() {
    return this.waybillInfoJson;
  }

  /**
   * 重量.
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * 重量.
   */
  public Double getWeight() {
    return this.weight;
  }

  /**
   * 仓库状态.
   */
  public void setWmsStatus(WmsOrderStatus wmsStatus) {
    this.wmsStatus = wmsStatus;
  }

  /**
   * 仓库状态.
   */
  public WmsOrderStatus getWmsStatus() {
    return this.wmsStatus;
  }

  /**
   * 邮编.
   */
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode == null ? null : zipcode.trim();
  }

  /**
   * 邮编.
   */
  public String getZipcode() {
    return this.zipcode;
  }

  /**
   * 配货单明细.
   */
  public void setDetails(List<DispatchOrderDetail> details) {
    this.details = details;
  }

  /**
   * 配货单明细.
   */
  public List<DispatchOrderDetail> getDetails() {
    return this.details;
  }

  /**
   * 发票信息.
   */
  public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
    this.invoiceInfo = invoiceInfo;
  }

  /**
   * 发票信息.
   */
  public InvoiceInfo getInvoiceInfo() {
    return this.invoiceInfo;
  }

  /**
   * 电子面单信息.
   */
  public void setWaybillInfo(WaybillInfo waybillInfo) {
    this.waybillInfo = waybillInfo;
  }

  /**
   * 电子面单信息.
   */
  public WaybillInfo getWaybillInfo() {
    return this.waybillInfo;
  }
}