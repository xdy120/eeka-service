package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.enums.trade.InvoiceType;
import java.time.LocalDateTime;

/**
 * 配货单发票信息.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/30
 */
public class InvoiceInfo {

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
   * 纳税人识别号.
   */
  private String taxpayerId;

  public Double getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public String getGmfAddress() {
    return gmfAddress;
  }

  public void setGmfAddress(String gmfAddress) {
    this.gmfAddress = gmfAddress;
  }

  public String getGmfBankName() {
    return gmfBankName;
  }

  public void setGmfBankName(String gmfBankName) {
    this.gmfBankName = gmfBankName;
  }

  public String getGmfBankNo() {
    return gmfBankNo;
  }

  public void setGmfBankNo(String gmfBankNo) {
    this.gmfBankNo = gmfBankNo;
  }

  public String getGmfMobile() {
    return gmfMobile;
  }

  public void setGmfMobile(String gmfMobile) {
    this.gmfMobile = gmfMobile;
  }

  public String getInvoiceContent() {
    return invoiceContent;
  }

  public void setInvoiceContent(String invoiceContent) {
    this.invoiceContent = invoiceContent;
  }

  public String getInvoiceTitle() {
    return invoiceTitle;
  }

  public void setInvoiceTitle(String invoiceTitle) {
    this.invoiceTitle = invoiceTitle;
  }

  public InvoiceType getInvoiceType() {
    return invoiceType;
  }

  public void setInvoiceType(InvoiceType invoiceType) {
    this.invoiceType = invoiceType;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public String getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(String taxpayerId) {
    this.taxpayerId = taxpayerId;
  }
}
