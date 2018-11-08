package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain;

import java.time.LocalDateTime;

/**
 * jdp_tb_refund
 *
 * @author code-generator
 */
public class JdpTbRefund {

  private Long refundId;
  private String sellerNick;
  private String buyerNick;
  private String status;
  private LocalDateTime created;
  private Long tid;
  private Long oid;
  private LocalDateTime modified;
  private String jdpHashcode;
  private String jdpResponse;
  private LocalDateTime jdpCreated;
  private LocalDateTime jdpModified;

  public Long getRefundId() {
    return this.refundId;
  }

  public void setRefundId(Long refundId) {
    this.refundId = refundId;
  }

  public String getSellerNick() {
    return this.sellerNick;
  }

  public void setSellerNick(String sellerNick) {
    this.sellerNick = sellerNick == null ? null : sellerNick.trim();
  }

  public String getBuyerNick() {
    return this.buyerNick;
  }

  public void setBuyerNick(String buyerNick) {
    this.buyerNick = buyerNick == null ? null : buyerNick.trim();
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public Long getTid() {
    return this.tid;
  }

  public void setTid(Long tid) {
    this.tid = tid;
  }

  public Long getOid() {
    return this.oid;
  }

  public void setOid(Long oid) {
    this.oid = oid;
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

  public LocalDateTime getJdpCreated() {
    return this.jdpCreated;
  }

  public void setJdpCreated(LocalDateTime jdpCreated) {
    this.jdpCreated = jdpCreated;
  }

  public LocalDateTime getJdpModified() {
    return this.jdpModified;
  }

  public void setJdpModified(LocalDateTime jdpModified) {
    this.jdpModified = jdpModified;
  }

  /**
   * 打印实体信息 ToString
   */
  public String toString() {
    return "{jdp_tb_refund:refundId=" + refundId + ",sellerNick=" + sellerNick + ",buyerNick="
        + buyerNick + ",status=" + status + ",created=" + created + ",tid=" + tid + ",oid=" + oid
        + ",modified=" + modified + ",jdpHashcode=" + jdpHashcode + ",jdpResponse=" + jdpResponse
        + ",jdpCreated=" + jdpCreated + ",jdpModified=" + jdpModified + "}";
  }
}