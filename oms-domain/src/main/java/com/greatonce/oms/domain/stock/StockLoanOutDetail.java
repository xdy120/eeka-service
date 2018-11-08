package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
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
public class StockLoanOutDetail extends BaseDO {
  /**
   * 预警数量.
   */
  private Integer alertQuantity;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 通知数量.
   */
  private Integer noticeQuantity;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * 出库时间.
   */
  private LocalDateTime outTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.stockLoanOutDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockLoanOutDetailId;
  }


  /**
   * 预警数量.
   */
  public void setAlertQuantity(Integer alertQuantity) {
    this.alertQuantity = alertQuantity;
  }

  /**
   * 预警数量.
   */
  public Integer getAlertQuantity() {
    return this.alertQuantity;
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
   * 通知数量.
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   */
  public Integer getNoticeQuantity() {
    return this.noticeQuantity;
  }

  /**
   * 出库数量.
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   */
  public Integer getOutQuantity() {
    return this.outQuantity;
  }

  /**
   * 出库时间.
   */
  public void setOutTime(LocalDateTime outTime) {
    this.outTime = outTime;
  }

  /**
   * 出库时间.
   */
  public LocalDateTime getOutTime() {
    return this.outTime;
  }

  /**
   * 计划数量.
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   */
  public Integer getPlanQuantity() {
    return this.planQuantity;
  }

  /**
   * 商品编码.
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   */
  public String getProductCode() {
    return this.productCode;
  }

  /**
   * 商品id.
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   */
  public Long getProductId() {
    return this.productId;
  }

  /**
   * 商品名称.
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * 归还数量.
   */
  public void setReturnQuantity(Integer returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  /**
   * 归还数量.
   */
  public Integer getReturnQuantity() {
    return this.returnQuantity;
  }

  /**
   * 商品规格编码.
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   */
  public String getSkuCode() {
    return this.skuCode;
  }

  /**
   * 商品规格id.
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   */
  public Long getSkuId() {
    return this.skuId;
  }

  /**
   * 商品规格名称.
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   */
  public String getSkuName() {
    return this.skuName;
  }

  /**
   * 借出明细id.
   */
  public void setStockLoanOutDetailId(Long stockLoanOutDetailId) {
    this.stockLoanOutDetailId = stockLoanOutDetailId;
  }

  /**
   * 借出明细id.
   */
  public Long getStockLoanOutDetailId() {
    return this.stockLoanOutDetailId;
  }

  /**
   * 借出单id.
   */
  public void setStockLoanOutId(Long stockLoanOutId) {
    this.stockLoanOutId = stockLoanOutId;
  }

  /**
   * 借出单id.
   */
  public Long getStockLoanOutId() {
    return this.stockLoanOutId;
  }

  /**
   * 剩余数量.
   */
  public void setSurplusQuantity(Integer surplusQuantity) {
    this.surplusQuantity = surplusQuantity;
  }

  /**
   * 剩余数量.
   */
  public Integer getSurplusQuantity() {
    return this.surplusQuantity;
  }

  /**
   * 核销原因.
   */
  public void setVerificationReason(String verificationReason) {
    this.verificationReason = verificationReason == null ? null : verificationReason.trim();
  }

  /**
   * 核销原因.
   */
  public String getVerificationReason() {
    return this.verificationReason;
  }

  /**
   * 核销人.
   */
  public void setVerificationUser(String verificationUser) {
    this.verificationUser = verificationUser == null ? null : verificationUser.trim();
  }

  /**
   * 核销人.
   */
  public String getVerificationUser() {
    return this.verificationUser;
  }
}