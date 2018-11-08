package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/9/27
 */
public class ReturnOrderExportBO {

  private String tradeId;
  private String salesOrderCode;
  private Integer applyQuantity;
  private Integer quantity;
  private String storeName;
  private String inVirtualWarehouseName;
  private String expressNo;
  private String unpacker;
  private String returnOrderCode;
  private String skuCode;
  private Integer skuQuantity;
  private String returnReasonType;
  private String returnType;
  private ReturnOrderStatus status;
  private Boolean exchange;
  private String boxNo;
  private String skuName;
  private Double refundableAmount;
  private String remark;
  private Boolean noticed;
  private String returnNoticeOrderCode;
  private String memberName;
  private String expressName;
  private LocalDateTime unpackedTime;
  private LocalDateTime createdTime;
  private String creator;
  private LocalDateTime auditedTime;
  private String auditor;
  private LocalDateTime reviewTime;
  private String reviewer;

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getSalesOrderCode() {
    return salesOrderCode;
  }

  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode;
  }

  public Integer getApplyQuantity() {
    return applyQuantity;
  }

  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getInVirtualWarehouseName() {
    return inVirtualWarehouseName;
  }

  public void setInVirtualWarehouseName(String inVirtualWarehouseName) {
    this.inVirtualWarehouseName = inVirtualWarehouseName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getUnpacker() {
    return unpacker;
  }

  public void setUnpacker(String unpacker) {
    this.unpacker = unpacker;
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

  public Integer getSkuQuantity() {
    return skuQuantity;
  }

  public void setSkuQuantity(Integer skuQuantity) {
    this.skuQuantity = skuQuantity;
  }

  public String getReturnReasonType() {
    return returnReasonType;
  }

  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public ReturnOrderStatus getStatus() {
    return status;
  }

  public void setStatus(ReturnOrderStatus status) {
    this.status = status;
  }

  public Boolean getExchange() {
    return exchange;
  }

  public void setExchange(Boolean exchange) {
    this.exchange = exchange;
  }

  public String getBoxNo() {
    return boxNo;
  }

  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public Double getRefundableAmount() {
    return refundableAmount;
  }

  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Boolean getNoticed() {
    return noticed;
  }

  public void setNoticed(Boolean noticed) {
    this.noticed = noticed;
  }

  public String getReturnNoticeOrderCode() {
    return returnNoticeOrderCode;
  }

  public void setReturnNoticeOrderCode(String returnNoticeOrderCode) {
    this.returnNoticeOrderCode = returnNoticeOrderCode;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public LocalDateTime getUnpackedTime() {
    return unpackedTime;
  }

  public void setUnpackedTime(LocalDateTime unpackedTime) {
    this.unpackedTime = unpackedTime;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public LocalDateTime getAuditedTime() {
    return auditedTime;
  }

  public void setAuditedTime(LocalDateTime auditedTime) {
    this.auditedTime = auditedTime;
  }

  public String getAuditor() {
    return auditor;
  }

  public void setAuditor(String auditor) {
    this.auditor = auditor;
  }

  public LocalDateTime getReviewTime() {
    return reviewTime;
  }

  public void setReviewTime(LocalDateTime reviewTime) {
    this.reviewTime = reviewTime;
  }

  public String getReviewer() {
    return reviewer;
  }

  public void setReviewer(String reviewer) {
    this.reviewer = reviewer;
  }
}
