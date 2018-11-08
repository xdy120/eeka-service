package com.greatonce.oms.query.finance;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.finance.AlipayRecordType;
import java.time.LocalDateTime;

/**
 * 支付宝账单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class AlipayRecordQuery extends Query {
  /**
   * 支付宝创建时间开始.
   */
  private LocalDateTime alipayCreatedTimeBegin;
  /**
   * 支付宝创建时间结束.
   */
  private LocalDateTime alipayCreatedTimeEnd;
  /**
   * 支付宝订单号.
   */
  private String alipayOrderNo;
  /**
   * 支付宝账单id.
   */
  private Long alipayRecordId;
  /**
   * 支付宝账户余额.
   */
  private Double balance;
  /**
   * 子业务类型描述.
   */
  private String businessTypeDesc;
  /**
   * 子业务类型.
   */
  private String businessType;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 收入金额.
   */
  private Double inAmount;
  /**
   * 备注.
   */
  private String memo;
  /**
   * 商户订单号.
   */
  private String merchantOrderNo;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 对方的支付宝id.
   */
  private String optUserId;
  /**
   * 支出金额.
   */
  private Double outAmount;
  /**
   * 自己的支付宝id.
   */
  private String selfUserId;
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
  /**
   * 财务类型描述.
   */
  private String typeDesc;
  /**
   * 账务类型.
   */
  private AlipayRecordType type;


  /**
   * 支付宝创建时间开始.
   * @param alipayCreatedTimeBegin 开始.
   */
  public void setAlipayCreatedTimeBegin(LocalDateTime alipayCreatedTimeBegin) {
    this.alipayCreatedTimeBegin = alipayCreatedTimeBegin;
  }

  /**
   * 支付宝创建时间开始.
   * @return 支付宝创建时间开始
   */
  public LocalDateTime getAlipayCreatedTimeBegin() {
    return this.alipayCreatedTimeBegin;
  }

  /**
   * 支付宝创建时间结束.
   * @param alipayCreatedTimeEnd 结束
   */
  public void setAlipayCreatedTimeEnd(LocalDateTime alipayCreatedTimeEnd) {
    this.alipayCreatedTimeEnd = alipayCreatedTimeEnd;
  }

  /**
   * 支付宝创建时间结束.
   * @return 支付宝创建时间结束
   */
  public LocalDateTime getAlipayCreatedTimeEnd() {
    return this.alipayCreatedTimeEnd;
  }

  /**
   * 支付宝订单号.
   * @param alipayOrderNo 支付宝订单号
   */
  public void setAlipayOrderNo(String alipayOrderNo) {
    this.alipayOrderNo = alipayOrderNo == null ? null : alipayOrderNo.trim();
  }

  /**
   * 支付宝订单号.
   * @return 支付宝订单号
   */
  public String getAlipayOrderNo() {
      return this.alipayOrderNo;
  }

  /**
   * 支付宝账单id.
   * @param alipayRecordId 支付宝账单id
   */
  public void setAlipayRecordId(Long alipayRecordId) {
    this.alipayRecordId = alipayRecordId;
  }

  /**
   * 支付宝账单id.
   * @return 支付宝账单id
   */
  public Long getAlipayRecordId() {
      return this.alipayRecordId;
  }

  /**
   * 支付宝账户余额.
   * @param balance 支付宝账户余额
   */
  public void setBalance(Double balance) {
    this.balance = balance;
  }

  /**
   * 支付宝账户余额.
   * @return 支付宝账户余额
   */
  public Double getBalance() {
      return this.balance;
  }

  /**
   * 子业务类型描述.
   * @param businessTypeDesc 子业务类型描述
   */
  public void setBusinessTypeDesc(String businessTypeDesc) {
    this.businessTypeDesc = businessTypeDesc == null ? null : businessTypeDesc.trim();
  }

  /**
   * 子业务类型描述.
   * @return 子业务类型描述
   */
  public String getBusinessTypeDesc() {
      return this.businessTypeDesc;
  }

  /**
   * 子业务类型.
   * @param businessType 子业务类型
   */
  public void setBusinessType(String businessType) {
    this.businessType = businessType == null ? null : businessType.trim();
  }

  /**
   * 子业务类型.
   * @return 子业务类型
   */
  public String getBusinessType() {
      return this.businessType;
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
   * 收入金额.
   * @param inAmount 收入金额
   */
  public void setInAmount(Double inAmount) {
    this.inAmount = inAmount;
  }

  /**
   * 收入金额.
   * @return 收入金额
   */
  public Double getInAmount() {
      return this.inAmount;
  }

  /**
   * 备注.
   * @param memo 备注
   */
  public void setMemo(String memo) {
    this.memo = memo == null ? null : memo.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getMemo() {
      return this.memo;
  }

  /**
   * 商户订单号.
   * @param merchantOrderNo 商户订单号
   */
  public void setMerchantOrderNo(String merchantOrderNo) {
    this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
  }

  /**
   * 商户订单号.
   * @return 商户订单号
   */
  public String getMerchantOrderNo() {
      return this.merchantOrderNo;
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
   * 对方的支付宝id.
   * @param optUserId 对方的支付宝id
   */
  public void setOptUserId(String optUserId) {
    this.optUserId = optUserId == null ? null : optUserId.trim();
  }

  /**
   * 对方的支付宝id.
   * @return 对方的支付宝id
   */
  public String getOptUserId() {
      return this.optUserId;
  }

  /**
   * 支出金额.
   * @param outAmount 支出金额
   */
  public void setOutAmount(Double outAmount) {
    this.outAmount = outAmount;
  }

  /**
   * 支出金额.
   * @return 支出金额
   */
  public Double getOutAmount() {
      return this.outAmount;
  }

  /**
   * 自己的支付宝id.
   * @param selfUserId 自己的支付宝id
   */
  public void setSelfUserId(String selfUserId) {
    this.selfUserId = selfUserId == null ? null : selfUserId.trim();
  }

  /**
   * 自己的支付宝id.
   * @return 自己的支付宝id
   */
  public String getSelfUserId() {
      return this.selfUserId;
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
   * 财务类型描述.
   * @param typeDesc 财务类型描述
   */
  public void setTypeDesc(String typeDesc) {
    this.typeDesc = typeDesc == null ? null : typeDesc.trim();
  }

  /**
   * 财务类型描述.
   * @return 财务类型描述
   */
  public String getTypeDesc() {
      return this.typeDesc;
  }

  /**
   * 账务类型.
   * @param type 账务类型
   */
  public void setType(AlipayRecordType type) {
    this.type = type;
  }

  /**
   * 账务类型.
   * @return 账务类型
   */
  public AlipayRecordType getType() {
      return this.type;
  }
}