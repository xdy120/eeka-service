package com.greatonce.oms.bo.mall;

import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.mall.MallStepOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.enums.trade.SourceType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ODS订单主表
 * @author ginta
 */
public class MallSalesOrderInfo {

  /**
   * 交易号
   */
  private String tradeId;
  /**
   * 买家信息
   */
  private String buyerMessage;
  /**
   * 买家备注
   */
  private String buyerMemo;
  /**
   * 卖家备注
   */
  private String sellerMemo;
  /**
   * 订单状态
   */
  private MallSalesOrderStatus status;
  /**
   * 创建时间
   */
  private LocalDateTime createdTime;
  /**
   * 修改日期
   */
  private LocalDateTime modifiedTime;
  /**
   * 支付时间
   */
  private LocalDateTime paidTime;
  /**
   * 完成时间
   */
  private LocalDateTime finishedTime;
  /**
   * 卖家昵称
   */
  private String sellerNick;
  /**
   * 买家昵称
   */
  private String buyerNick;
  /**
   * 买家支付宝单号
   */
  private String buyerAlipayNo;
  /**
   * 买家邮箱
   */
  private String buyerEmail;
  /**
   * 支付宝单号
   */
  private String sellerAlipayNo;
  /**
   * 实际金额（用户实际支付金额）
   */
  private Double actualAmount;
  /**
   * 结算金额（商城与商家的结算金额）
   */
  private Double settlementAmount;
  /**
   * 销售金额（商品吊牌价金额）
   */
  private Double sellingAmount;
  /**
   * 分销金额（商家与分销商结算的金额）
   */
  private Double distributionAmount;
  /**
   * 邮费
   */
  private Double expressFee;
  /**
   * 货到付款金额（用户需要支付的货到付款金额）
   */
  private Double codAmount;
  /**
   * 运费险
   */
  private Double freightRiskFee;
  /***
   * 是否第三方发货
   */
  private boolean thirdDelivery;
  /**
   * 是否换货订单
   */
  private boolean exchangeOrder;
  /**
   * 换货订单,原订单交易号
   */
  private String sourceTradeId;
  /**
   * 是否到付
   */
  private boolean cod;
  /**
   * 需要发票
   */
  private boolean hasInvoice;
  /**
   * 是否国外订单
   */
  private boolean foreign;
  /**
   * 收货人姓名
   */
  private String contact;
  /**
   * 国家
   */
  private String country;
  /**
   * 省
   */
  private String province;
  /**
   * 市
   */
  private String city;
  /**
   * 区
   */
  private String district;
  /**
   * 地址
   */
  private String address;
  /**
   * 邮编
   */
  private String zipCode;
  /**
   * 收货人手机
   */
  private String mobile;
  /**
   * 收货人电话
   */
  private String telephone;
  /**
   * 订单来源
   */
  private SourceType sourceType;
  /**
   * 订单类型
   */
  private SalesOrderType orderType;
  /**
   * 货币编码
   */
  private String currencyCode;
  /**
   * 国家代码
   */
  private String countryCode;
  /**
   * 分销订单号
   */
  private String distributionTradeId;
  /**
   * 快递编码
   */
  private String expressCode;
  /**
   * 快递名称
   */
  private String expressName;
  /**
   * 快递单号
   */
  private String expressNo;
  /***
   * 创建人
   */
  private String creator;
  /***
   * 预付款订单状态
   */
  private MallStepOrderStatus stepTradeStatus;
  /**
   * 订单导购号
   */
  private String shoppingGuide;
  /**
   * 会员卡号
   */
  private String vipCardNo;
  /**
   * 仓库编码
   */
  private String warehouseCode;
  /**
   * 会员名称
   */
  private String memberName;
  /**
   * 订单创建类型
   */
  private SalesOrderCreateType createType;
  /**
   * 订单发票
   */
  private MallSalesOrderInvoiceInfo invoice;
  /**
   * 订单明细
   */
  private List<MallSalesOrderDetailInfo> details;
  /**
   * 订单支付
   */
  private List<MallSalesOrderPaymentInfo> payments;
  /**
   * 订单优惠
   */
  private List<MallSalesOrderDiscountInfo> discounts;

