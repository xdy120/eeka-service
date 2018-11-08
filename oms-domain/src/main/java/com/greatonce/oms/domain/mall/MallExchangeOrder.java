package com.greatonce.oms.domain.mall;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.mall.MallExchangeStatus;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商城换货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class MallExchangeOrder extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 商城明细id.
   */
  private String mallDetailId;
  /**
   * 商城换货单id.
   */
  private String mallExchangeId;
  /**
   * 换货单id.
   */
  private Long mallExchangeOrderId;
  /**
   * 商城换货单状态.
   */
  private MallExchangeStatus mallExchangeStatus;
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
    this.mallExchangeOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.mallExchangeOrderId;
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
   * 商城明细id.
   */
  public void setMallDetailId(String mallDetailId) {
    this.mallDetailId = mallDetailId == null ? null : mallDetailId.trim();
  }

  /**
   * 商城明细id.
   */
  public String getMallDetailId() {
    return this.mallDetailId;
  }

  /**
   * 商城换货单id.
   */
  public void setMallExchangeId(String mallExchangeId) {
    this.mallExchangeId = mallExchangeId == null ? null : mallExchangeId.trim();
  }

  /**
   * 商城换货单id.
   */
  public String getMallExchangeId() {
    return this.mallExchangeId;
  }

  /**
   * 换货单id.
   */
  public void setMallExchangeOrderId(Long mallExchangeOrderId) {
    this.mallExchangeOrderId = mallExchangeOrderId;
  }

  /**
   * 换货单id.
   */
  public Long getMallExchangeOrderId() {
    return this.mallExchangeOrderId;
  }

  /**
   * 商城换货单状态.
   */
  public void setMallExchangeStatus(MallExchangeStatus mallExchangeStatus) {
    this.mallExchangeStatus = mallExchangeStatus;
  }

  /**
   * 商城换货单状态.
   */
  public MallExchangeStatus getMallExchangeStatus() {
    return this.mallExchangeStatus;
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