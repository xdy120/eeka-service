package com.greatonce.oms.message.stock;

import com.greatonce.oms.message.Message;

/**
 * 库存异动消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class StockChangedMessage extends Message {

  @Override
  public String routingKey() {
    return "oms.stock.quantity.change";
  }

  /**
   * 批次号.
   */
  private String batchId;
  /**
   * 异动规格ID.
   */
  private Long skuId;
  /**
   * 虚拟仓.
   */
  private Long virtualWarehouseId;
  /**
   * 店铺ID.
   */
  private Long storeId;
  /**
   * 操作人.
   */
  private String operator;
  /**
   * 异动原因.
   */
  private String reason;


  public StockChangedMessage(Long skuId, String operator, String reason) {
    this.skuId = skuId;
    this.operator = operator;
    this.reason = reason;
  }

  public StockChangedMessage(String batchId, Long skuId, String operator, String reason) {
    this.batchId = batchId;
    this.skuId = skuId;
    this.operator = operator;
    this.reason = reason;
  }

  public StockChangedMessage(String batchId, Long skuId, Long storeId, String operator,
      String reason) {
    this.batchId = batchId;
    this.skuId = skuId;
    this.storeId = storeId;
    this.operator = operator;
    this.reason = reason;
  }

  public StockChangedMessage(String batchId, Long skuId, Long virtualWarehouseId, Long storeId,
      String operator, String reason) {
    this.batchId = batchId;
    this.skuId = skuId;
    this.virtualWarehouseId = virtualWarehouseId;
    this.storeId = storeId;
    this.operator = operator;
    this.reason = reason;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return "StockChangedMessage{" + "batchId='" + batchId + '\''
        + ", skuId=" + skuId
        + ", storeId=" + storeId
        + ", reason='" + reason + '\''
        + '}';
  }
}
