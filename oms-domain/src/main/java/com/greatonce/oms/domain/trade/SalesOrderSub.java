package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.enums.trade.SourceType;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售订单副表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SalesOrderSub extends BaseDO {
  /**
   * 买家邮箱.
   */
  private String buyerEmail;
  /**
   * 买家备注.
   */
  private String buyerMemo;
  /**
   * 创建类型.
   */
  private SalesOrderCreateType createType;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 货币编码.
   */
  private String currencyCode;
  /**
   * 快递费.
   */
  private Double expressFee;
  /**
   * 运费险.
   */
  private Double freightRiskFee;
  /**
   * 是否黑名单.
   */
  private Boolean blacklist;
  /**
   * 是否货到付款.
   */
  private Boolean cod;
  /**
   * 是否需要发票.
   */
  private Boolean hasInvoice;
  /**
   * 是否新会员.
   */
  private Boolean newMember;
  /**
   * 是否预付.
   */
  private Boolean prepay;
  /**
   * 是否第三方发货.
   */
  private Boolean thirdDelivery;
  /**
   * 平台下单时间.
   */
  private LocalDateTime mallCreatedTime;
  /**
   * 平台完成时间.
   */
  private LocalDateTime mallFinishedTime;
  /**
   * 平台修改时间.
   */
  private LocalDateTime mallModifiedTime;
  /**
   * 平台支付时间.
   */
  private LocalDateTime mallPaidTime;
  /**
   * 平台预付日期.
   */
  private LocalDateTime mallPrepayTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 包裹号.
   */
  private String packageNo;
  /**
   * 预售发货日期.
   */
  private LocalDate presellDeliveryDate;
  /**
   * 预售类型.
   */
  private PresellType presellType;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 订单类型.
   */
  private SalesOrderType salesOrderType;
  /**
   * 卖家备注.
   */
  private String sellerMemo;
  /**
   * 导购号.
   */
  private String shoppingGuide;
  /**
   * 来源类型.
   */
  private SourceType sourceType;
  /**
   * 重量.
   */
  private Double weight;
  /**
   * 邮编.
   */
  private String zipcode;


  @Override
  public void setPrimaryKey(Long pk) {
    this.salesOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.salesOrderId;
  }


  /**
   * 买家邮箱.
   */
  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
  }

  /**
   * 买家邮箱.
   */
  public String getBuyerEmail() {
    return this.buyerEmail;
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
   * 创建类型.
   */
  public void setCreateType(SalesOrderCreateType createType) {
    this.createType = createType;
  }

  /**
   * 创建类型.
   */
  public SalesOrderCreateType getCreateType() {
    return this.createType;
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
   * 货币编码.
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode == null ? null : currencyCode.trim();
  }

  /**
   * 货币编码.
   */
  public String getCurrencyCode() {
    return this.currencyCode;
  }

  /**
   * 快递费.
   */
  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  /**
   * 快递费.
   */
  public Double getExpressFee() {
    return this.expressFee;
  }

  /**
   * 运费险.
   */
  public void setFreightRiskFee(Double freightRiskFee) {
    this.freightRiskFee = freightRiskFee;
  }

  /**
   * 运费险.
   */
  public Double getFreightRiskFee() {
    return this.freightRiskFee;
  }

  /**
   * 是否黑名单.
   */
  public void setBlacklist(Boolean blacklist) {
    this.blacklist = blacklist;
  }

  /**
   * 是否黑名单.
   */
  public Boolean isBlacklist() {
    return this.blacklist;
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
   * 是否需要发票.
   */
  public void setHasInvoice(Boolean hasInvoice) {
    this.hasInvoice = hasInvoice;
  }

  /**
   * 是否需要发票.
   */
  public Boolean isHasInvoice() {
    return this.hasInvoice;
  }

  /**
   * 是否新会员.
   */
  public void setNewMember(Boolean newMember) {
    this.newMember = newMember;
  }

  /**
   * 是否新会员.
   */
  public Boolean isNewMember() {
    return this.newMember;
  }

  /**
   * 是否预付.
   */
  public void setPrepay(Boolean prepay) {
    this.prepay = prepay;
  }

  /**
   * 是否预付.
   */
  public Boolean isPrepay() {
    return this.prepay;
  }

  /**
   * 是否第三方发货.
   */
  public void setThirdDelivery(Boolean thirdDelivery) {
    this.thirdDelivery = thirdDelivery;
  }

  /**
   * 是否第三方发货.
   */
  public Boolean isThirdDelivery() {
    return this.thirdDelivery;
  }

  /**
   * 平台下单时间.
   */
  public void setMallCreatedTime(LocalDateTime mallCreatedTime) {
    this.mallCreatedTime = mallCreatedTime;
  }

  /**
   * 平台下单时间.
   */
  public LocalDateTime getMallCreatedTime() {
    return this.mallCreatedTime;
  }

  /**
   * 平台完成时间.
   */
  public void setMallFinishedTime(LocalDateTime mallFinishedTime) {
    this.mallFinishedTime = mallFinishedTime;
  }

  /**
   * 平台完成时间.
   */
  public LocalDateTime getMallFinishedTime() {
    return this.mallFinishedTime;
  }

  /**
   * 平台修改时间.
   */
  public void setMallModifiedTime(LocalDateTime mallModifiedTime) {
    this.mallModifiedTime = mallModifiedTime;
  }

  /**
   * 平台修改时间.
   */
  public LocalDateTime getMallModifiedTime() {
    return this.mallModifiedTime;
  }

  /**
   * 平台支付时间.
   */
  public void setMallPaidTime(LocalDateTime mallPaidTime) {
    this.mallPaidTime = mallPaidTime;
  }

  /**
   * 平台支付时间.
   */
  public LocalDateTime getMallPaidTime() {
    return this.mallPaidTime;
  }

  /**
   * 平台预付日期.
   */
  public void setMallPrepayTime(LocalDateTime mallPrepayTime) {
    this.mallPrepayTime = mallPrepayTime;
  }

  /**
   * 平台预付日期.
   */
  public LocalDateTime getMallPrepayTime() {
    return this.mallPrepayTime;
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
   * 包裹号.
   */
  public void setPackageNo(String packageNo) {
    this.packageNo = packageNo == null ? null : packageNo.trim();
  }

  /**
   * 包裹号.
   */
  public String getPackageNo() {
    return this.packageNo;
  }

  /**
   * 预售发货日期.
   */
  public void setPresellDeliveryDate(LocalDate presellDeliveryDate) {
    this.presellDeliveryDate = presellDeliveryDate;
  }

  /**
   * 预售发货日期.
   */
  public LocalDate getPresellDeliveryDate() {
    return this.presellDeliveryDate;
  }

  /**
   * 预售类型.
   */
  public void setPresellType(PresellType presellType) {
    this.presellType = presellType;
  }

  /**
   * 预售类型.
   */
  public PresellType getPresellType() {
    return this.presellType;
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
   * 订单类型.
   */
  public void setSalesOrderType(SalesOrderType salesOrderType) {
    this.salesOrderType = salesOrderType;
  }

  /**
   * 订单类型.
   */
  public SalesOrderType getSalesOrderType() {
    return this.salesOrderType;
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
   * 导购号.
   */
  public void setShoppingGuide(String shoppingGuide) {
    this.shoppingGuide = shoppingGuide == null ? null : shoppingGuide.trim();
  }

  /**
   * 导购号.
   */
  public String getShoppingGuide() {
    return this.shoppingGuide;
  }

  /**
   * 来源类型.
   */
  public void setSourceType(SourceType sourceType) {
    this.sourceType = sourceType;
  }

  /**
   * 来源类型.
   */
  public SourceType getSourceType() {
    return this.sourceType;
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
}