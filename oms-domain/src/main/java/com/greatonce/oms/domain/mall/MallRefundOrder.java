package com.greatonce.oms.domain.mall;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商城退单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class MallRefundOrder extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 平台退款单号.
   */
  private String mallRefundId;
  /**
   * 商城售后申请id.
   */
  private Long mallRefundOrderId;
  /**
   * 商城售后申请状态.
   */
  private MallRefundStatus mallRefundStatus;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * json数据.
   */
  private String orderJson;
  /**
   * 状态.
   */
  private MallDataProcessStatus status;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 交易号.
   */
  private String tradeId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.mallRefundOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.mallRefundOrderId;
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
   * 平台退款单号.
   */
  public void setMallRefundId(String mallRefundId) {
    this.mallRefundId = mallRefundId == null ? null : mallRefundId.trim();
  }

  /**
   * 平台退款单号.
   */
  public String getMallRefundId() {
    return this.mallRefundId;
  }

  /**
   * 商城售后申请id.
   */
  public void setMallRefundOrderId(Long mallRefundOrderId) {
    this.mallRefundOrderId = mallRefundOrderId;
  }

  /**
   * 商城售后申请id.
   */
  public Long getMallRefundOrderId() {
    return this.mallRefundOrderId;
  }

  /**
   * 商城售后申请状态.
   */
  public void setMallRefundStatus(MallRefundStatus mallRefundStatus) {
    this.mallRefundStatus = mallRefundStatus;
  }

  /**
   * 商城售后申请状态.
   */
  public MallRefundStatus getMallRefundStatus() {
    return this.mallRefundStatus;
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
   * json数据.
   */
  public void setOrderJson(String orderJson) {
    this.orderJson = orderJson == null ? null : orderJson.trim();
  }

  /**
   * json数据.
   */
  public String getOrderJson() {
    return this.orderJson;
  }

  /**
   * 状态.
   */
  public void setStatus(MallDataProcessStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public MallDataProcessStatus getStatus() {
    return this.status;
  }

  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }

  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 交易号.
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   */
  public String getTradeId() {
    return this.tradeId;
  }
}