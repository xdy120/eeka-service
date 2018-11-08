package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class ExchangeApplyOrderQuery extends Query {
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
   * .
   */
  private List<String> inExpressNos;
  /**
   * 退入商品编码.
   */
  private String inProductCode;
  /**
   * .
   */
  private List<String> inProductCodes;
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
   * .
   */
  private List<String> inSkuCodes;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 换出快递名称.
   */
  private String outExpressName;
  /**
   * 换出快递单号.
   */
  private String outExpressNo;
  /**
   * .
   */
  private List<String> outExpressNos;
  /**
   * 换出商城规格id.
   */
  private String outMallSkuId;
  /**
   * 换出商品编码.
   */
  private String outProductCode;
  /**
   * .
   */
  private List<String> outProductCodes;
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
   * .
   */
  private List<String> outSkuCodes;
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
   * 交易号.
   */
  private String tradeId;
  /**
   * .
   */
  private List<String> tradeIds;


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
   * 换货单编码.
   * @param exchangeApplyOrderCode 换货单编码
   */
  public void setExchangeApplyOrderCode(String exchangeApplyOrderCode) {
    this.exchangeApplyOrderCode = exchangeApplyOrderCode == null ? null : exchangeApplyOrderCode.trim();
  }

  /**
   * 换货单编码.
   * @return 换货单编码
   */
  public String getExchangeApplyOrderCode() {
      return this.exchangeApplyOrderCode;
  }

  /**
   * 换货单id.
   * @param exchangeApplyOrderId 换货单id
   */
  public void setExchangeApplyOrderId(Long exchangeApplyOrderId) {
    this.exchangeApplyOrderId = exchangeApplyOrderId;
  }

  /**
   * 换货单id.
   * @return 换货单id
   */
  public Long getExchangeApplyOrderId() {
      return this.exchangeApplyOrderId;
  }

  /**
   * 退入快递名称.
   * @param inExpressName 退入快递名称
   */
  public void setInExpressName(String inExpressName) {
    this.inExpressName = inExpressName == null ? null : inExpressName.trim();
  }

  /**
   * 退入快递名称.
   * @return 退入快递名称
   */
  public String getInExpressName() {
      return this.inExpressName;
  }

  /**
   * 退入快递单号.
   * @param inExpressNo 退入快递单号
   */
  public void setInExpressNo(String inExpressNo) {
    this.inExpressNo = inExpressNo == null ? null : inExpressNo.trim();
  }

  /**
   * 退入快递单号.
   * @return 退入快递单号
   */
  public String getInExpressNo() {
      return this.inExpressNo;
  }

  /**
   * .
   * @param inExpressNos 
   */
  public void setInExpressNos(List<String> inExpressNos) {
    this.inExpressNos = inExpressNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getInExpressNos() {
      return this.inExpressNos;
  }

  /**
   * 退入商品编码.
   * @param inProductCode 退入商品编码
   */
  public void setInProductCode(String inProductCode) {
    this.inProductCode = inProductCode == null ? null : inProductCode.trim();
  }

  /**
   * 退入商品编码.
   * @return 退入商品编码
   */
  public String getInProductCode() {
      return this.inProductCode;
  }

  /**
   * .
   * @param inProductCodes 
   */
  public void setInProductCodes(List<String> inProductCodes) {
    this.inProductCodes = inProductCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getInProductCodes() {
      return this.inProductCodes;
  }

  /**
   * 退入商品id.
   * @param inProductId 退入商品id
   */
  public void setInProductId(Long inProductId) {
    this.inProductId = inProductId;
  }

  /**
   * 退入商品id.
   * @return 退入商品id
   */
  public Long getInProductId() {
      return this.inProductId;
  }

  /**
   * 退入商品名称.
   * @param inProductName 退入商品名称
   */
  public void setInProductName(String inProductName) {
    this.inProductName = inProductName == null ? null : inProductName.trim();
  }

  /**
   * 退入商品名称.
   * @return 退入商品名称
   */
  public String getInProductName() {
      return this.inProductName;
  }

  /**
   * 退入商品规格编码.
   * @param inSkuCode 退入商品规格编码
   */
  public void setInSkuCode(String inSkuCode) {
    this.inSkuCode = inSkuCode == null ? null : inSkuCode.trim();
  }

  /**
   * 退入商品规格编码.
   * @return 退入商品规格编码
   */
  public String getInSkuCode() {
      return this.inSkuCode;
  }

  /**
   * .
   * @param inSkuCodes 
   */
  public void setInSkuCodes(List<String> inSkuCodes) {
    this.inSkuCodes = inSkuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getInSkuCodes() {
      return this.inSkuCodes;
  }

  /**
   * 退入商品规格id.
   * @param inSkuId 退入商品规格id
   */
  public void setInSkuId(Long inSkuId) {
    this.inSkuId = inSkuId;
  }

  /**
   * 退入商品规格id.
   * @return 退入商品规格id
   */
  public Long getInSkuId() {
      return this.inSkuId;
  }

  /**
   * 退入商品规格名称.
   * @param inSkuName 退入商品规格名称
   */
  public void setInSkuName(String inSkuName) {
    this.inSkuName = inSkuName == null ? null : inSkuName.trim();
  }

  /**
   * 退入商品规格名称.
   * @return 退入商品规格名称
   */
  public String getInSkuName() {
      return this.inSkuName;
  }

  /**
   * 商城明细id.
   * @param mallDetailId 商城明细id
   */
  public void setMallDetailId(String mallDetailId) {
    this.mallDetailId = mallDetailId == null ? null : mallDetailId.trim();
  }

  /**
   * 商城明细id.
   * @return 商城明细id
   */
  public String getMallDetailId() {
      return this.mallDetailId;
  }

  /**
   * 商城换货单id.
   * @param mallExchangeId 商城换货单id
   */
  public void setMallExchangeId(String mallExchangeId) {
    this.mallExchangeId = mallExchangeId == null ? null : mallExchangeId.trim();
  }

  /**
   * 商城换货单id.
   * @return 商城换货单id
   */
  public String getMallExchangeId() {
      return this.mallExchangeId;
  }

  /**
   * 商城换货单状态.
   * @param mallExchangeStatus 商城换货单状态
   */
  public void setMallExchangeStatus(MallExchangeStatus mallExchangeStatus) {
    this.mallExchangeStatus = mallExchangeStatus;
  }

  /**
   * 商城换货单状态.
   * @return 商城换货单状态
   */
  public MallExchangeStatus getMallExchangeStatus() {
      return this.mallExchangeStatus;
  }

  /**
   * 商城换货单版本.
   * @param mallExchangeVersion 商城换货单版本
   */
  public void setMallExchangeVersion(String mallExchangeVersion) {
    this.mallExchangeVersion = mallExchangeVersion == null ? null : mallExchangeVersion.trim();
  }

  /**
   * 商城换货单版本.
   * @return 商城换货单版本
   */
  public String getMallExchangeVersion() {
      return this.mallExchangeVersion;
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
   * 换出快递名称.
   * @param outExpressName 换出快递名称
   */
  public void setOutExpressName(String outExpressName) {
    this.outExpressName = outExpressName == null ? null : outExpressName.trim();
  }

  /**
   * 换出快递名称.
   * @return 换出快递名称
   */
  public String getOutExpressName() {
      return this.outExpressName;
  }

  /**
   * 换出快递单号.
   * @param outExpressNo 换出快递单号
   */
  public void setOutExpressNo(String outExpressNo) {
    this.outExpressNo = outExpressNo == null ? null : outExpressNo.trim();
  }

  /**
   * 换出快递单号.
   * @return 换出快递单号
   */
  public String getOutExpressNo() {
      return this.outExpressNo;
  }

  /**
   * .
   * @param outExpressNos 
   */
  public void setOutExpressNos(List<String> outExpressNos) {
    this.outExpressNos = outExpressNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOutExpressNos() {
      return this.outExpressNos;
  }

  /**
   * 换出商城规格id.
   * @param outMallSkuId 换出商城规格id
   */
  public void setOutMallSkuId(String outMallSkuId) {
    this.outMallSkuId = outMallSkuId == null ? null : outMallSkuId.trim();
  }

  /**
   * 换出商城规格id.
   * @return 换出商城规格id
   */
  public String getOutMallSkuId() {
      return this.outMallSkuId;
  }

  /**
   * 换出商品编码.
   * @param outProductCode 换出商品编码
   */
  public void setOutProductCode(String outProductCode) {
    this.outProductCode = outProductCode == null ? null : outProductCode.trim();
  }

  /**
   * 换出商品编码.
   * @return 换出商品编码
   */
  public String getOutProductCode() {
      return this.outProductCode;
  }

  /**
   * .
   * @param outProductCodes 
   */
  public void setOutProductCodes(List<String> outProductCodes) {
    this.outProductCodes = outProductCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOutProductCodes() {
      return this.outProductCodes;
  }

  /**
   * 换出商品id.
   * @param outProductId 换出商品id
   */
  public void setOutProductId(Long outProductId) {
    this.outProductId = outProductId;
  }

  /**
   * 换出商品id.
   * @return 换出商品id
   */
  public Long getOutProductId() {
      return this.outProductId;
  }

  /**
   * 换出商品名称.
   * @param outProductName 换出商品名称
   */
  public void setOutProductName(String outProductName) {
    this.outProductName = outProductName == null ? null : outProductName.trim();
  }

  /**
   * 换出商品名称.
   * @return 换出商品名称
   */
  public String getOutProductName() {
      return this.outProductName;
  }

  /**
   * 换出商品规格编码.
   * @param outSkuCode 换出商品规格编码
   */
  public void setOutSkuCode(String outSkuCode) {
    this.outSkuCode = outSkuCode == null ? null : outSkuCode.trim();
  }

  /**
   * 换出商品规格编码.
   * @return 换出商品规格编码
   */
  public String getOutSkuCode() {
      return this.outSkuCode;
  }

  /**
   * .
   * @param outSkuCodes 
   */
  public void setOutSkuCodes(List<String> outSkuCodes) {
    this.outSkuCodes = outSkuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOutSkuCodes() {
      return this.outSkuCodes;
  }

  /**
   * 换出商品规格id.
   * @param outSkuId 换出商品规格id
   */
  public void setOutSkuId(Long outSkuId) {
    this.outSkuId = outSkuId;
  }

  /**
   * 换出商品规格id.
   * @return 换出商品规格id
   */
  public Long getOutSkuId() {
      return this.outSkuId;
  }

  /**
   * 换出商品规格名称.
   * @param outSkuName 换出商品规格名称
   */
  public void setOutSkuName(String outSkuName) {
    this.outSkuName = outSkuName == null ? null : outSkuName.trim();
  }

  /**
   * 换出商品规格名称.
   * @return 换出商品规格名称
   */
  public String getOutSkuName() {
      return this.outSkuName;
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
   * 换货理由.
   * @param reason 换货理由
   */
  public void setReason(String reason) {
    this.reason = reason == null ? null : reason.trim();
  }

  /**
   * 换货理由.
   * @return 换货理由
   */
  public String getReason() {
      return this.reason;
  }

  /**
   * 销售单id.
   * @param salesOrderId 销售单id
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   * @return 销售单id
   */
  public Long getSalesOrderId() {
      return this.salesOrderId;
  }

  /**
   * 换货单状态.
   * @param status 换货单状态
   */
  public void setStatus(ExchangeApplyOrderStatus status) {
    this.status = status;
  }

  /**
   * 换货单状态.
   * @return 换货单状态
   */
  public ExchangeApplyOrderStatus getStatus() {
      return this.status;
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

  /**
   * 交易号.
   * @param tradeId 交易号
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   * @return 交易号
   */
  public String getTradeId() {
      return this.tradeId;
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
}