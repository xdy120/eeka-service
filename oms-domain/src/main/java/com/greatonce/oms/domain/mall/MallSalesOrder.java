package com.greatonce.oms.domain.mall;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商城订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class MallSalesOrder extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 商城订单id.
   */
  private Long mallSalesOrderId;
  /**
   * 商城状态.
   */
  private MallSalesOrderStatus mallSalesOrderStatus;
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
    this.mallSalesOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.mallSalesOrderId;
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
   * 商城订单id.
   */
  public void setMallSalesOrderId(Long mallSalesOrderId) {
    this.mallSalesOrderId = mallSalesOrderId;
  }

  /**
   * 商城订单id.
   */
  public Long getMallSalesOrderId() {
    return this.mallSalesOrderId;
  }

  /**
   * 商城状态.
   */
  public void setMallSalesOrderStatus(MallSalesOrderStatus mallSalesOrderStatus) {
    this.mallSalesOrderStatus = mallSalesOrderStatus;
  }

  /**
   * 商城状态.
   */
  public MallSalesOrderStatus getMallSalesOrderStatus() {
    return this.mallSalesOrderStatus;
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