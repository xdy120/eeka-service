package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.trade.InvoiceType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售订单发票明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SalesOrderInvoiceQuery extends Query {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 地址.
   */
  private String gmfAddress;
  /**
   * 开户行.
   */
  private String gmfBankName;
  /**
   * 账号.
   */
  private String gmfBankNo;
  /**
   * 发票电话.
   */
  private String gmfMobile;
  /**
   * 发票内容.
   */
  private String invoiceContent;
  /**
   * 发票抬头.
   */
  private String invoiceTitle;
  /**
   * 发票类型.
   */
  private InvoiceType invoiceType;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 发票id.
   */
  private Long salesOrderInvoiceId;
  /**
   * 纳税人识别号.
   */
  private String taxpayerId;


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
   * 地址.
   * @param gmfAddress 地址
   */
  public void setGmfAddress(String gmfAddress) {
    this.gmfAddress = gmfAddress == null ? null : gmfAddress.trim();
  }

  /**
   * 地址.
   * @return 地址
   */
  public String getGmfAddress() {
      return this.gmfAddress;
  }

  /**
   * 开户行.
   * @param gmfBankName 开户行
   */
  public void setGmfBankName(String gmfBankName) {
    this.gmfBankName = gmfBankName == null ? null : gmfBankName.trim();
  }

  /**
   * 开户行.
   * @return 开户行
   */
  public String getGmfBankName() {
      return this.gmfBankName;
  }

  /**
   * 账号.
   * @param gmfBankNo 账号
   */
  public void setGmfBankNo(String gmfBankNo) {
    this.gmfBankNo = gmfBankNo == null ? null : gmfBankNo.trim();
  }

  /**
   * 账号.
   * @return 账号
   */
  public String getGmfBankNo() {
      return this.gmfBankNo;
  }

  /**
   * 发票电话.
   * @param gmfMobile 发票电话
   */
  public void setGmfMobile(String gmfMobile) {
    this.gmfMobile = gmfMobile == null ? null : gmfMobile.trim();
  }

  /**
   * 发票电话.
   * @return 发票电话
   */
  public String getGmfMobile() {
      return this.gmfMobile;
  }

  /**
   * 发票内容.
   * @param invoiceContent 发票内容
   */
  public void setInvoiceContent(String invoiceContent) {
    this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
  }

  /**
   * 发票内容.
   * @return 发票内容
   */
  public String getInvoiceContent() {
      return this.invoiceContent;
  }

  /**
   * 发票抬头.
   * @param invoiceTitle 发票抬头
   */
  public void setInvoiceTitle(String invoiceTitle) {
    this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
  }

  /**
   * 发票抬头.
   * @return 发票抬头
   */
  public String getInvoiceTitle() {
      return this.invoiceTitle;
  }

  /**
   * 发票类型.
   * @param invoiceType 发票类型
   */
  public void setInvoiceType(InvoiceType invoiceType) {
    this.invoiceType = invoiceType;
  }

  /**
   * 发票类型.
   * @return 发票类型
   */
  public InvoiceType getInvoiceType() {
      return this.invoiceType;
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
   * 发票id.
   * @param salesOrderInvoiceId 发票id
   */
  public void setSalesOrderInvoiceId(Long salesOrderInvoiceId) {
    this.salesOrderInvoiceId = salesOrderInvoiceId;
  }

  /**
   * 发票id.
   * @return 发票id
   */
  public Long getSalesOrderInvoiceId() {
      return this.salesOrderInvoiceId;
  }

  /**
   * 纳税人识别号.
   * @param taxpayerId 纳税人识别号
   */
  public void setTaxpayerId(String taxpayerId) {
    this.taxpayerId = taxpayerId == null ? null : taxpayerId.trim();
  }

  /**
   * 纳税人识别号.
   * @return 纳税人识别号
   */
  public String getTaxpayerId() {
      return this.taxpayerId;
  }
}