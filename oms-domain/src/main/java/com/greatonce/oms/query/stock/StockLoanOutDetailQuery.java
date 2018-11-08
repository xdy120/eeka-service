package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 借出单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockLoanOutDetailQuery extends Query {
  /**
   * 预警数量.
   */
  private Integer alertQuantity;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 通知数量.
   */
  private Integer noticeQuantity;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * 出库时间开始.
   */
  private LocalDateTime outTimeBegin;
  /**
   * 出库时间结束.
   */
  private LocalDateTime outTimeEnd;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * 商品名称.
   */
  private String productName;
  /**
   * 归还数量.
   */
  private Integer returnQuantity;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 商品规格名称.
   */
  private String skuName;
  /**
   * 借出明细id.
   */
  private Long stockLoanOutDetailId;
  /**
   * 借出单id.
   */
  private Long stockLoanOutId;
  /**
   * 剩余数量.
   */
  private Integer surplusQuantity;
  /**
   * 核销原因.
   */
  private String verificationReason;
  /**
   * 核销人.
   */
  private String verificationUser;


  /**
   * 预警数量.
   * @param alertQuantity 预警数量
   */
  public void setAlertQuantity(Integer alertQuantity) {
    this.alertQuantity = alertQuantity;
  }

  /**
   * 预警数量.
   * @return 预警数量
   */
  public Integer getAlertQuantity() {
      return this.alertQuantity;
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
   * 通知数量.
   * @param noticeQuantity 通知数量
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   * @return 通知数量
   */
  public Integer getNoticeQuantity() {
      return this.noticeQuantity;
  }

  /**
   * 出库数量.
   * @param outQuantity 出库数量
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   * @return 出库数量
   */
  public Integer getOutQuantity() {
      return this.outQuantity;
  }

  /**
   * 出库时间开始.
   * @param outTimeBegin 开始.
   */
  public void setOutTimeBegin(LocalDateTime outTimeBegin) {
    this.outTimeBegin = outTimeBegin;
  }

  /**
   * 出库时间开始.
   * @return 出库时间开始
   */
  public LocalDateTime getOutTimeBegin() {
    return this.outTimeBegin;
  }

  /**
   * 出库时间结束.
   * @param outTimeEnd 结束
   */
  public void setOutTimeEnd(LocalDateTime outTimeEnd) {
    this.outTimeEnd = outTimeEnd;
  }

  /**
   * 出库时间结束.
   * @return 出库时间结束
   */
  public LocalDateTime getOutTimeEnd() {
    return this.outTimeEnd;
  }

  /**
   * 计划数量.
   * @param planQuantity 计划数量
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   * @return 计划数量
   */
  public Integer getPlanQuantity() {
      return this.planQuantity;
  }

  /**
   * 商品编码.
   * @param productCode 商品编码
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   * @return 商品编码
   */
  public String getProductCode() {
      return this.productCode;
  }

  /**
   * 商品id.
   * @param productId 商品id
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   * @return 商品id
   */
  public Long getProductId() {
      return this.productId;
  }

  /**
   * 商品名称.
   * @param productName 商品名称
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   * @return 商品名称
   */
  public String getProductName() {
      return this.productName;
  }

  /**
   * 归还数量.
   * @param returnQuantity 归还数量
   */
  public void setReturnQuantity(Integer returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  /**
   * 归还数量.
   * @return 归还数量
   */
  public Integer getReturnQuantity() {
      return this.returnQuantity;
  }

  /**
   * 商品规格编码.
   * @param skuCode 商品规格编码
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   * @return 商品规格编码
   */
  public String getSkuCode() {
      return this.skuCode;
  }

  /**
   * 商品规格id.
   * @param skuId 商品规格id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   * @return 商品规格id
   */
  public Long getSkuId() {
      return this.skuId;
  }

  /**
   * 商品规格名称.
   * @param skuName 商品规格名称
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   * @return 商品规格名称
   */
  public String getSkuName() {
      return this.skuName;
  }

  /**
   * 借出明细id.
   * @param stockLoanOutDetailId 借出明细id
   */
  public void setStockLoanOutDetailId(Long stockLoanOutDetailId) {
    this.stockLoanOutDetailId = stockLoanOutDetailId;
  }

  /**
   * 借出明细id.
   * @return 借出明细id
   */
  public Long getStockLoanOutDetailId() {
      return this.stockLoanOutDetailId;
  }

  /**
   * 借出单id.
   * @param stockLoanOutId 借出单id
   */
  public void setStockLoanOutId(Long stockLoanOutId) {
    this.stockLoanOutId = stockLoanOutId;
  }

  /**
   * 借出单id.
   * @return 借出单id
   */
  public Long getStockLoanOutId() {
      return this.stockLoanOutId;
  }

  /**
   * 剩余数量.
   * @param surplusQuantity 剩余数量
   */
  public void setSurplusQuantity(Integer surplusQuantity) {
    this.surplusQuantity = surplusQuantity;
  }

  /**
   * 剩余数量.
   * @return 剩余数量
   */
  public Integer getSurplusQuantity() {
      return this.surplusQuantity;
  }

  /**
   * 核销原因.
   * @param verificationReason 核销原因
   */
  public void setVerificationReason(String verificationReason) {
    this.verificationReason = verificationReason == null ? null : verificationReason.trim();
  }

  /**
   * 核销原因.
   * @return 核销原因
   */
  public String getVerificationReason() {
      return this.verificationReason;
  }

  /**
   * 核销人.
   * @param verificationUser 核销人
   */
  public void setVerificationUser(String verificationUser) {
    this.verificationUser = verificationUser == null ? null : verificationUser.trim();
  }

  /**
   * 核销人.
   * @return 核销人
   */
  public String getVerificationUser() {
      return this.verificationUser;
  }
}