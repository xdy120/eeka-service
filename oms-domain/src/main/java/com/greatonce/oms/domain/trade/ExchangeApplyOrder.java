package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallExchangeStatus;
import com.greatonce.oms.domain.enums.trade.ExchangeApplyOrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 换货申请单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ExchangeApplyOrder extends BaseDO {
  /**
   * 地址.
   */
  private String address;
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
   * 换货单编码.
   */
  private String exchangeApplyOrderCode;
  /**
   * 换货单id.
   */
  private Long exchangeApplyOrderId;
  /**
   * 退入快递名称.
   */
  private String inExpressName;
  /**
   * 退入快递单号.
   */
  private String inExpressNo;
  /**
   * 退入商品编码.
   */
  private String inProductCode;
  /**
   * 退入商品id.
   */
  private Long inProductId;
  /**
   * 退入商品名称.
   */
  private String inProductName;
  /**
   * 退入商品规格编码.
   */
  private String inSkuCode;
  /**
   * 退入商品规格id.
   */
  private Long inSkuId;
  /**
   * 退入商品规格名称.
   */
  private String inSkuName;
  /**
   * 商城明细id.
   */
  private String mallDetailId;
  /**
   * 商城换货单id.
   */
  private String mallExchangeId;
  /**
   * 商城换货单状态.
   */
  private MallExchangeStatus mallExchangeStatus;
  /**
   * 商城换货单版本.
   */
  private String mallExchangeVersion;
  /**
   * 商城类型.
   */
  private MallType mallType;
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
   * 换出快递名称.
   */
  private String outExpressName;
  /**
   * 换出快递单号.
   */
  private String outExpressNo;
  /**
   * 换出商城规格id.
   */
  private String outMallSkuId;
  /**
   * 换出商品编码.
   */
  private String outProductCode;
  /**
   * 换出商品id.
   */
  private Long outProductId;
  /**
   * 换出商品名称.
   */
  private String outProductName;
  /**
   * 换出商品规格编码.
   */
  private String outSkuCode;
  /**
   * 换出商品规格id.
   */
  private Long outSkuId;
  /**
   * 换出商品规格名称.
   */
  private String outSkuName;
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
   * 换货理由.
   */
  private String reason;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 换货单状态.
   */
  private ExchangeApplyOrderStatus status;
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
   * 交易号.
   */
  private String tradeId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.exchangeApplyOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.exchangeApplyOrderId;
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
   * 换货单编码.
   */
  public void setExchangeApplyOrderCode(String exchangeApplyOrderCode) {
    this.exchangeApplyOrderCode = exchangeApplyOrderCode == null ? null : exchangeApplyOrderCode.trim();
  }

  /**
   * 换货单编码.
   */
  public String getExchangeApplyOrderCode() {
    return this.exchangeApplyOrderCode;
  }

  /**
   * 换货单id.
   */
  public void setExchangeApplyOrderId(Long exchangeApplyOrderId) {
    this.exchangeApplyOrderId = exchangeApplyOrderId;
  }

  /**
   * 换货单id.
   */
  public Long getExchangeApplyOrderId() {
    return this.exchangeApplyOrderId;
  }

  /**
   * 退入快递名称.
   */
  public void setInExpressName(String inExpressName) {
    this.inExpressName = inExpressName == null ? null : inExpressName.trim();
  }

  /**
   * 退入快递名称.
   */
  public String getInExpressName() {
    return this.inExpressName;
  }

  /**
   * 退入快递单号.
   */
  public void setInExpressNo(String inExpressNo) {
    this.inExpressNo = inExpressNo == null ? null : inExpressNo.trim();
  }

  /**
   * 退入快递单号.
   */
  public String getInExpressNo() {
    return this.inExpressNo;
  }


  /**
   * 退入商品编码.
   */
  public void setInProductCode(String inProductCode) {
    this.inProductCode = inProductCode == null ? null : inProductCode.trim();
  }

  /**
   * 退入商品编码.
   */
  public String getInProductCode() {
    return this.inProductCode;
  }


  /**
   * 退入商品id.
   */
  public void setInProductId(Long inProductId) {
    this.inProductId = inProductId;
  }

  /**
   * 退入商品id.
   */
  public Long getInProductId() {
    return this.inProductId;
  }

  /**
   * 退入商品名称.
   */
  public void setInProductName(String inProductName) {
    this.inProductName = inProductName == null ? null : inProductName.trim();
  }

  /**
   * 退入商品名称.
   */
  public String getInProductName() {
    return this.inProductName;
  }

  /**
   * 退入商品规格编码.
   */
  public void setInSkuCode(String inSkuCode) {
    this.inSkuCode = inSkuCode == null ? null : inSkuCode.trim();
  }

  /**
   * 退入商品规格编码.
   */
  public String getInSkuCode() {
    return this.inSkuCode;
  }


  /**
   * 退入商品规格id.
   */
  public void setInSkuId(Long inSkuId) {
    this.inSkuId = inSkuId;
  }

  /**
   * 退入商品规格id.
   */
  public Long getInSkuId() {
    return this.inSkuId;
  }

  /**
   * 退入商品规格名称.
   */
  public void setInSkuName(String inSkuName) {
    this.inSkuName = inSkuName == null ? null : inSkuName.trim();
  }

  /**
   * 退入商品规格名称.
   */
  public String getInSkuName() {
    return this.inSkuName;
  }

  /**
   * 商城明细id.
   */
  public void setMallDetailId(String mallDetailId) {
    this.mallDetailId = mallDetailId == null ? null : mallDetailId.trim();
  }

  /**
   * 商城明细id.
   */
  public String getMallDetailId() {
    return this.mallDetailId;
  }

  /**
   * 商城换货单id.
   */
  public void setMallExchangeId(String mallExchangeId) {
    this.mallExchangeId = mallExchangeId == null ? null : mallExchangeId.trim();
  }

  /**
   * 商城换货单id.
   */
  public String getMallExchangeId() {
    return this.mallExchangeId;
  }

  /**
   * 商城换货单状态.
   */
  public void setMallExchangeStatus(MallExchangeStatus mallExchangeStatus) {
    this.mallExchangeStatus = mallExchangeStatus;
  }

  /**
   * 商城换货单状态.
   */
  public MallExchangeStatus getMallExchangeStatus() {
    return this.mallExchangeStatus;
  }

  /**
   * 商城换货单版本.
   */
  public void setMallExchangeVersion(String mallExchangeVersion) {
    this.mallExchangeVersion = mallExchangeVersion == null ? null : mallExchangeVersion.trim();
  }

  /**
   * 商城换货单版本.
   */
  public String getMallExchangeVersion() {
    return this.mallExchangeVersion;
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
   * 换出快递名称.
   */
  public void setOutExpressName(String outExpressName) {
    this.outExpressName = outExpressName == null ? null : outExpressName.trim();
  }

  /**
   * 换出快递名称.
   */
  public String getOutExpressName() {
    return this.outExpressName;
  }

  /**
   * 换出快递单号.
   */
  public void setOutExpressNo(String outExpressNo) {
    this.outExpressNo = outExpressNo == null ? null : outExpressNo.trim();
  }

  /**
   * 换出快递单号.
   */
  public String getOutExpressNo() {
    return this.outExpressNo;
  }


  /**
   * 换出商城规格id.
   */
  public void setOutMallSkuId(String outMallSkuId) {
    this.outMallSkuId = outMallSkuId == null ? null : outMallSkuId.trim();
  }

  /**
   * 换出商城规格id.
   */
  public String getOutMallSkuId() {
    return this.outMallSkuId;
  }

  /**
   * 换出商品编码.
   */
  public void setOutProductCode(String outProductCode) {
    this.outProductCode = outProductCode == null ? null : outProductCode.trim();
  }

  /**
   * 换出商品编码.
   */
  public String getOutProductCode() {
    return this.outProductCode;
  }


  /**
   * 换出商品id.
   */
  public void setOutProductId(Long outProductId) {
    this.outProductId = outProductId;
  }

  /**
   * 换出商品id.
   */
  public Long getOutProductId() {
    return this.outProductId;
  }

  /**
   * 换出商品名称.
   */
  public void setOutProductName(String outProductName) {
    this.outProductName = outProductName == null ? null : outProductName.trim();
  }

  /**
   * 换出商品名称.
   */
  public String getOutProductName() {
    return this.outProductName;
  }

  /**
   * 换出商品规格编码.
   */
  public void setOutSkuCode(String outSkuCode) {
    this.outSkuCode = outSkuCode == null ? null : outSkuCode.trim();
  }

  /**
   * 换出商品规格编码.
   */
  public String getOutSkuCode() {
    return this.outSkuCode;
  }


  /**
   * 换出商品规格id.
   */
  public void setOutSkuId(Long outSkuId) {
    this.outSkuId = outSkuId;
  }

  /**
   * 换出商品规格id.
   */
  public Long getOutSkuId() {
    return this.outSkuId;
  }

  /**
   * 换出商品规格名称.
   */
  public void setOutSkuName(String outSkuName) {
    this.outSkuName = outSkuName == null ? null : outSkuName.trim();
  }

  /**
   * 换出商品规格名称.
   */
  public String getOutSkuName() {
    return this.outSkuName;
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
   * 换货理由.
   */
  public void setReason(String reason) {
    this.reason = reason == null ? null : reason.trim();
  }

  /**
   * 换货理由.
   */
  public String getReason() {
    return this.reason;
  }

  /**
   * 销售单id.
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   */
  public Long getSalesOrderId() {
    return this.salesOrderId;
  }

  /**
   * 换货单状态.
   */
  public void setStatus(ExchangeApplyOrderStatus status) {
    this.status = status;
  }

  /**
   * 换货单状态.
   */
  public ExchangeApplyOrderStatus getStatus() {
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
   * 交易号.
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   */
  public String getTradeId() {
    return this.tradeId;
  }

}