package com.greatonce.oms.bo.stock;

import com.greatonce.oms.domain.stock.Stock;

/**
 * 库存查询BO.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/7
 */
public class StockQueryBO extends Stock {

  private String productCode;
  private String skuName;
  private Integer salesOrderLockQuantity;
  private Integer dispatchOrderLockQuantity;
  private Integer returnOrderLockQuantity;
  private Integer otherLockQuantity;
  private Integer vipLockQuantity;
  private Integer available;
  private Integer saleable;
  private String productName;

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public Integer getSalesOrderLockQuantity() {
    return salesOrderLockQuantity;
  }

  public void setSalesOrderLockQuantity(Integer salesOrderLockQuantity) {
    this.salesOrderLockQuantity = salesOrderLockQuantity;
  }

  public Integer getDispatchOrderLockQuantity() {
    return dispatchOrderLockQuantity;
  }

  public void setDispatchOrderLockQuantity(Integer dispatchOrderLockQuantity) {
    this.dispatchOrderLockQuantity = dispatchOrderLockQuantity;
  }

  public Integer getReturnOrderLockQuantity() {
    return returnOrderLockQuantity;
  }

  public void setReturnOrderLockQuantity(Integer returnOrderLockQuantity) {
    this.returnOrderLockQuantity = returnOrderLockQuantity;
  }

  public Integer getOtherLockQuantity() {
    return otherLockQuantity;
  }

  public void setOtherLockQuantity(Integer otherLockQuantity) {
    this.otherLockQuantity = otherLockQuantity;
  }

  public Integer getVipLockQuantity() {
    return vipLockQuantity;
  }

  public void setVipLockQuantity(Integer vipLockQuantity) {
    this.vipLockQuantity = vipLockQuantity;
  }

  public Integer getAvailable() {
    return available;
  }

  public void setAvailable(Integer available) {
    this.available = available;
  }

  public Integer getSaleable() {
    return saleable;
  }

  public void setSaleable(Integer saleable) {
    this.saleable = saleable;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }
}