  public MallSalesOrderInfo() {
    country = "中国";
    codAmount = 0d;
    cod = false;
    freightRiskFee = 0d;
    expressFee = 0d;
    orderType = SalesOrderType.SALES;
    thirdDelivery = false;
    sourceType = SourceType.PC;
    exchangeOrder = false;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getBuyerMessage() {
    return buyerMessage;
  }

  public void setBuyerMessage(String buyerMessage) {
    this.buyerMessage = buyerMessage;
  }

  public String getBuyerMemo() {
    return buyerMemo;
  }

  public void setBuyerMemo(String buyerMemo) {
    this.buyerMemo = buyerMemo;
  }

  public String getSellerMemo() {
    return sellerMemo;
  }

  public void setSellerMemo(String sellerMemo) {
    this.sellerMemo = sellerMemo;
  }

  public MallSalesOrderStatus getStatus() {
    return status;
  }

  public void setStatus(MallSalesOrderStatus status) {
    this.status = status;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public LocalDateTime getPaidTime() {
    return paidTime;
  }

  public void setPaidTime(LocalDateTime paidTime) {
    this.paidTime = paidTime;
  }

  public LocalDateTime getFinishedTime() {
    return finishedTime;
  }

  public void setFinishedTime(LocalDateTime finishedTime) {
    this.finishedTime = finishedTime;
  }

  public String getSellerNick() {
    return sellerNick;
  }

  public void setSellerNick(String sellerNick) {
    this.sellerNick = sellerNick;
  }

  public String getBuyerNick() {
    return buyerNick;
  }

  public void setBuyerNick(String buyerNick) {
    this.buyerNick = buyerNick;
  }

  public String getBuyerAlipayNo() {
    return buyerAlipayNo;
  }

  public void setBuyerAlipayNo(String buyerAlipayNo) {
    this.buyerAlipayNo = buyerAlipayNo;
  }

  public String getBuyerEmail() {
    return buyerEmail;
  }

  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail;
  }

  public String getSellerAlipayNo() {
    return sellerAlipayNo;
  }

  public void setSellerAlipayNo(String sellerAlipayNo) {
    this.sellerAlipayNo = sellerAlipayNo;
  }

  public Double getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  public Double getSettlementAmount() {
    return settlementAmount;
  }

  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  public Double getSellingAmount() {
    return sellingAmount;
  }

  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  public Double getDistributionAmount() {
    return distributionAmount;
  }

  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  public Double getExpressFee() {
    return expressFee;
  }

  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  public Double getCodAmount() {
    return codAmount;
  }

  public void setCodAmount(Double codAmount) {
    this.codAmount = codAmount;
  }

  public Double getFreightRiskFee() {
    return freightRiskFee;
  }

  public void setFreightRiskFee(Double freightRiskFee) {
    this.freightRiskFee = freightRiskFee;
  }

  public boolean isThirdDelivery() {
    return thirdDelivery;
  }

  public void setThirdDelivery(boolean thirdDelivery) {
    this.thirdDelivery = thirdDelivery;
  }

  public boolean isExchangeOrder() {
    return exchangeOrder;
  }

  public void setExchangeOrder(boolean exchangeOrder) {
    this.exchangeOrder = exchangeOrder;
  }

  public String getSourceTradeId() {
    return sourceTradeId;
  }

  public void setSourceTradeId(String sourceTradeId) {
    this.sourceTradeId = sourceTradeId;
  }

  public boolean isCod() {
    return cod;
  }

  public void setCod(boolean cod) {
    this.cod = cod;
  }

  public boolean isHasInvoice() {
    return hasInvoice;
  }

  public void setHasInvoice(boolean hasInvoice) {
    this.hasInvoice = hasInvoice;
  }

  public boolean isForeign() {
    return foreign;
  }

  public void setForeign(boolean foreign) {
    this.foreign = foreign;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public SourceType getSourceType() {
    return sourceType;
  }

  public void setSourceType(SourceType sourceType) {
    this.sourceType = sourceType;
  }

  public SalesOrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(SalesOrderType orderType) {
    this.orderType = orderType;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getDistributionTradeId() {
    return distributionTradeId;
  }

  public void setDistributionTradeId(String distributionTradeId) {
    this.distributionTradeId = distributionTradeId;
  }

  public String getExpressCode() {
    return expressCode;
  }

  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public MallStepOrderStatus getStepTradeStatus() {
    return stepTradeStatus;
  }

  public void setStepTradeStatus(MallStepOrderStatus stepTradeStatus) {
    this.stepTradeStatus = stepTradeStatus;
  }

  public String getShoppingGuide() {
    return shoppingGuide;
  }

  public void setShoppingGuide(String shoppingGuide) {
    this.shoppingGuide = shoppingGuide;
  }

  public String getVipCardNo() {
    return vipCardNo;
  }

  public void setVipCardNo(String vipCardNo) {
    this.vipCardNo = vipCardNo;
  }

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public SalesOrderCreateType getCreateType() {
    return createType;
  }

  public void setCreateType(SalesOrderCreateType createType) {
    this.createType = createType;
  }

  public MallSalesOrderInvoiceInfo getInvoice() {
    return invoice;
  }

  public void setInvoice(MallSalesOrderInvoiceInfo invoice) {
    this.invoice = invoice;
  }

  public List<MallSalesOrderDetailInfo> getDetails() {
    return details;
  }

  public void setDetails(List<MallSalesOrderDetailInfo> details) {
    this.details = details;
  }

  public List<MallSalesOrderPaymentInfo> getPayments() {
    return payments;
  }

  public void setPayments(List<MallSalesOrderPaymentInfo> payments) {
    this.payments = payments;
  }

  public List<MallSalesOrderDiscountInfo> getDiscounts() {
    return discounts;
  }

  public void setDiscounts(List<MallSalesOrderDiscountInfo> discounts) {
    this.discounts = discounts;
  }
}
