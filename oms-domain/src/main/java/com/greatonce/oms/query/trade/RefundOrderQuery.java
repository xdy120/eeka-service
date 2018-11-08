package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class RefundOrderQuery extends Query {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 审核时间开始.
   */
  private LocalDateTime auditedTimeBegin;
  /**
   * 审核时间结束.
   */
  private LocalDateTime auditedTimeEnd;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * .
   */
  private List<String> productCodes;
  /**
   * 退款方式.
   */
  private String refundMethod;
  /**
   * 退款单编码.
   */
  private String refundOrderCode;
  /**
   * .
   */
  private List<String> refundOrderCodes;
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
   * .
   */
  private List<String> returnOrderCodes;
  /**
   * 退换货单id.
   */
  private Long returnOrderId;
  /**
   * 退货原因.
   */
  private String returnReasonType;
  /**
   * 复核时间开始.
   */
  private LocalDateTime reviewTimeBegin;
  /**
   * 复核时间结束.
   */
  private LocalDateTime reviewTimeEnd;
  /**
   * 复核人.
   */
  private String reviewer;
  /**
   * 销售单编码.
   */
  private String salesOrderCode;
  /**
   * .
   */
  private List<String> salesOrderCodes;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * 状态.
   */
  private RefundOrderStatus status;
  /**
   * .
   */
  private List<RefundOrderStatus> statuses;
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
   * .
   */
  private List<String> tradeIds;
  /**
   * 版本.
   */
  private Integer version;


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
   * 审核时间开始.
   * @param auditedTimeBegin 开始.
   */
  public void setAuditedTimeBegin(LocalDateTime auditedTimeBegin) {
    this.auditedTimeBegin = auditedTimeBegin;
  }

  /**
   * 审核时间开始.
   * @return 审核时间开始
   */
  public LocalDateTime getAuditedTimeBegin() {
    return this.auditedTimeBegin;
  }

  /**
   * 审核时间结束.
   * @param auditedTimeEnd 结束
   */
  public void setAuditedTimeEnd(LocalDateTime auditedTimeEnd) {
    this.auditedTimeEnd = auditedTimeEnd;
  }

  /**
   * 审核时间结束.
   * @return 审核时间结束
   */
  public LocalDateTime getAuditedTimeEnd() {
    return this.auditedTimeEnd;
  }

  /**
   * 审核人.
   * @param auditor 审核人
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   * @return 审核人
   */
  public String getAuditor() {
      return this.auditor;
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
   * 创建人.
   * @param creator 创建人
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   * @return 创建人
   */
  public String getCreator() {
      return this.creator;
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
   * 是否快速退款.
   * @param quickRefund 是否快速退款
   */
  public void setQuickRefund(Boolean quickRefund) {
    this.quickRefund = quickRefund;
  }

  /**
   * 是否快速退款.
   * @return 是否快速退款
   */
  public Boolean isQuickRefund() {
      return this.quickRefund;
  }

  /**
   * 是否已退款.
   * @param refunded 是否已退款
   */
  public void setRefunded(Boolean refunded) {
    this.refunded = refunded;
  }

  /**
   * 是否已退款.
   * @return 是否已退款
   */
  public Boolean isRefunded() {
      return this.refunded;
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
   * 收款人.
   * @param payeeName 收款人
   */
  public void setPayeeName(String payeeName) {
    this.payeeName = payeeName == null ? null : payeeName.trim();
  }

  /**
   * 收款人.
   * @return 收款人
   */
  public String getPayeeName() {
      return this.payeeName;
  }

  /**
   * 收款账号.
   * @param payeeNo 收款账号
   */
  public void setPayeeNo(String payeeNo) {
    this.payeeNo = payeeNo == null ? null : payeeNo.trim();
  }

  /**
   * 收款账号.
   * @return 收款账号
   */
  public String getPayeeNo() {
      return this.payeeNo;
  }

  /**
   * 收款单位.
   * @param payeeOrganization 收款单位
   */
  public void setPayeeOrganization(String payeeOrganization) {
    this.payeeOrganization = payeeOrganization == null ? null : payeeOrganization.trim();
  }

  /**
   * 收款单位.
   * @return 收款单位
   */
  public String getPayeeOrganization() {
      return this.payeeOrganization;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
  }

  /**
   * 退款方式.
   * @param refundMethod 退款方式
   */
  public void setRefundMethod(String refundMethod) {
    this.refundMethod = refundMethod == null ? null : refundMethod.trim();
  }

  /**
   * 退款方式.
   * @return 退款方式
   */
  public String getRefundMethod() {
      return this.refundMethod;
  }

  /**
   * 退款单编码.
   * @param refundOrderCode 退款单编码
   */
  public void setRefundOrderCode(String refundOrderCode) {
    this.refundOrderCode = refundOrderCode == null ? null : refundOrderCode.trim();
  }

  /**
   * 退款单编码.
   * @return 退款单编码
   */
  public String getRefundOrderCode() {
      return this.refundOrderCode;
  }

  /**
   * .
   * @param refundOrderCodes 
   */
  public void setRefundOrderCodes(List<String> refundOrderCodes) {
    this.refundOrderCodes = refundOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getRefundOrderCodes() {
      return this.refundOrderCodes;
  }

  /**
   * 退款单id.
   * @param refundOrderId 退款单id
   */
  public void setRefundOrderId(Long refundOrderId) {
    this.refundOrderId = refundOrderId;
  }

  /**
   * 退款单id.
   * @return 退款单id
   */
  public Long getRefundOrderId() {
      return this.refundOrderId;
  }

  /**
   * 退款类型.
   * @param refundType 退款类型
   */
  public void setRefundType(RefundType refundType) {
    this.refundType = refundType;
  }

  /**
   * 退款类型.
   * @return 退款类型
   */
  public RefundType getRefundType() {
      return this.refundType;
  }

  /**
   * 应退金额.
   * @param refundableAmount 应退金额
   */
  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  /**
   * 应退金额.
   * @return 应退金额
   */
  public Double getRefundableAmount() {
      return this.refundableAmount;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * 退换货单code.
   * @param returnOrderCode 退换货单code
   */
  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode == null ? null : returnOrderCode.trim();
  }

  /**
   * 退换货单code.
   * @return 退换货单code
   */
  public String getReturnOrderCode() {
      return this.returnOrderCode;
  }

  /**
   * .
   * @param returnOrderCodes 
   */
  public void setReturnOrderCodes(List<String> returnOrderCodes) {
    this.returnOrderCodes = returnOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getReturnOrderCodes() {
      return this.returnOrderCodes;
  }

  /**
   * 退换货单id.
   * @param returnOrderId 退换货单id
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   * @return 退换货单id
   */
  public Long getReturnOrderId() {
      return this.returnOrderId;
  }

  /**
   * 退货原因.
   * @param returnReasonType 退货原因
   */
  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType == null ? null : returnReasonType.trim();
  }

  /**
   * 退货原因.
   * @return 退货原因
   */
  public String getReturnReasonType() {
      return this.returnReasonType;
  }

  /**
   * 复核时间开始.
   * @param reviewTimeBegin 开始.
   */
  public void setReviewTimeBegin(LocalDateTime reviewTimeBegin) {
    this.reviewTimeBegin = reviewTimeBegin;
  }

  /**
   * 复核时间开始.
   * @return 复核时间开始
   */
  public LocalDateTime getReviewTimeBegin() {
    return this.reviewTimeBegin;
  }

  /**
   * 复核时间结束.
   * @param reviewTimeEnd 结束
   */
  public void setReviewTimeEnd(LocalDateTime reviewTimeEnd) {
    this.reviewTimeEnd = reviewTimeEnd;
  }

  /**
   * 复核时间结束.
   * @return 复核时间结束
   */
  public LocalDateTime getReviewTimeEnd() {
    return this.reviewTimeEnd;
  }

  /**
   * 复核人.
   * @param reviewer 复核人
   */
  public void setReviewer(String reviewer) {
    this.reviewer = reviewer == null ? null : reviewer.trim();
  }

  /**
   * 复核人.
   * @return 复核人
   */
  public String getReviewer() {
      return this.reviewer;
  }

  /**
   * 销售单编码.
   * @param salesOrderCode 销售单编码
   */
  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode == null ? null : salesOrderCode.trim();
  }

  /**
   * 销售单编码.
   * @return 销售单编码
   */
  public String getSalesOrderCode() {
      return this.salesOrderCode;
  }

  /**
   * .
   * @param salesOrderCodes 
   */
  public void setSalesOrderCodes(List<String> salesOrderCodes) {
    this.salesOrderCodes = salesOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSalesOrderCodes() {
      return this.salesOrderCodes;
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
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(RefundOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public RefundOrderStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<RefundOrderStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<RefundOrderStatus> getStatuses() {
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

  /**
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }
}