package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain;

import java.time.LocalDateTime;

/**
 * jdp_fx_trade
 *
 * @author code-generator
 */
public class JdpFxTrade {

  private Long fenxiaoId;
  private String status;
  private String tcOrderId;
  private String supplierUsername;
  private String distributorUsername;
  private LocalDateTime created;
  private LocalDateTime modified;
  private String jdpHashcode;
  private String jdpResponse;
  private LocalDateTime jdpModified;
  private LocalDateTime jdpCreated;

  public Long getFenxiaoId() {
    return this.fenxiaoId;
  }

  public void setFenxiaoId(Long fenxiaoId) {
    this.fenxiaoId = fenxiaoId;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  public String getTcOrderId() {
    return this.tcOrderId;
  }

  public void setTcOrderId(String tcOrderId) {
    this.tcOrderId = tcOrderId == null ? null : tcOrderId.trim();
  }

  public String getSupplierUsername() {
    return this.supplierUsername;
  }

  public void setSupplierUsername(String supplierUsername) {
    this.supplierUsername = supplierUsername == null ? null : supplierUsername.trim();
  }

  public String getDistributorUsername() {
    return this.distributorUsername;
  }

  public void setDistributorUsername(String distributorUsername) {
    this.distributorUsername = distributorUsername == null ? null : distributorUsername.trim();
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
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
    return "{jdp_fx_trade:fenxiaoId=" + fenxiaoId + ",status=" + status + ",tcOrderId=" + tcOrderId
        + ",supplierUsername=" + supplierUsername + ",distributorUsername=" + distributorUsername
        + ",created=" + created + ",modified=" + modified + ",jdpHashcode=" + jdpHashcode
        + ",jdpResponse=" + jdpResponse + ",jdpModified=" + jdpModified + ",jdpCreated="
        + jdpCreated + "}";
  }
}