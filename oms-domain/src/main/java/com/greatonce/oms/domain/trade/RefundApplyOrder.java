package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
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
public class RefundApplyOrder extends BaseDO {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 申请时间.
   */
  private LocalDateTime appliedTime;
  /**
   * 申请金额.
   */
  private Double applyAmount;
  /**
   * 申请数量.
   */
  private Integer applyQuantity;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 快递名称.
   */
  private String expressName;
  /**
   * 快递单号.
   */
  private String expressNo;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 交易号.
   */
  private String tradeId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.refundApplyOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.refundApplyOrderId;
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
   * 申请时间.
   */
  public void setAppliedTime(LocalDateTime appliedTime) {
    this.appliedTime = appliedTime;
  }

  /**
   * 申请时间.
   */
  public LocalDateTime getAppliedTime() {
    return this.appliedTime;
  }

  /**
   * 申请金额.
   */
  public void setApplyAmount(Double applyAmount) {
    this.applyAmount = applyAmount;
  }

  /**
   * 申请金额.
   */
  public Double getApplyAmount() {
    return this.applyAmount;
  }

  /**
   * 申请数量.
   */
  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  /**
   * 申请数量.
   */
  public Integer getApplyQuantity() {
    return this.applyQuantity;
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
   * 快递名称.
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   */
  public String getExpressName() {
    return this.expressName;
  }

  /**
   * 快递单号.
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   */
  public String getExpressNo() {
    return this.expressNo;
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
   * 是否整单退.
   */
  public void setWhole(Boolean whole) {
    this.whole = whole;
  }

  /**
   * 是否整单退.
   */
  public Boolean isWhole() {
    return this.whole;
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
   * 平台退款单号.
   */
  public void setMallRefundId(String mallRefundId) {
    this.mallRefundId = mallRefundId == null ? null : mallRefundId.trim();
  }

  /**
   * 平台退款单号.
   */
  public String getMallRefundId() {
    return this.mallRefundId;
  }


  /**
   * 商城退款阶段.
   */
  public void setMallRefundPhase(MallRefundPhase mallRefundPhase) {
    this.mallRefundPhase = mallRefundPhase;
  }

  /**
   * 商城退款阶段.
   */
  public MallRefundPhase getMallRefundPhase() {
    return this.mallRefundPhase;
  }

  /**
   * 商城售后申请状态.
   */
  public void setMallRefundStatus(MallRefundStatus mallRefundStatus) {
    this.mallRefundStatus = mallRefundStatus;
  }

  /**
   * 商城售后申请状态.
   */
  public MallRefundStatus getMallRefundStatus() {
    return this.mallRefundStatus;
  }

  /**
   * 商城退款版本.
   */
  public void setMallRefundVersion(String mallRefundVersion) {
    this.mallRefundVersion = mallRefundVersion == null ? null : mallRefundVersion.trim();
  }

  /**
   * 商城退款版本.
   */
  public String getMallRefundVersion() {
    return this.mallRefundVersion;
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
   * 支付帐号.
   */
  public void setPayAccount(String payAccount) {
    this.payAccount = payAccount == null ? null : payAccount.trim();
  }

  /**
   * 支付帐号.
   */
  public String getPayAccount() {
    return this.payAccount;
  }

  /**
   * 原因.
   */
  public void setReason(String reason) {
    this.reason = reason == null ? null : reason.trim();
  }

  /**
   * 原因.
   */
  public String getReason() {
    return this.reason;
  }

  /**
   * 售后申请id.
   */
  public void setRefundApplyOrderId(Long refundApplyOrderId) {
    this.refundApplyOrderId = refundApplyOrderId;
  }

  /**
   * 售后申请id.
   */
  public Long getRefundApplyOrderId() {
    return this.refundApplyOrderId;
  }

  /**
   * 售后申请类型.
   */
  public void setRefundApplyOrderType(RefundApplyOrderType refundApplyOrderType) {
    this.refundApplyOrderType = refundApplyOrderType;
  }

  /**
   * 售后申请类型.
   */
  public RefundApplyOrderType getRefundApplyOrderType() {
    return this.refundApplyOrderType;
  }

  /**
   * 状态.
   */
  public void setStatus(RefundApplyOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public RefundApplyOrderStatus getStatus() {
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