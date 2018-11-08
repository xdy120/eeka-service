package com.greatonce.oms.bo.report;

import com.greatonce.oms.domain.enums.mall.MallExchangeStatus;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderType;
import java.time.LocalDateTime;

public class ReturnDetailBO {

  private String storeName;
  private Integer noticeQuantity;
  private Integer inQuantity;
  private String returnOrderCode;
  private String skuCode;
  private String salesOrderCode;
  private String expressName;
  private String expressNo;
  private String memberName;
  private String returnType;
  private String unpacker;
  private LocalDateTime unpackedTime;
  private Boolean isExchange;

  private String productCode;
  private String productName;
  private Double refundableAmount;

  private Double costPrice;

  private LocalDateTime appliedTime;
  private Double applyAmount;
  private RefundApplyOrderType refundApplyOrderType;
  private MallRefundStatus mallRefundStatus;
  private MallExchangeStatus mallExchangeStatus;
  private LocalDateTime lastInTime;

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  public Integer getInQuantity() {
    return inQuantity;
  }

  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  public String getReturnOrderCode() {
    return returnOrderCode;
  }

  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public String getSalesOrderCode() {
    return salesOrderCode;
  }

  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode;
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

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public String getUnpacker() {
    return unpacker;
  }

  public void setUnpacker(String unpacker) {
    this.unpacker = unpacker;
  }

  public LocalDateTime getUnpackedTime() {
    return unpackedTime;
  }

  public void setUnpackedTime(LocalDateTime unpackedTime) {
    this.unpackedTime = unpackedTime;
  }

  public Boolean getExchange() {
    return isExchange;
  }

  public void setExchange(Boolean exchange) {
    isExchange = exchange;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getRefundableAmount() {
    return refundableAmount;
  }

  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  public Double getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(Double costPrice) {
    this.costPrice = costPrice;
  }

  public LocalDateTime getAppliedTime() {
    return appliedTime;
  }

  public void setAppliedTime(LocalDateTime appliedTime) {
    this.appliedTime = appliedTime;
  }

  public Double getApplyAmount() {
    return applyAmount;
  }

  public void setApplyAmount(Double applyAmount) {
    this.applyAmount = applyAmount;
  }

  public RefundApplyOrderType getRefundApplyOrderType() {
    return refundApplyOrderType;
  }

  public void setRefundApplyOrderType(
      RefundApplyOrderType refundApplyOrderType) {
    this.refundApplyOrderType = refundApplyOrderType;
  }

  public MallRefundStatus getMallRefundStatus() {
    return mallRefundStatus;
  }

  public void setMallRefundStatus(MallRefundStatus mallRefundStatus) {
    this.mallRefundStatus = mallRefundStatus;
  }

  public MallExchangeStatus getMallExchangeStatus() {
    return mallExchangeStatus;
  }

  public void setMallExchangeStatus(
      MallExchangeStatus mallExchangeStatus) {
    this.mallExchangeStatus = mallExchangeStatus;
  }

  public LocalDateTime getLastInTime() {
    return lastInTime;
  }

  public void setLastInTime(LocalDateTime lastInTime) {
    this.lastInTime = lastInTime;
  }
}
