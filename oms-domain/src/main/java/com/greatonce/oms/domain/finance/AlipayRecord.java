package com.greatonce.oms.domain.finance;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.finance.AlipayRecordType;
import java.time.LocalDateTime;

/**
 * 支付宝账单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class AlipayRecord extends BaseDO {
  /**
   * 支付宝创建时间.
   */
  private LocalDateTime alipayCreatedTime;
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
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.alipayRecordId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.alipayRecordId;
  }


  /**
   * 支付宝创建时间.
   */
  public void setAlipayCreatedTime(LocalDateTime alipayCreatedTime) {
    this.alipayCreatedTime = alipayCreatedTime;
  }

  /**
   * 支付宝创建时间.
   */
  public LocalDateTime getAlipayCreatedTime() {
    return this.alipayCreatedTime;
  }

  /**
   * 支付宝订单号.
   */
  public void setAlipayOrderNo(String alipayOrderNo) {
    this.alipayOrderNo = alipayOrderNo == null ? null : alipayOrderNo.trim();
  }

  /**
   * 支付宝订单号.
   */
  public String getAlipayOrderNo() {
    return this.alipayOrderNo;
  }

  /**
   * 支付宝账单id.
   */
  public void setAlipayRecordId(Long alipayRecordId) {
    this.alipayRecordId = alipayRecordId;
  }

  /**
   * 支付宝账单id.
   */
  public Long getAlipayRecordId() {
    return this.alipayRecordId;
  }

  /**
   * 支付宝账户余额.
   */
  public void setBalance(Double balance) {
    this.balance = balance;
  }

  /**
   * 支付宝账户余额.
   */
  public Double getBalance() {
    return this.balance;
  }

  /**
   * 子业务类型描述.
   */
  public void setBusinessTypeDesc(String businessTypeDesc) {
    this.businessTypeDesc = businessTypeDesc == null ? null : businessTypeDesc.trim();
  }

  /**
   * 子业务类型描述.
   */
  public String getBusinessTypeDesc() {
    return this.businessTypeDesc;
  }

  /**
   * 子业务类型.
   */
  public void setBusinessType(String businessType) {
    this.businessType = businessType == null ? null : businessType.trim();
  }

  /**
   * 子业务类型.
   */
  public String getBusinessType() {
    return this.businessType;
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
   * 收入金额.
   */
  public void setInAmount(Double inAmount) {
    this.inAmount = inAmount;
  }

  /**
   * 收入金额.
   */
  public Double getInAmount() {
    return this.inAmount;
  }

  /**
   * 备注.
   */
  public void setMemo(String memo) {
    this.memo = memo == null ? null : memo.trim();
  }

  /**
   * 备注.
   */
  public String getMemo() {
    return this.memo;
  }

  /**
   * 商户订单号.
   */
  public void setMerchantOrderNo(String merchantOrderNo) {
    this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
  }

  /**
   * 商户订单号.
   */
  public String getMerchantOrderNo() {
    return this.merchantOrderNo;
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
   * 对方的支付宝id.
   */
  public void setOptUserId(String optUserId) {
    this.optUserId = optUserId == null ? null : optUserId.trim();
  }

  /**
   * 对方的支付宝id.
   */
  public String getOptUserId() {
    return this.optUserId;
  }

  /**
   * 支出金额.
   */
  public void setOutAmount(Double outAmount) {
    this.outAmount = outAmount;
  }

  /**
   * 支出金额.
   */
  public Double getOutAmount() {
    return this.outAmount;
  }

  /**
   * 自己的支付宝id.
   */
  public void setSelfUserId(String selfUserId) {
    this.selfUserId = selfUserId == null ? null : selfUserId.trim();
  }

  /**
   * 自己的支付宝id.
   */
  public String getSelfUserId() {
    return this.selfUserId;
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

  /**
   * 财务类型描述.
   */
  public void setTypeDesc(String typeDesc) {
    this.typeDesc = typeDesc == null ? null : typeDesc.trim();
  }

  /**
   * 财务类型描述.
   */
  public String getTypeDesc() {
    return this.typeDesc;
  }

  /**
   * 账务类型.
   */
  public void setType(AlipayRecordType type) {
    this.type = type;
  }

  /**
   * 账务类型.
   */
  public AlipayRecordType getType() {
    return this.type;
  }
}