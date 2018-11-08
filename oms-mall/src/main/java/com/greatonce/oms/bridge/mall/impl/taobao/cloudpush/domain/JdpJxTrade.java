package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain;

import java.time.LocalDateTime;

/**
 * jdp_jx_trade
 *
 * @author code-generator
 */
public class JdpJxTrade {

  private Long dealerOrderId;
  private String orderStatus;
  private String supplierNick;
  private String applierNick;
  private LocalDateTime appliedTime;
  private LocalDateTime modifiedTime;
  private String jdpHashcode;
  private String jdpResponse;
  private LocalDateTime jdpModified;
  private LocalDateTime jdpCreated;

  public Long getDealerOrderId() {
    return this.dealerOrderId;
  }

  public void setDealerOrderId(Long dealerOrderId) {
    this.dealerOrderId = dealerOrderId;
  }

  public String getOrderStatus() {
    return this.orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus == null ? null : orderStatus.trim();
  }

  public String getSupplierNick() {
    return this.supplierNick;
  }

  public void setSupplierNick(String supplierNick) {
    this.supplierNick = supplierNick == null ? null : supplierNick.trim();
  }

  public String getApplierNick() {
    return this.applierNick;
  }

  public void setApplierNick(String applierNick) {
    this.applierNick = applierNick == null ? null : applierNick.trim();
  }

  public LocalDateTime getAppliedTime() {
    return this.appliedTime;
  }

  public void setAppliedTime(LocalDateTime appliedTime) {
    this.appliedTime = appliedTime;
  }

  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
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
    return "{jdp_jx_trade:dealerOrderId=" + dealerOrderId + ",orderStatus=" + orderStatus
        + ",supplierNick=" + supplierNick + ",applierNick=" + applierNick + ",appliedTime="
        + appliedTime + ",modifiedTime=" + modifiedTime + ",jdpHashcode=" + jdpHashcode
        + ",jdpResponse=" + jdpResponse + ",jdpModified=" + jdpModified + ",jdpCreated="
        + jdpCreated + "}";
  }
}