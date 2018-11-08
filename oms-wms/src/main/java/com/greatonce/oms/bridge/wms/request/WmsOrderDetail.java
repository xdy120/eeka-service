package com.greatonce.oms.bridge.wms.request;

import java.util.Map;

/**
 * WmsOrderDetail
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class WmsOrderDetail {

  /**
   * 商品编码
   */
  private String productCode;
  /**
   * 规格编码
   */
  private String skuCode;
  /**
   * 仓库规格ID
   */
  private String wmsSkuId;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 规格名称
   */
  private String skuName;
  /**
   * 应收商品数量
   */
  private int quantity;
  /**
   * 价格
   */
  private double price;
  /**
   * 金额
   */
  private double amount;
  /**
   * 扩展属性
   */
  private Map extendProps;

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public String getWmsSkuId() {
    return wmsSkuId;
  }

  public void setWmsSkuId(String wmsSkuId) {
    this.wmsSkuId = wmsSkuId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Map getExtendProps() {
    return extendProps;
  }

  public void setExtendProps(Map extendProps) {
    this.extendProps = extendProps;
  }
}
