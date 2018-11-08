package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class DispatchOrderQuery extends Query {
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
   * .
   */
  private List<String> contacts;
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
   * 发货快递id.
   */
  private String deliveryExpressId;
  /**
   * 发货快递单号.
   */
  private String deliveryExpressNo;
  /**
   * 优惠金额.
   */
  private Double discountAmount;
  /**
   * 配货单编号.
   */
  private String dispatchOrderCode;
  /**
   * .
   */
  private List<String> dispatchOrderCodes;
  /**
   * 配货单id.
   */
  private Long dispatchOrderId;
  /**
   * 分销金额.
   */
  private Double distributionAmount;
  /**
   * .
   */
  private List<String> distributionTradeIds;
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
   * 最后一次发货时间开始.
   */
  private LocalDateTime lastDeliveryTimeBegin;
  /**
   * 最后一次发货时间结束.
   */
  private LocalDateTime lastDeliveryTimeEnd;
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
   * .
   */
  private List<String> mobiles;
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
   * .
   */
  private List<String> salesOrderCodes;
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
   * .
   */
  private String smartKeys;
  /**
   * 状态.
   */
  private DispatchOrderStatus status;
  /**
   * .
   */
  private List<DispatchOrderStatus> statuses;
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
   * .
   */
  private List<String> telephones;
  /**
   * .
   */
  private List<String> tradeIds;
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
   * 实际金额.
   * @param actualAmount 实际金额
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 实际金额.
   * @return 实际金额
   */
  public Double getActualAmount() {
      return this.actualAmount;
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
   * 买家备注.
   * @param buyerMemo 买家备注
   */
  public void setBuyerMemo(String buyerMemo) {
    this.buyerMemo = buyerMemo == null ? null : buyerMemo.trim();
  }

  /**
   * 买家备注.
   * @return 买家备注
   */
  public String getBuyerMemo() {
      return this.buyerMemo;
  }

  /**
   * 菜鸟订单号.
   * @param cainiaoOrderId 菜鸟订单号
   */
  public void setCainiaoOrderId(String cainiaoOrderId) {
    this.cainiaoOrderId = cainiaoOrderId == null ? null : cainiaoOrderId.trim();
  }

  /**
   * 菜鸟订单号.
   * @return 菜鸟订单号
   */
  public String getCainiaoOrderId() {
      return this.cainiaoOrderId;
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
   * 到付金额.
   * @param codAmount 到付金额
   */
  public void setCodAmount(Double codAmount) {
    this.codAmount = codAmount;
  }

  /**
   * 到付金额.
   * @return 到付金额
   */
  public Double getCodAmount() {
      return this.codAmount;
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
   * .
   * @param contacts 
   */
  public void setContacts(List<String> contacts) {
    this.contacts = contacts;
  }

  /**
   * .
   * @return 
   */
  public List<String> getContacts() {
      return this.contacts;
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
   * 发货快递id.
   * @param deliveryExpressId 发货快递id
   */
  public void setDeliveryExpressId(String deliveryExpressId) {
    this.deliveryExpressId = deliveryExpressId == null ? null : deliveryExpressId.trim();
  }

  /**
   * 发货快递id.
   * @return 发货快递id
   */
  public String getDeliveryExpressId() {
      return this.deliveryExpressId;
  }

  /**
   * 发货快递单号.
   * @param deliveryExpressNo 发货快递单号
   */
  public void setDeliveryExpressNo(String deliveryExpressNo) {
    this.deliveryExpressNo = deliveryExpressNo == null ? null : deliveryExpressNo.trim();
  }

  /**
   * 发货快递单号.
   * @return 发货快递单号
   */
  public String getDeliveryExpressNo() {
      return this.deliveryExpressNo;
  }

  /**
   * 优惠金额.
   * @param discountAmount 优惠金额
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   * @return 优惠金额
   */
  public Double getDiscountAmount() {
      return this.discountAmount;
  }

  /**
   * 配货单编号.
   * @param dispatchOrderCode 配货单编号
   */
  public void setDispatchOrderCode(String dispatchOrderCode) {
    this.dispatchOrderCode = dispatchOrderCode == null ? null : dispatchOrderCode.trim();
  }

  /**
   * 配货单编号.
   * @return 配货单编号
   */
  public String getDispatchOrderCode() {
      return this.dispatchOrderCode;
  }

  /**
   * .
   * @param dispatchOrderCodes 
   */
  public void setDispatchOrderCodes(List<String> dispatchOrderCodes) {
    this.dispatchOrderCodes = dispatchOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getDispatchOrderCodes() {
      return this.dispatchOrderCodes;
  }

  /**
   * 配货单id.
   * @param dispatchOrderId 配货单id
   */
  public void setDispatchOrderId(Long dispatchOrderId) {
    this.dispatchOrderId = dispatchOrderId;
  }

  /**
   * 配货单id.
   * @return 配货单id
   */
  public Long getDispatchOrderId() {
      return this.dispatchOrderId;
  }

  /**
   * 分销金额.
   * @param distributionAmount 分销金额
   */
  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  /**
   * 分销金额.
   * @return 分销金额
   */
  public Double getDistributionAmount() {
      return this.distributionAmount;
  }

  /**
   * .
   * @param distributionTradeIds 
   */
  public void setDistributionTradeIds(List<String> distributionTradeIds) {
    this.distributionTradeIds = distributionTradeIds;
  }

  /**
   * .
   * @return 
   */
  public List<String> getDistributionTradeIds() {
      return this.distributionTradeIds;
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
   * 发票信息.
   * @param invoiceInfoJson 发票信息
   */
  public void setInvoiceInfoJson(String invoiceInfoJson) {
    this.invoiceInfoJson = invoiceInfoJson == null ? null : invoiceInfoJson.trim();
  }

  /**
   * 发票信息.
   * @return 发票信息
   */
  public String getInvoiceInfoJson() {
      return this.invoiceInfoJson;
  }

  /**
   * 是否货到付款.
   * @param cod 是否货到付款
   */
  public void setCod(Boolean cod) {
    this.cod = cod;
  }

  /**
   * 是否货到付款.
   * @return 是否货到付款
   */
  public Boolean isCod() {
      return this.cod;
  }

  /**
   * 是否发货完成.
   * @param deliveryFinish 是否发货完成
   */
  public void setDeliveryFinish(Boolean deliveryFinish) {
    this.deliveryFinish = deliveryFinish;
  }

  /**
   * 是否发货完成.
   * @return 是否发货完成
   */
  public Boolean isDeliveryFinish() {
      return this.deliveryFinish;
  }

  /**
   * 是否获取面单.
   * @param getWaybill 是否获取面单
   */
  public void setGetWaybill(Boolean getWaybill) {
    this.getWaybill = getWaybill;
  }

  /**
   * 是否获取面单.
   * @return 是否获取面单
   */
  public Boolean isGetWaybill() {
      return this.getWaybill;
  }

  /**
   * 是否包含换货.
   * @param hasExchange 是否包含换货
   */
  public void setHasExchange(Boolean hasExchange) {
    this.hasExchange = hasExchange;
  }

  /**
   * 是否包含换货.
   * @return 是否包含换货
   */
  public Boolean isHasExchange() {
      return this.hasExchange;
  }

  /**
   * 是否合单.
   * @param merge 是否合单
   */
  public void setMerge(Boolean merge) {
    this.merge = merge;
  }

  /**
   * 是否合单.
   * @return 是否合单
   */
  public Boolean isMerge() {
      return this.merge;
  }

  /**
   * 是否开发票.
   * @param needInvoice 是否开发票
   */
  public void setNeedInvoice(Boolean needInvoice) {
    this.needInvoice = needInvoice;
  }

  /**
   * 是否开发票.
   * @return 是否开发票
   */
  public Boolean isNeedInvoice() {
      return this.needInvoice;
  }

  /**
   * 是否线下发货.
   * @param offlineDelivery 是否线下发货
   */
  public void setOfflineDelivery(Boolean offlineDelivery) {
    this.offlineDelivery = offlineDelivery;
  }

  /**
   * 是否线下发货.
   * @return 是否线下发货
   */
  public Boolean isOfflineDelivery() {
      return this.offlineDelivery;
  }

  /**
   * 是否加急.
   * @param urgent 是否加急
   */
  public void setUrgent(Boolean urgent) {
    this.urgent = urgent;
  }

  /**
   * 是否加急.
   * @return 是否加急
   */
  public Boolean isUrgent() {
      return this.urgent;
  }

  /**
   * 是否wms取消.
   * @param wmsCancel 是否wms取消
   */
  public void setWmsCancel(Boolean wmsCancel) {
    this.wmsCancel = wmsCancel;
  }

  /**
   * 是否wms取消.
   * @return 是否wms取消
   */
  public Boolean isWmsCancel() {
      return this.wmsCancel;
  }

  /**
   * 最后一次发货时间开始.
   * @param lastDeliveryTimeBegin 开始.
   */
  public void setLastDeliveryTimeBegin(LocalDateTime lastDeliveryTimeBegin) {
    this.lastDeliveryTimeBegin = lastDeliveryTimeBegin;
  }

  /**
   * 最后一次发货时间开始.
   * @return 最后一次发货时间开始
   */
  public LocalDateTime getLastDeliveryTimeBegin() {
    return this.lastDeliveryTimeBegin;
  }

  /**
   * 最后一次发货时间结束.
   * @param lastDeliveryTimeEnd 结束
   */
  public void setLastDeliveryTimeEnd(LocalDateTime lastDeliveryTimeEnd) {
    this.lastDeliveryTimeEnd = lastDeliveryTimeEnd;
  }

  /**
   * 最后一次发货时间结束.
   * @return 最后一次发货时间结束
   */
  public LocalDateTime getLastDeliveryTimeEnd() {
    return this.lastDeliveryTimeEnd;
  }

  /**
   * 集结地编码.
   * @param massCode 集结地编码
   */
  public void setMassCode(String massCode) {
    this.massCode = massCode == null ? null : massCode.trim();
  }

  /**
   * 集结地编码.
   * @return 集结地编码
   */
  public String getMassCode() {
      return this.massCode;
  }

  /**
   * 集结地名称.
   * @param massName 集结地名称
   */
  public void setMassName(String massName) {
    this.massName = massName == null ? null : massName.trim();
  }

  /**
   * 集结地名称.
   * @return 集结地名称
   */
  public String getMassName() {
      return this.massName;
  }

  /**
   * 集结地短名称.
   * @param massShortName 集结地短名称
   */
  public void setMassShortName(String massShortName) {
    this.massShortName = massShortName == null ? null : massShortName.trim();
  }

  /**
   * 集结地短名称.
   * @return 集结地短名称
   */
  public String getMassShortName() {
      return this.massShortName;
  }

  /**
   * 会员id.
   * @param memberId 会员id
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 会员id.
   * @return 会员id
   */
  public Long getMemberId() {
      return this.memberId;
  }

  /**
   * 会员名称.
   * @param memberName 会员名称
   */
  public void setMemberName(String memberName) {
    this.memberName = memberName == null ? null : memberName.trim();
  }

  /**
   * 会员名称.
   * @return 会员名称
   */
  public String getMemberName() {
      return this.memberName;
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
   * .
   * @param mobiles 
   */
  public void setMobiles(List<String> mobiles) {
    this.mobiles = mobiles;
  }

  /**
   * .
   * @return 
   */
  public List<String> getMobiles() {
      return this.mobiles;
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
   * 包裹编号.
   * @param packageNo 包裹编号
   */
  public void setPackageNo(String packageNo) {
    this.packageNo = packageNo == null ? null : packageNo.trim();
  }

  /**
   * 包裹编号.
   * @return 包裹编号
   */
  public String getPackageNo() {
      return this.packageNo;
  }

  /**
   * 过账状态.
   * @param postStatus 过账状态
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   * @return 过账状态
   */
  public PostStatus getPostStatus() {
      return this.postStatus;
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
   * 数量.
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   * @return 数量
   */
  public Integer getQuantity() {
      return this.quantity;
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
   * @param salesOrderCodes 
   */
  public void setSalesOrderCodes(List<String> salesOrderCodes) {
    this.salesOrderCodes = salesOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSalesOrderCodes() {
      return this.salesOrderCodes;
  }

  /**
   * 卖家备注.
   * @param sellerMemo 卖家备注
   */
  public void setSellerMemo(String sellerMemo) {
    this.sellerMemo = sellerMemo == null ? null : sellerMemo.trim();
  }

  /**
   * 卖家备注.
   * @return 卖家备注
   */
  public String getSellerMemo() {
      return this.sellerMemo;
  }

  /**
   * 销售金额.
   * @param sellingAmount 销售金额
   */
  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  /**
   * 销售金额.
   * @return 销售金额
   */
  public Double getSellingAmount() {
      return this.sellingAmount;
  }

  /**
   * 结算金额.
   * @param settlementAmount 结算金额
   */
  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  /**
   * 结算金额.
   * @return 结算金额
   */
  public Double getSettlementAmount() {
      return this.settlementAmount;
  }

  /**
   * .
   * @param smartKeys 
   */
  public void setSmartKeys(String smartKeys) {
    this.smartKeys = smartKeys == null ? null : smartKeys.trim();
  }

  /**
   * .
   * @return 
   */
  public String getSmartKeys() {
      return this.smartKeys;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(DispatchOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public DispatchOrderStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<DispatchOrderStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<DispatchOrderStatus> getStatuses() {
      return this.statuses;
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
   * 建议快递id.
   * @param suggestExpressId 建议快递id
   */
  public void setSuggestExpressId(Long suggestExpressId) {
    this.suggestExpressId = suggestExpressId;
  }

  /**
   * 建议快递id.
   * @return 建议快递id
   */
  public Long getSuggestExpressId() {
      return this.suggestExpressId;
  }

  /**
   * 建议快递名称.
   * @param suggestExpressName 建议快递名称
   */
  public void setSuggestExpressName(String suggestExpressName) {
    this.suggestExpressName = suggestExpressName == null ? null : suggestExpressName.trim();
  }

  /**
   * 建议快递名称.
   * @return 建议快递名称
   */
  public String getSuggestExpressName() {
      return this.suggestExpressName;
  }

  /**
   * 建议快递单号.
   * @param suggestExpressNo 建议快递单号
   */
  public void setSuggestExpressNo(String suggestExpressNo) {
    this.suggestExpressNo = suggestExpressNo == null ? null : suggestExpressNo.trim();
  }

  /**
   * 建议快递单号.
   * @return 建议快递单号
   */
  public String getSuggestExpressNo() {
      return this.suggestExpressNo;
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
   * .
   * @param telephones 
   */
  public void setTelephones(List<String> telephones) {
    this.telephones = telephones;
  }

  /**
   * .
   * @return 
   */
  public List<String> getTelephones() {
      return this.telephones;
  }

  /**
   * .
   * @param tradeIds 
   */
  public void setTradeIds(List<String> tradeIds) {
    this.tradeIds = tradeIds;
  }

  /**
   * .
   * @return 
   */
  public List<String> getTradeIds() {
      return this.tradeIds;
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

  /**
   * 面单信息.
   * @param waybillInfoJson 面单信息
   */
  public void setWaybillInfoJson(String waybillInfoJson) {
    this.waybillInfoJson = waybillInfoJson == null ? null : waybillInfoJson.trim();
  }

  /**
   * 面单信息.
   * @return 面单信息
   */
  public String getWaybillInfoJson() {
      return this.waybillInfoJson;
  }

  /**
   * 重量.
   * @param weight 重量
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * 重量.
   * @return 重量
   */
  public Double getWeight() {
      return this.weight;
  }

  /**
   * 仓库状态.
   * @param wmsStatus 仓库状态
   */
  public void setWmsStatus(WmsOrderStatus wmsStatus) {
    this.wmsStatus = wmsStatus;
  }

  /**
   * 仓库状态.
   * @return 仓库状态
   */
  public WmsOrderStatus getWmsStatus() {
      return this.wmsStatus;
  }

  /**
   * 邮编.
   * @param zipcode 邮编
   */
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode == null ? null : zipcode.trim();
  }

  /**
   * 邮编.
   * @return 邮编
   */
  public String getZipcode() {
      return this.zipcode;
  }
}