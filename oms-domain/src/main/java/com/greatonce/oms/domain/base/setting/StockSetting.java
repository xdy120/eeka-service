package com.greatonce.oms.domain.base.setting;

import java.io.Serializable;

/**
 * 库存配置.
 *
 * @author ginta
 */
public class StockSetting implements Serializable {

  /**
   * 构造方法.
   */
  public StockSetting() {
    this.virtualAllocationType = AllocationType.SOLD_OUT;
    this.deliveryAllocationType = AllocationType.SOLD_OUT;
    this.stockConfirmMethod = StockConfirmMethod.ORDER;
  }

  /**
   * 库存上传时数量小于等于.
   */
  private Integer onlyToShopMin;

  /**
   * 只传给指定店铺.
   */
  private String onlyToShop;
  /**
   * 只传给指定店铺名称.
   */
  private String onlyToShopName;

  /**
   * 虚拟调拨计算方式.
   */
  private AllocationType virtualAllocationType;

  /**
   * 出库类单据计算方式.
   */
  private AllocationType deliveryAllocationType;

  /**
   * 库存确认方式.
   */
  private StockConfirmMethod stockConfirmMethod;

  public Integer getOnlyToShopMin() {
    return onlyToShopMin;
  }

  public void setOnlyToShopMin(Integer onlyToShopMin) {
    this.onlyToShopMin = onlyToShopMin;
  }

  public String getOnlyToShop() {
    return onlyToShop;
  }

  public void setOnlyToShop(String onlyToShop) {
    this.onlyToShop = onlyToShop;
  }

  public String getOnlyToShopName() {
    return onlyToShopName;
  }

  public void setOnlyToShopName(String onlyToShopName) {
    this.onlyToShopName = onlyToShopName;
  }

  public AllocationType getVirtualAllocationType() {
    return virtualAllocationType;
  }

  public void setVirtualAllocationType(AllocationType virtualAllocationType) {
    this.virtualAllocationType = virtualAllocationType;
  }

  public AllocationType getDeliveryAllocationType() {
    return deliveryAllocationType;
  }

  public void setDeliveryAllocationType(AllocationType deliveryAllocationType) {
    this.deliveryAllocationType = deliveryAllocationType;
  }

  public StockConfirmMethod getStockConfirmMethod() {
    return stockConfirmMethod;
  }

  public void setStockConfirmMethod(StockConfirmMethod stockConfirmMethod) {
    this.stockConfirmMethod = stockConfirmMethod;
  }

  public static enum AllocationType {
    /**
     * 按照调出仓可销数计算.
     */
    SOLD_OUT,
    /**
     * 按照调出仓可用数计算.
     */
    AVAILABLE_BINS,
  }

  public enum StockConfirmMethod {
    /**
     * 接口同步.
     */
    INTERFACE,
    /**
     * 单据同步.
     */
    ORDER
  }

}
