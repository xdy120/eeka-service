package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain;

import java.time.LocalDateTime;

/**
 * jdp_tb_item
 *
 * @author code-generator
 */
public class JdpTbItem {

  private Long numIid;
  private String nick;
  private String approveStatus;
  private String hasShowcase;
  private LocalDateTime created;
  private LocalDateTime modified;
  private String cid;
  private String hasDiscount;
  private String jdpHashcode;
  private String jdpResponse;
  private Integer jdpDelete;
  private LocalDateTime jdpCreated;
  private LocalDateTime jdpModified;

  public Long getNumIid() {
    return this.numIid;
  }

  public void setNumIid(Long numIid) {
    this.numIid = numIid;
  }

  public String getNick() {
    return this.nick;
  }

  public void setNick(String nick) {
    this.nick = nick == null ? null : nick.trim();
  }

  public String getApproveStatus() {
    return this.approveStatus;
  }

  public void setApproveStatus(String approveStatus) {
    this.approveStatus = approveStatus == null ? null : approveStatus.trim();
  }

  public String getHasShowcase() {
    return this.hasShowcase;
  }

  public void setHasShowcase(String hasShowcase) {
    this.hasShowcase = hasShowcase == null ? null : hasShowcase.trim();
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

  public String getCid() {
    return this.cid;
  }

  public void setCid(String cid) {
    this.cid = cid == null ? null : cid.trim();
  }

  public String getHasDiscount() {
    return this.hasDiscount;
  }

  public void setHasDiscount(String hasDiscount) {
    this.hasDiscount = hasDiscount == null ? null : hasDiscount.trim();
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

  public Integer getJdpDelete() {
    return this.jdpDelete;
  }

  public void setJdpDelete(Integer jdpDelete) {
    this.jdpDelete = jdpDelete;
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
    return "{jdp_tb_item:numIid=" + numIid + ",nick=" + nick + ",approveStatus=" + approveStatus
        + ",hasShowcase=" + hasShowcase + ",created=" + created + ",modified=" + modified + ",cid="
        + cid + ",hasDiscount=" + hasDiscount + ",jdpHashcode=" + jdpHashcode + ",jdpResponse="
        + jdpResponse + ",jdpDelete=" + jdpDelete + ",jdpCreated=" + jdpCreated + ",jdpModified="
        + jdpModified + "}";
  }
}