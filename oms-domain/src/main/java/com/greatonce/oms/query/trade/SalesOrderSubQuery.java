package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class SalesOrderSubQuery extends Query {
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
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 平台下单时间开始.
   */
  private LocalDateTime mallCreatedTimeBegin;
  /**
   * 平台下单时间结束.
   */
  private LocalDateTime mallCreatedTimeEnd;
  /**
   * 平台完成时间开始.
   */
  private LocalDateTime mallFinishedTimeBegin;
  /**
   * 平台完成时间结束.
   */
  private LocalDateTime mallFinishedTimeEnd;
  /**
   * 平台修改时间开始.
   */
  private LocalDateTime mallModifiedTimeBegin;
  /**
   * 平台修改时间结束.
   */
  private LocalDateTime mallModifiedTimeEnd;
  /**
   * 平台支付时间开始.
   */
  private LocalDateTime mallPaidTimeBegin;
  /**
   * 平台支付时间结束.
   */
  private LocalDateTime mallPaidTimeEnd;
  /**
   * 平台预付日期开始.
   */
  private LocalDateTime mallPrepayTimeBegin;
  /**
   * 平台预付日期结束.
   */
  private LocalDateTime mallPrepayTimeEnd;
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
   * .
   */
  private Boolean memo;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 包裹号.
   */
  private String packageNo;
  /**
   * 预售发货日期开始.
   */
  private LocalDate presellDeliveryDateBegin;
  /**
   * 预售发货日期结束.
   */
  private LocalDate presellDeliveryDateEnd;
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


  /**
   * 买家邮箱.
   * @param buyerEmail 买家邮箱
   */
  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
  }

  /**
   * 买家邮箱.
   * @return 买家邮箱
   */
  public String getBuyerEmail() {
      return this.buyerEmail;
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
   * 创建类型.
   * @param createType 创建类型
   */
  public void setCreateType(SalesOrderCreateType createType) {
    this.createType = createType;
  }

  /**
   * 创建类型.
   * @return 创建类型
   */
  public SalesOrderCreateType getCreateType() {
      return this.createType;
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
   * 货币编码.
   * @param currencyCode 货币编码
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode == null ? null : currencyCode.trim();
  }

  /**
   * 货币编码.
   * @return 货币编码
   */
  public String getCurrencyCode() {
      return this.currencyCode;
  }

  /**
   * 快递费.
   * @param expressFee 快递费
   */
  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  /**
   * 快递费.
   * @return 快递费
   */
  public Double getExpressFee() {
      return this.expressFee;
  }

  /**
   * 运费险.
   * @param freightRiskFee 运费险
   */
  public void setFreightRiskFee(Double freightRiskFee) {
    this.freightRiskFee = freightRiskFee;
  }

  /**
   * 运费险.
   * @return 运费险
   */
  public Double getFreightRiskFee() {
      return this.freightRiskFee;
  }

  /**
   * 是否黑名单.
   * @param blacklist 是否黑名单
   */
  public void setBlacklist(Boolean blacklist) {
    this.blacklist = blacklist;
  }

  /**
   * 是否黑名单.
   * @return 是否黑名单
   */
  public Boolean isBlacklist() {
      return this.blacklist;
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
   * 是否需要发票.
   * @param hasInvoice 是否需要发票
   */
  public void setHasInvoice(Boolean hasInvoice) {
    this.hasInvoice = hasInvoice;
  }

  /**
   * 是否需要发票.
   * @return 是否需要发票
   */
  public Boolean isHasInvoice() {
      return this.hasInvoice;
  }

  /**
   * 是否新会员.
   * @param newMember 是否新会员
   */
  public void setNewMember(Boolean newMember) {
    this.newMember = newMember;
  }

  /**
   * 是否新会员.
   * @return 是否新会员
   */
  public Boolean isNewMember() {
      return this.newMember;
  }

  /**
   * 是否预付.
   * @param prepay 是否预付
   */
  public void setPrepay(Boolean prepay) {
    this.prepay = prepay;
  }

  /**
   * 是否预付.
   * @return 是否预付
   */
  public Boolean isPrepay() {
      return this.prepay;
  }

  /**
   * 是否第三方发货.
   * @param thirdDelivery 是否第三方发货
   */
  public void setThirdDelivery(Boolean thirdDelivery) {
    this.thirdDelivery = thirdDelivery;
  }

  /**
   * 是否第三方发货.
   * @return 是否第三方发货
   */
  public Boolean isThirdDelivery() {
      return this.thirdDelivery;
  }

  /**
   * 平台下单时间开始.
   * @param mallCreatedTimeBegin 开始.
   */
  public void setMallCreatedTimeBegin(LocalDateTime mallCreatedTimeBegin) {
    this.mallCreatedTimeBegin = mallCreatedTimeBegin;
  }

  /**
   * 平台下单时间开始.
   * @return 平台下单时间开始
   */
  public LocalDateTime getMallCreatedTimeBegin() {
    return this.mallCreatedTimeBegin;
  }

  /**
   * 平台下单时间结束.
   * @param mallCreatedTimeEnd 结束
   */
  public void setMallCreatedTimeEnd(LocalDateTime mallCreatedTimeEnd) {
    this.mallCreatedTimeEnd = mallCreatedTimeEnd;
  }

  /**
   * 平台下单时间结束.
   * @return 平台下单时间结束
   */
  public LocalDateTime getMallCreatedTimeEnd() {
    return this.mallCreatedTimeEnd;
  }

  /**
   * 平台完成时间开始.
   * @param mallFinishedTimeBegin 开始.
   */
  public void setMallFinishedTimeBegin(LocalDateTime mallFinishedTimeBegin) {
    this.mallFinishedTimeBegin = mallFinishedTimeBegin;
  }

  /**
   * 平台完成时间开始.
   * @return 平台完成时间开始
   */
  public LocalDateTime getMallFinishedTimeBegin() {
    return this.mallFinishedTimeBegin;
  }

  /**
   * 平台完成时间结束.
   * @param mallFinishedTimeEnd 结束
   */
  public void setMallFinishedTimeEnd(LocalDateTime mallFinishedTimeEnd) {
    this.mallFinishedTimeEnd = mallFinishedTimeEnd;
  }

  /**
   * 平台完成时间结束.
   * @return 平台完成时间结束
   */
  public LocalDateTime getMallFinishedTimeEnd() {
    return this.mallFinishedTimeEnd;
  }

  /**
   * 平台修改时间开始.
   * @param mallModifiedTimeBegin 开始.
   */
  public void setMallModifiedTimeBegin(LocalDateTime mallModifiedTimeBegin) {
    this.mallModifiedTimeBegin = mallModifiedTimeBegin;
  }

  /**
   * 平台修改时间开始.
   * @return 平台修改时间开始
   */
  public LocalDateTime getMallModifiedTimeBegin() {
    return this.mallModifiedTimeBegin;
  }

  /**
   * 平台修改时间结束.
   * @param mallModifiedTimeEnd 结束
   */
  public void setMallModifiedTimeEnd(LocalDateTime mallModifiedTimeEnd) {
    this.mallModifiedTimeEnd = mallModifiedTimeEnd;
  }

  /**
   * 平台修改时间结束.
   * @return 平台修改时间结束
   */
  public LocalDateTime getMallModifiedTimeEnd() {
    return this.mallModifiedTimeEnd;
  }

  /**
   * 平台支付时间开始.
   * @param mallPaidTimeBegin 开始.
   */
  public void setMallPaidTimeBegin(LocalDateTime mallPaidTimeBegin) {
    this.mallPaidTimeBegin = mallPaidTimeBegin;
  }

  /**
   * 平台支付时间开始.
   * @return 平台支付时间开始
   */
  public LocalDateTime getMallPaidTimeBegin() {
    return this.mallPaidTimeBegin;
  }

  /**
   * 平台支付时间结束.
   * @param mallPaidTimeEnd 结束
   */
  public void setMallPaidTimeEnd(LocalDateTime mallPaidTimeEnd) {
    this.mallPaidTimeEnd = mallPaidTimeEnd;
  }

  /**
   * 平台支付时间结束.
   * @return 平台支付时间结束
   */
  public LocalDateTime getMallPaidTimeEnd() {
    return this.mallPaidTimeEnd;
  }

  /**
   * 平台预付日期开始.
   * @param mallPrepayTimeBegin 开始.
   */
  public void setMallPrepayTimeBegin(LocalDateTime mallPrepayTimeBegin) {
    this.mallPrepayTimeBegin = mallPrepayTimeBegin;
  }

  /**
   * 平台预付日期开始.
   * @return 平台预付日期开始
   */
  public LocalDateTime getMallPrepayTimeBegin() {
    return this.mallPrepayTimeBegin;
  }

  /**
   * 平台预付日期结束.
   * @param mallPrepayTimeEnd 结束
   */
  public void setMallPrepayTimeEnd(LocalDateTime mallPrepayTimeEnd) {
    this.mallPrepayTimeEnd = mallPrepayTimeEnd;
  }

  /**
   * 平台预付日期结束.
   * @return 平台预付日期结束
   */
  public LocalDateTime getMallPrepayTimeEnd() {
    return this.mallPrepayTimeEnd;
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
   * .
   * @param memo 
   */
  public void setMemo(Boolean memo) {
    this.memo = memo;
  }

  /**
   * .
   * @return 
   */
  public Boolean isMemo() {
      return this.memo;
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
   * 包裹号.
   * @param packageNo 包裹号
   */
  public void setPackageNo(String packageNo) {
    this.packageNo = packageNo == null ? null : packageNo.trim();
  }

  /**
   * 包裹号.
   * @return 包裹号
   */
  public String getPackageNo() {
      return this.packageNo;
  }

  /**
   * 预售发货日期开始.
   * @param presellDeliveryDateBegin 开始.
   */
  public void setPresellDeliveryDateBegin(LocalDate presellDeliveryDateBegin) {
    this.presellDeliveryDateBegin = presellDeliveryDateBegin;
  }

  /**
   * 预售发货日期开始.
   * @return 预售发货日期开始
   */
  public LocalDate getPresellDeliveryDateBegin() {
    return this.presellDeliveryDateBegin;
  }

  /**
   * 预售发货日期结束.
   * @param presellDeliveryDateEnd 结束
   */
  public void setPresellDeliveryDateEnd(LocalDate presellDeliveryDateEnd) {
    this.presellDeliveryDateEnd = presellDeliveryDateEnd;
  }

  /**
   * 预售发货日期结束.
   * @return 预售发货日期结束
   */
  public LocalDate getPresellDeliveryDateEnd() {
    return this.presellDeliveryDateEnd;
  }

  /**
   * 预售类型.
   * @param presellType 预售类型
   */
  public void setPresellType(PresellType presellType) {
    this.presellType = presellType;
  }

  /**
   * 预售类型.
   * @return 预售类型
   */
  public PresellType getPresellType() {
      return this.presellType;
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
   * 订单类型.
   * @param salesOrderType 订单类型
   */
  public void setSalesOrderType(SalesOrderType salesOrderType) {
    this.salesOrderType = salesOrderType;
  }

  /**
   * 订单类型.
   * @return 订单类型
   */
  public SalesOrderType getSalesOrderType() {
      return this.salesOrderType;
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
   * 导购号.
   * @param shoppingGuide 导购号
   */
  public void setShoppingGuide(String shoppingGuide) {
    this.shoppingGuide = shoppingGuide == null ? null : shoppingGuide.trim();
  }

  /**
   * 导购号.
   * @return 导购号
   */
  public String getShoppingGuide() {
      return this.shoppingGuide;
  }

  /**
   * 来源类型.
   * @param sourceType 来源类型
   */
  public void setSourceType(SourceType sourceType) {
    this.sourceType = sourceType;
  }

  /**
   * 来源类型.
   * @return 来源类型
   */
  public SourceType getSourceType() {
      return this.sourceType;
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