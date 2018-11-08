package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderType;
import com.greatonce.oms.domain.enums.mall.MallRefundPhase;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 售后申请单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class RefundApplyOrderQuery extends Query {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 申请时间开始.
   */
  private LocalDateTime appliedTimeBegin;
  /**
   * 申请时间结束.
   */
  private LocalDateTime appliedTimeEnd;
  /**
   * 申请金额.
   */
  private Double applyAmount;
  /**
   * 申请数量.
   */
  private Integer applyQuantity;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 快递名称.
   */
  private String expressName;
  /**
   * 快递单号.
   */
  private String expressNo;
  /**
   * .
   */
  private List<String> expressNos;
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
   * 是否整单退.
   */
  private Boolean whole;
  /**
   * 商城明细id.
   */
  private String mallDetailId;
  /**
   * 平台退款单号.
   */
  private String mallRefundId;
  /**
   * .
   */
  private List<String> mallRefundIds;
  /**
   * 商城退款阶段.
   */
  private MallRefundPhase mallRefundPhase;
  /**
   * 商城售后申请状态.
   */
  private MallRefundStatus mallRefundStatus;
  /**
   * 商城退款版本.
   */
  private String mallRefundVersion;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 支付帐号.
   */
  private String payAccount;
  /**
   * 原因.
   */
  private String reason;
  /**
   * 售后申请id.
   */
  private Long refundApplyOrderId;
  /**
   * 售后申请类型.
   */
  private RefundApplyOrderType refundApplyOrderType;
  /**
   * 状态.
   */
  private RefundApplyOrderStatus status;
  /**
   * .
   */
  private List<RefundApplyOrderStatus> statuses;
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
   * 交易号.
   */
  private String tradeId;
  /**
   * .
   */
  private List<String> tradeIds;


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
   * 申请时间开始.
   * @param appliedTimeBegin 开始.
   */
  public void setAppliedTimeBegin(LocalDateTime appliedTimeBegin) {
    this.appliedTimeBegin = appliedTimeBegin;
  }

  /**
   * 申请时间开始.
   * @return 申请时间开始
   */
  public LocalDateTime getAppliedTimeBegin() {
    return this.appliedTimeBegin;
  }

  /**
   * 申请时间结束.
   * @param appliedTimeEnd 结束
   */
  public void setAppliedTimeEnd(LocalDateTime appliedTimeEnd) {
    this.appliedTimeEnd = appliedTimeEnd;
  }

  /**
   * 申请时间结束.
   * @return 申请时间结束
   */
  public LocalDateTime getAppliedTimeEnd() {
    return this.appliedTimeEnd;
  }

  /**
   * 申请金额.
   * @param applyAmount 申请金额
   */
  public void setApplyAmount(Double applyAmount) {
    this.applyAmount = applyAmount;
  }

  /**
   * 申请金额.
   * @return 申请金额
   */
  public Double getApplyAmount() {
      return this.applyAmount;
  }

  /**
   * 申请数量.
   * @param applyQuantity 申请数量
   */
  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  /**
   * 申请数量.
   * @return 申请数量
   */
  public Integer getApplyQuantity() {
      return this.applyQuantity;
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
   * 快递名称.
   * @param expressName 快递名称
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   * @return 快递名称
   */
  public String getExpressName() {
      return this.expressName;
  }

  /**
   * 快递单号.
   * @param expressNo 快递单号
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   * @return 快递单号
   */
  public String getExpressNo() {
      return this.expressNo;
  }

  /**
   * .
   * @param expressNos 
   */
  public void setExpressNos(List<String> expressNos) {
    this.expressNos = expressNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getExpressNos() {
      return this.expressNos;
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
   * 是否整单退.
   * @param whole 是否整单退
   */
  public void setWhole(Boolean whole) {
    this.whole = whole;
  }

  /**
   * 是否整单退.
   * @return 是否整单退
   */
  public Boolean isWhole() {
      return this.whole;
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
   * 平台退款单号.
   * @param mallRefundId 平台退款单号
   */
  public void setMallRefundId(String mallRefundId) {
    this.mallRefundId = mallRefundId == null ? null : mallRefundId.trim();
  }

  /**
   * 平台退款单号.
   * @return 平台退款单号
   */
  public String getMallRefundId() {
      return this.mallRefundId;
  }

  /**
   * .
   * @param mallRefundIds 
   */
  public void setMallRefundIds(List<String> mallRefundIds) {
    this.mallRefundIds = mallRefundIds;
  }

  /**
   * .
   * @return 
   */
  public List<String> getMallRefundIds() {
      return this.mallRefundIds;
  }

  /**
   * 商城退款阶段.
   * @param mallRefundPhase 商城退款阶段
   */
  public void setMallRefundPhase(MallRefundPhase mallRefundPhase) {
    this.mallRefundPhase = mallRefundPhase;
  }

  /**
   * 商城退款阶段.
   * @return 商城退款阶段
   */
  public MallRefundPhase getMallRefundPhase() {
      return this.mallRefundPhase;
  }

  /**
   * 商城售后申请状态.
   * @param mallRefundStatus 商城售后申请状态
   */
  public void setMallRefundStatus(MallRefundStatus mallRefundStatus) {
    this.mallRefundStatus = mallRefundStatus;
  }

  /**
   * 商城售后申请状态.
   * @return 商城售后申请状态
   */
  public MallRefundStatus getMallRefundStatus() {
      return this.mallRefundStatus;
  }

  /**
   * 商城退款版本.
   * @param mallRefundVersion 商城退款版本
   */
  public void setMallRefundVersion(String mallRefundVersion) {
    this.mallRefundVersion = mallRefundVersion == null ? null : mallRefundVersion.trim();
  }

  /**
   * 商城退款版本.
   * @return 商城退款版本
   */
  public String getMallRefundVersion() {
      return this.mallRefundVersion;
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
   * 支付帐号.
   * @param payAccount 支付帐号
   */
  public void setPayAccount(String payAccount) {
    this.payAccount = payAccount == null ? null : payAccount.trim();
  }

  /**
   * 支付帐号.
   * @return 支付帐号
   */
  public String getPayAccount() {
      return this.payAccount;
  }

  /**
   * 原因.
   * @param reason 原因
   */
  public void setReason(String reason) {
    this.reason = reason == null ? null : reason.trim();
  }

  /**
   * 原因.
   * @return 原因
   */
  public String getReason() {
      return this.reason;
  }

  /**
   * 售后申请id.
   * @param refundApplyOrderId 售后申请id
   */
  public void setRefundApplyOrderId(Long refundApplyOrderId) {
    this.refundApplyOrderId = refundApplyOrderId;
  }

  /**
   * 售后申请id.
   * @return 售后申请id
   */
  public Long getRefundApplyOrderId() {
      return this.refundApplyOrderId;
  }

  /**
   * 售后申请类型.
   * @param refundApplyOrderType 售后申请类型
   */
  public void setRefundApplyOrderType(RefundApplyOrderType refundApplyOrderType) {
    this.refundApplyOrderType = refundApplyOrderType;
  }

  /**
   * 售后申请类型.
   * @return 售后申请类型
   */
  public RefundApplyOrderType getRefundApplyOrderType() {
      return this.refundApplyOrderType;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(RefundApplyOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public RefundApplyOrderStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<RefundApplyOrderStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<RefundApplyOrderStatus> getStatuses() {
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