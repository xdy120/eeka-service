package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.trade.RefundType;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.trade.RefundOrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退款单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class RefundOrder extends VersionDO {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 是否货到付款.
   */
  private Boolean cod;
  /**
   * 是否快速退款.
   */
  private Boolean quickRefund;
  /**
   * 是否已退款.
   */
  private Boolean refunded;
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
   * 收款人.
   */
  private String payeeName;
  /**
   * 收款账号.
   */
  private String payeeNo;
  /**
   * 收款单位.
   */
  private String payeeOrganization;
  /**
   * 退款方式.
   */
  private String refundMethod;
  /**
   * 退款单编码.
   */
  private String refundOrderCode;
  /**
   * 退款单id.
   */
  private Long refundOrderId;
  /**
   * 退款类型.
   */
  private RefundType refundType;
  /**
   * 应退金额.
   */
  private Double refundableAmount;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 退换货单code.
   */
  private String returnOrderCode;
  /**
   * 退换货单id.
   */
  private Long returnOrderId;
  /**
   * 退货原因.
   */
  private String returnReasonType;
  /**
   * 复核时间.
   */
  private LocalDateTime reviewTime;
  /**
   * 复核人.
   */
  private String reviewer;
  /**
   * 销售单编码.
   */
  private String salesOrderCode;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 状态.
   */
  private RefundOrderStatus status;
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
   * 版本.
   */
  private Integer version;

  /**
   * 明细.
   */
  private List<RefundOrderDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.refundOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.refundOrderId;
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
   * 审核时间.
   */
  public void setAuditedTime(LocalDateTime auditedTime) {
    this.auditedTime = auditedTime;
  }

  /**
   * 审核时间.
   */
  public LocalDateTime getAuditedTime() {
    return this.auditedTime;
  }

  /**
   * 审核人.
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   */
  public String getAuditor() {
    return this.auditor;
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
   * 创建人.
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   */
  public String getCreator() {
    return this.creator;
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
   * 是否快速退款.
   */
  public void setQuickRefund(Boolean quickRefund) {
    this.quickRefund = quickRefund;
  }

  /**
   * 是否快速退款.
   */
  public Boolean isQuickRefund() {
    return this.quickRefund;
  }

  /**
   * 是否已退款.
   */
  public void setRefunded(Boolean refunded) {
    this.refunded = refunded;
  }

  /**
   * 是否已退款.
   */
  public Boolean isRefunded() {
    return this.refunded;
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
   * 收款人.
   */
  public void setPayeeName(String payeeName) {
    this.payeeName = payeeName == null ? null : payeeName.trim();
  }

  /**
   * 收款人.
   */
  public String getPayeeName() {
    return this.payeeName;
  }

  /**
   * 收款账号.
   */
  public void setPayeeNo(String payeeNo) {
    this.payeeNo = payeeNo == null ? null : payeeNo.trim();
  }

  /**
   * 收款账号.
   */
  public String getPayeeNo() {
    return this.payeeNo;
  }

  /**
   * 收款单位.
   */
  public void setPayeeOrganization(String payeeOrganization) {
    this.payeeOrganization = payeeOrganization == null ? null : payeeOrganization.trim();
  }

  /**
   * 收款单位.
   */
  public String getPayeeOrganization() {
    return this.payeeOrganization;
  }


  /**
   * 退款方式.
   */
  public void setRefundMethod(String refundMethod) {
    this.refundMethod = refundMethod == null ? null : refundMethod.trim();
  }

  /**
   * 退款方式.
   */
  public String getRefundMethod() {
    return this.refundMethod;
  }

  /**
   * 退款单编码.
   */
  public void setRefundOrderCode(String refundOrderCode) {
    this.refundOrderCode = refundOrderCode == null ? null : refundOrderCode.trim();
  }

  /**
   * 退款单编码.
   */
  public String getRefundOrderCode() {
    return this.refundOrderCode;
  }


  /**
   * 退款单id.
   */
  public void setRefundOrderId(Long refundOrderId) {
    this.refundOrderId = refundOrderId;
  }

  /**
   * 退款单id.
   */
  public Long getRefundOrderId() {
    return this.refundOrderId;
  }

  /**
   * 退款类型.
   */
  public void setRefundType(RefundType refundType) {
    this.refundType = refundType;
  }

  /**
   * 退款类型.
   */
  public RefundType getRefundType() {
    return this.refundType;
  }

  /**
   * 应退金额.
   */
  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  /**
   * 应退金额.
   */
  public Double getRefundableAmount() {
    return this.refundableAmount;
  }

  /**
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
  }

  /**
   * 退换货单code.
   */
  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode == null ? null : returnOrderCode.trim();
  }

  /**
   * 退换货单code.
   */
  public String getReturnOrderCode() {
    return this.returnOrderCode;
  }


  /**
   * 退换货单id.
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   */
  public Long getReturnOrderId() {
    return this.returnOrderId;
  }

  /**
   * 退货原因.
   */
  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType == null ? null : returnReasonType.trim();
  }

  /**
   * 退货原因.
   */
  public String getReturnReasonType() {
    return this.returnReasonType;
  }

  /**
   * 复核时间.
   */
  public void setReviewTime(LocalDateTime reviewTime) {
    this.reviewTime = reviewTime;
  }

  /**
   * 复核时间.
   */
  public LocalDateTime getReviewTime() {
    return this.reviewTime;
  }

  /**
   * 复核人.
   */
  public void setReviewer(String reviewer) {
    this.reviewer = reviewer == null ? null : reviewer.trim();
  }

  /**
   * 复核人.
   */
  public String getReviewer() {
    return this.reviewer;
  }

  /**
   * 销售单编码.
   */
  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode == null ? null : salesOrderCode.trim();
  }

  /**
   * 销售单编码.
   */
  public String getSalesOrderCode() {
    return this.salesOrderCode;
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
   * 状态.
   */
  public void setStatus(RefundOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public RefundOrderStatus getStatus() {
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


  /**
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }

  /**
   * 明细.
   */
  public void setDetails(List<RefundOrderDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<RefundOrderDetail> getDetails() {
    return this.details;
  }
}