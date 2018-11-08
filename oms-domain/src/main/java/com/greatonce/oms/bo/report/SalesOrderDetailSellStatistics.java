package com.greatonce.oms.bo.report;

/**
 * 销售单明细的销售统计
 * @author Administrator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/17
 */
public class SalesOrderDetailSellStatistics {

  private Long storeId;
  /**
   * 店铺名称
   */
  private String storeName;
  /**
   * 销售数量
   */
  private Integer salesQuantity;
  /**
   * 销售金额
   */
  private Double salesAmount;
  /**
   * 退货数量
   */
  private Integer returnQuantity;
  /**
   * 退货金额
   */
  private Double returnAmount;
  /**
   * 实际销售数量 = 销售数量 - 退货数量
   */
  private Integer actualQuantity;
  /**
   * 实际销售额 = 销售金额 - 应退金额
   */
  private Double actualAmount;

  private Double returnQuantityRatio;

  private Double returnAmountRatio;

  public String getStoreName() {
    return storeName;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public Integer getSalesQuantity() {
    return salesQuantity;
  }

  public void setSalesQuantity(Integer salesQuantity) {
    this.salesQuantity = salesQuantity;
  }

  public Double getSalesAmount() {
    return salesAmount;
  }

  public void setSalesAmount(Double salesAmount) {
    this.salesAmount = salesAmount;
  }

  public Integer getReturnQuantity() {
    return returnQuantity;
  }

  public void setReturnQuantity(Integer returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  public Double getReturnAmount() {
    return returnAmount;
  }

  public void setReturnAmount(Double returnAmount) {
    this.returnAmount = returnAmount;
  }

  public Integer getActualQuantity() {
    return actualQuantity;
  }

  public void setActualQuantity(Integer actualQuantity) {
    this.actualQuantity = actualQuantity;
  }

  public Double getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  public Double getReturnQuantityRatio() {
    return returnQuantityRatio;
  }

  public void setReturnQuantityRatio(Double returnQuantityRatio) {
    this.returnQuantityRatio = returnQuantityRatio;
  }

  public Double getReturnAmountRatio() {
    return returnAmountRatio;
  }

  public void setReturnAmountRatio(Double returnAmountRatio) {
    this.returnAmountRatio = returnAmountRatio;
  }
}
