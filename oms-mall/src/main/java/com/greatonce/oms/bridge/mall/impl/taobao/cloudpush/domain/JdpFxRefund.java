package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain;

import java.time.LocalDateTime;

/**
 * jdp_fx_refund
 *
 * @author code-generator
 */
public class JdpFxRefund {

  private Long subOrderId;
  private LocalDateTime refundCreateTime;
  private Integer refundStatus;
  private String supplierNick;
  private String distributorNick;
  private LocalDateTime modified;
  private String jdpHashcode;
  private String jdpResponse;
  private LocalDateTime jdpModified;
  private LocalDateTime jdpCreated;

  public Long getSubOrderId() {
    return this.subOrderId;
  }

  public void setSubOrderId(Long subOrderId) {
    this.subOrderId = subOrderId;
  }

  public LocalDateTime getRefundCreateTime() {
    return this.refundCreateTime;
  }

  public void setRefundCreateTime(LocalDateTime refundCreateTime) {
    this.refundCreateTime = refundCreateTime;
  }

  public Integer getRefundStatus() {
    return this.refundStatus;
  }

  public void setRefundStatus(Integer refundStatus) {
    this.refundStatus = refundStatus;
  }

  public String getSupplierNick() {
    return this.supplierNick;
  }

  public void setSupplierNick(String supplierNick) {
    this.supplierNick = supplierNick == null ? null : supplierNick.trim();
  }

  public String getDistributorNick() {
    return this.distributorNick;
  }

  public void setDistributorNick(String distributorNick) {
    this.distributorNick = distributorNick == null ? null : distributorNick.trim();
  }

  public LocalDateTime getModified() {
    return this.modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public String getJdpHashcode() {
    return this.jdpHashcode;
  }

  public void setJdpHashcode(String jdpHashcode) {
    this.jdpHashcode = jdpHashcode == null ? null : jdpHashcode.trim();
  }

  public String getJdpResponse() {
    return this.jdpResponse;
  }

  public void setJdpResponse(String jdpResponse) {
    this.jdpResponse = jdpResponse == null ? null : jdpResponse.trim();
  }

  public LocalDateTime getJdpModified() {
    return this.jdpModified;
  }

  public void setJdpModified(LocalDateTime jdpModified) {
    this.jdpModified = jdpModified;
  }

  public LocalDateTime getJdpCreated() {
    return this.jdpCreated;
  }

  public void setJdpCreated(LocalDateTime jdpCreated) {
    this.jdpCreated = jdpCreated;
  }

  /**
   * 打印实体信息 ToString
   */
  public String toString() {
    return "{jdp_fx_refund:subOrderId=" + subOrderId + ",refundCreateTime=" + refundCreateTime
        + ",refundStatus=" + refundStatus + ",supplierNick=" + supplierNick + ",distributorNick="
        + distributorNick + ",modified=" + modified + ",jdpHashcode=" + jdpHashcode
        + ",jdpResponse=" + jdpResponse + ",jdpModified=" + jdpModified + ",jdpCreated="
        + jdpCreated + "}";
  }
}