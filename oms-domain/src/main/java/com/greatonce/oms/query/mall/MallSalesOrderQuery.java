package com.greatonce.oms.query.mall;

import com.greatonce.core.database.Query;
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
public class MallSalesOrderQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 商城订单id.
   */
  private Long mallSalesOrderId;
  /**
   * 商城状态.
   */
  private MallSalesOrderStatus mallSalesOrderStatus;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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


  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 商城订单id.
   * @param mallSalesOrderId 商城订单id
   */
  public void setMallSalesOrderId(Long mallSalesOrderId) {
    this.mallSalesOrderId = mallSalesOrderId;
  }

  /**
   * 商城订单id.
   * @return 商城订单id
   */
  public Long getMallSalesOrderId() {
      return this.mallSalesOrderId;
  }

  /**
   * 商城状态.
   * @param mallSalesOrderStatus 商城状态
   */
  public void setMallSalesOrderStatus(MallSalesOrderStatus mallSalesOrderStatus) {
    this.mallSalesOrderStatus = mallSalesOrderStatus;
  }

  /**
   * 商城状态.
   * @return 商城状态
   */
  public MallSalesOrderStatus getMallSalesOrderStatus() {
      return this.mallSalesOrderStatus;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * json数据.
   * @param orderJson json数据
   */
  public void setOrderJson(String orderJson) {
    this.orderJson = orderJson == null ? null : orderJson.trim();
  }

  /**
   * json数据.
   * @return json数据
   */
  public String getOrderJson() {
      return this.orderJson;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(MallDataProcessStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public MallDataProcessStatus getStatus() {
      return this.status;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }

  /**
   * 交易号.
   * @param tradeId 交易号
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   * @return 交易号
   */
  public String getTradeId() {
      return this.tradeId;
  }
}