package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain;

import java.time.LocalDateTime;

/**
 * jdp_tb_trade
 *
 * @author code-generator
 */
public class JdpTbTrade {

  private Long tid;
  /*
  状态
   */
  private String status;
  /*
  类型
   */
  private String type;
  /*
  卖家呢称
   */
  private String sellerNick;
  /*
  买家呢称
   */
  private String buyerNick;
  /*
  创建时间  ---不是订单创建时间  创建时间进去response
   */
  private LocalDateTime created;
  /*
  订单发生变化时间
   */
  private LocalDateTime modified;
  /*
  哈希值
   */
  private String jdpHashcode;
  /*
  返回值
   */
  private String jdpResponse;
  /*
    云推创建时间
   */
  private LocalDateTime jdpCreated;
  /*
  云推修改时间
   */
  private LocalDateTime jdpModified;

  public Long getTid() {
    return this.tid;
  }

  public void setTid(Long tid) {
    this.tid = tid;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type == null ? null : type.trim();
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
    return "{jdp_tb_trade:tid=" + tid + ",status=" + status + ",type=" + type + ",sellerNick="
        + sellerNick + ",buyerNick=" + buyerNick + ",created=" + created + ",modified=" + modified
        + ",jdpHashcode=" + jdpHashcode + ",jdpResponse=" + jdpResponse + ",jdpCreated="
        + jdpCreated + ",jdpModified=" + jdpModified + "}";
  }
}