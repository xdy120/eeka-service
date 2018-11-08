package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
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
public class SalesOrderInvoice extends BaseDO {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.salesOrderInvoiceId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.salesOrderInvoiceId;
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
   * 地址.
   */
  public void setGmfAddress(String gmfAddress) {
    this.gmfAddress = gmfAddress == null ? null : gmfAddress.trim();
  }

  /**
   * 地址.
   */
  public String getGmfAddress() {
    return this.gmfAddress;
  }

  /**
   * 开户行.
   */
  public void setGmfBankName(String gmfBankName) {
    this.gmfBankName = gmfBankName == null ? null : gmfBankName.trim();
  }

  /**
   * 开户行.
   */
  public String getGmfBankName() {
    return this.gmfBankName;
  }

  /**
   * 账号.
   */
  public void setGmfBankNo(String gmfBankNo) {
    this.gmfBankNo = gmfBankNo == null ? null : gmfBankNo.trim();
  }

  /**
   * 账号.
   */
  public String getGmfBankNo() {
    return this.gmfBankNo;
  }

  /**
   * 发票电话.
   */
  public void setGmfMobile(String gmfMobile) {
    this.gmfMobile = gmfMobile == null ? null : gmfMobile.trim();
  }

  /**
   * 发票电话.
   */
  public String getGmfMobile() {
    return this.gmfMobile;
  }

  /**
   * 发票内容.
   */
  public void setInvoiceContent(String invoiceContent) {
    this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
  }

  /**
   * 发票内容.
   */
  public String getInvoiceContent() {
    return this.invoiceContent;
  }

  /**
   * 发票抬头.
   */
  public void setInvoiceTitle(String invoiceTitle) {
    this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
  }

  /**
   * 发票抬头.
   */
  public String getInvoiceTitle() {
    return this.invoiceTitle;
  }

  /**
   * 发票类型.
   */
  public void setInvoiceType(InvoiceType invoiceType) {
    this.invoiceType = invoiceType;
  }

  /**
   * 发票类型.
   */
  public InvoiceType getInvoiceType() {
    return this.invoiceType;
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
   * 发票id.
   */
  public void setSalesOrderInvoiceId(Long salesOrderInvoiceId) {
    this.salesOrderInvoiceId = salesOrderInvoiceId;
  }

  /**
   * 发票id.
   */
  public Long getSalesOrderInvoiceId() {
    return this.salesOrderInvoiceId;
  }

  /**
   * 纳税人识别号.
   */
  public void setTaxpayerId(String taxpayerId) {
    this.taxpayerId = taxpayerId == null ? null : taxpayerId.trim();
  }

  /**
   * 纳税人识别号.
   */
  public String getTaxpayerId() {
    return this.taxpayerId;
  }
}