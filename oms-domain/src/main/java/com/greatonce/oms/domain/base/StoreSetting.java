package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.enums.DispatchMode;
import com.greatonce.oms.domain.enums.JingdongMode;
import com.greatonce.oms.domain.enums.product.ProductMappingType;
import com.greatonce.oms.domain.enums.trade.PayType;
import java.io.Serializable;

/**
 * 店铺设置..
 *
 * @author buer
 * @version 2017-11-15 14:47
 */
public class StoreSetting implements Serializable {

  /**
   * 默认仓库.
   */
  private Long defaultWarehouse;
  /**
   * 默认退入仓库名称.
   */
  private String defaultWarehouseName;
  /**
   * 默认退入仓库.
   */
  private Long defaultReturnWarehouse;
  /**
   * 默认退入仓库名称.
   */
  private String defaultReturnWarehouseName;
  /**
   * 审单策略.
   */
  private Long auditStrategy;
  /**
   * 审单策略名称.
   */
  private String auditStrategyName;
  /**
   * 库存策略.
   */
  private Long stockStrategy;
  /**
   * 库存策略名称.
   */
  private String stockStrategyName;
  /**
   * 配货方式.
   */
  private DispatchMode dispatchMode;
  /**
   * 铺货规则.
   */
  private ProductMappingType productMappingType;
  /**
   * 默认支付方式.
   */
  private PayType defaultPayType;
  /**
   * 默认币种.
   */
  private String defaultCurrencyCode;
  /**
   * 最大和单数.
   */
  private Integer maxMerge;
  /**
   * 配货延迟分钟.
   */
  private Integer delayMinutes;
  /**
   * 发货超时预警小时.
   */
  private Integer deliveryTimeoutWarning;
  /**
   * 是否推单到仓库.
   */
  private Boolean pushToWarehouse;
  /**
   * 预售有货先发.
   */
  private Boolean presellPriority;
  /**
   * 自动下载铺货.
   */
  private Boolean autoProductMapping;
  /**
   * 赠品是否单独发.
   */
  private Boolean onlyGiftShipping;
  /**
   * 自动开电子发票.
   */
  private Boolean autoInvoic;
  /**
   * 物流保价.
   */
  private Boolean logisticsPremium;
  /**
   * 换不同款.
   */
  private Boolean exchangeDifferentSpu;
  /**
   * 换货提前占用
   */
  private Boolean taobaoExchangeBeforeOccupancy;
  /**
   * 自动复核换货单.
   */
  private Boolean autoReviewExchangeOrder;
  /**
   * 淘系退货地址Id.
   */
  private String taobaoReturnAddressId;
  /**
   * 淘系退货地址.
   */
  private String taobaoReturnAddress;
  /**
   * 淘系退货留言.
   */
  private String taobaoReturnMemo;
  /**
   * 淘系是否启用AG.
   */
  private Boolean taobaoEnableAG;
  /**
   * 自动同意退款申请
   */
  private Boolean taobaoAutoAgreeRefund;
  /**
   * 淘系是否启用自动审核后台退款单.
   */
  private Boolean taobaoAutoAuditRefund;
  /**
   * 淘系是否启用自动审核后台换货单.
   */
  private Boolean taobaoAutoAuditExchange;
  /**
   * 淘宝订单云推.
   */
  private Boolean taobaoOrderCloudPush;
  /**
   * 淘宝商品云推.
   */
  private Boolean taobaoProductCloudPush;
  /**
   * 自动识别赠品.
   */
  private Boolean taobaoAutoGiftChecking;
  /**
   * 是否启用预付款订单.
   */
  private Boolean enablePrepayOrder;
  /**
   * 订单是否只用于记账（天猫国际、菜鸟发货、京东发货等）.
   */
  private Boolean onlyCharge;
  /**
   * 京东商家编码
   */
  private String vendorCode;
  /**
   * 京东商家名称
   */
  private String vendorName;
  /**
   * 京东店铺合作模式.
   */
  private JingdongMode jingdongMode;
  /**
   * 唯品常态合作编码.
   */
  private Integer vipCooperationNo;
  /**
   * 唯品供应商ID.
   */
  private Integer vipVendorId;

  public Long getDefaultWarehouse() {
    return defaultWarehouse;
  }

  public void setDefaultWarehouse(Long defaultWarehouse) {
    this.defaultWarehouse = defaultWarehouse;
  }

  public String getDefaultWarehouseName() {
    return defaultWarehouseName;
  }

  public void setDefaultWarehouseName(String defaultWarehouseName) {
    this.defaultWarehouseName = defaultWarehouseName;
  }

  public Long getDefaultReturnWarehouse() {
    return defaultReturnWarehouse;
  }

  public void setDefaultReturnWarehouse(Long defaultReturnWarehouse) {
    this.defaultReturnWarehouse = defaultReturnWarehouse;
  }

  public String getDefaultReturnWarehouseName() {
    return defaultReturnWarehouseName;
  }

  public void setDefaultReturnWarehouseName(String defaultReturnWarehouseName) {
    this.defaultReturnWarehouseName = defaultReturnWarehouseName;
  }

  public Long getAuditStrategy() {
    return auditStrategy;
  }

  public void setAuditStrategy(Long auditStrategy) {
    this.auditStrategy = auditStrategy;
  }

  public String getAuditStrategyName() {
    return auditStrategyName;
  }

  public void setAuditStrategyName(String auditStrategyName) {
    this.auditStrategyName = auditStrategyName;
  }

  public Long getStockStrategy() {
    return stockStrategy;
  }

  public void setStockStrategy(Long stockStrategy) {
    this.stockStrategy = stockStrategy;
  }

  public String getStockStrategyName() {
    return stockStrategyName;
  }

  public void setStockStrategyName(String stockStrategyName) {
    this.stockStrategyName = stockStrategyName;
  }

  public DispatchMode getDispatchMode() {
    return dispatchMode;
  }

  public void setDispatchMode(DispatchMode dispatchMode) {
    this.dispatchMode = dispatchMode;
  }

  public ProductMappingType getProductMappingType() {
    return productMappingType;
  }

  public void setProductMappingType(ProductMappingType productMappingType) {
    this.productMappingType = productMappingType;
  }

  public PayType getDefaultPayType() {
    return defaultPayType;
  }

  public void setDefaultPayType(PayType defaultPayType) {
    this.defaultPayType = defaultPayType;
  }

  public String getDefaultCurrencyCode() {
    return defaultCurrencyCode;
  }

  public void setDefaultCurrencyCode(String defaultCurrencyCode) {
    this.defaultCurrencyCode = defaultCurrencyCode;
  }

  public Integer getMaxMerge() {
    return maxMerge;
  }

  public void setMaxMerge(Integer maxMerge) {
    this.maxMerge = maxMerge;
  }

  public Integer getDeliveryTimeoutWarning() {
    return deliveryTimeoutWarning;
  }

  public void setDeliveryTimeoutWarning(Integer deliveryTimeoutWarning) {
    this.deliveryTimeoutWarning = deliveryTimeoutWarning;
  }

  public Boolean isPushToWarehouse() {
    return pushToWarehouse;
  }

  public void setPushToWarehouse(Boolean pushToWarehouse) {
    this.pushToWarehouse = pushToWarehouse;
  }

  public Boolean isPresellPriority() {
    return presellPriority;
  }

  public void setPresellPriority(Boolean presellPriority) {
    this.presellPriority = presellPriority;
  }

  public Boolean isAutoProductMapping() {
    return autoProductMapping;
  }

  public void setAutoProductMapping(Boolean autoProductMapping) {
    this.autoProductMapping = autoProductMapping;
  }

  public Boolean isAutoInvoic() {
    return autoInvoic;
  }

  public void setAutoInvoic(Boolean autoInvoic) {
    this.autoInvoic = autoInvoic;
  }

  public Boolean isLogisticsPremium() {
    return logisticsPremium;
  }

  public void setLogisticsPremium(Boolean logisticsPremium) {
    this.logisticsPremium = logisticsPremium;
  }

  public Boolean isExchangeDifferentSpu() {
    return exchangeDifferentSpu;
  }

  public void setExchangeDifferentSpu(Boolean exchangeDifferentSpu) {
    this.exchangeDifferentSpu = exchangeDifferentSpu;
  }

  public Boolean isAutoReviewExchangeOrder() {
    return autoReviewExchangeOrder;
  }

  public void setAutoReviewExchangeOrder(Boolean autoReviewExchangeOrder) {
    this.autoReviewExchangeOrder = autoReviewExchangeOrder;
  }

  public String getTaobaoReturnAddressId() {
    return taobaoReturnAddressId;
  }

  public void setTaobaoReturnAddressId(String taobaoReturnAddressId) {
    this.taobaoReturnAddressId = taobaoReturnAddressId;
  }

  public String getTaobaoReturnAddress() {
    return taobaoReturnAddress;
  }

  public void setTaobaoReturnAddress(String taobaoReturnAddress) {
    this.taobaoReturnAddress = taobaoReturnAddress;
  }

  public String getTaobaoReturnMemo() {
    return taobaoReturnMemo;
  }

  public void setTaobaoReturnMemo(String taobaoReturnMemo) {
    this.taobaoReturnMemo = taobaoReturnMemo;
  }

  public Boolean isTaobaoEnableAG() {
    return taobaoEnableAG;
  }

  public void setTaobaoEnableAG(Boolean taobaoEnableAG) {
    this.taobaoEnableAG = taobaoEnableAG;
  }

  public Boolean isTaobaoAutoAuditRefund() {
    return taobaoAutoAuditRefund;
  }

  public void setTaobaoAutoAuditRefund(Boolean taobaoAutoAuditRefund) {
    this.taobaoAutoAuditRefund = taobaoAutoAuditRefund;
  }

  public Boolean isTaobaoAutoAuditExchange() {
    return taobaoAutoAuditExchange;
  }

  public void setTaobaoAutoAuditExchange(Boolean taobaoAutoAuditExchange) {
    this.taobaoAutoAuditExchange = taobaoAutoAuditExchange;
  }

  public Boolean isTaobaoOrderCloudPush() {
    return taobaoOrderCloudPush;
  }

  public void setTaobaoOrderCloudPush(Boolean taobaoOrderCloudPush) {
    this.taobaoOrderCloudPush = taobaoOrderCloudPush;
  }

  public Boolean isTaobaoProductCloudPush() {
    return taobaoProductCloudPush;
  }

  public void setTaobaoProductCloudPush(Boolean taobaoProductCloudPush) {
    this.taobaoProductCloudPush = taobaoProductCloudPush;
  }

  public Boolean isEnablePrepayOrder() {
    return enablePrepayOrder;
  }

  public void setEnablePrepayOrder(Boolean enablePrepayOrder) {
    this.enablePrepayOrder = enablePrepayOrder;
  }

  public Boolean isOnlyCharge() {
    return onlyCharge;
  }

  public void setOnlyCharge(Boolean onlyCharge) {
    this.onlyCharge = onlyCharge;
  }

  public JingdongMode getJingdongMode() {
    return jingdongMode;
  }

  public void setJingdongMode(JingdongMode jingdongMode) {
    this.jingdongMode = jingdongMode;
  }

  public Integer getVipCooperationNo() {
    return vipCooperationNo;
  }

  public void setVipCooperationNo(Integer vipCooperationNo) {
    this.vipCooperationNo = vipCooperationNo;
  }

  public Integer getVipVendorId() {
    return vipVendorId;
  }

  public void setVipVendorId(Integer vipVendorId) {
    this.vipVendorId = vipVendorId;
  }

  public Integer getDelayMinutes() {
    return delayMinutes;
  }

  public void setDelayMinutes(Integer delayMinutes) {
    this.delayMinutes = delayMinutes;
  }

  public String getVendorCode() {
    return vendorCode;
  }

  public void setVendorCode(String vendorCode) {
    this.vendorCode = vendorCode;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public Boolean isTaobaoAutoGiftChecking() {
    return taobaoAutoGiftChecking;
  }

  public void setTaobaoAutoGiftChecking(Boolean taobaoAutoGiftChecking) {
    this.taobaoAutoGiftChecking = taobaoAutoGiftChecking;
  }

  public Boolean isTaobaoExchangeBeforeOccupancy() {
    return taobaoExchangeBeforeOccupancy;
  }

  public void setTaobaoExchangeBeforeOccupancy(Boolean taobaoExchangeBeforeOccupancy) {
    this.taobaoExchangeBeforeOccupancy = taobaoExchangeBeforeOccupancy;
  }

  public Boolean isTaobaoAutoAgreeRefund() {
    return taobaoAutoAgreeRefund;
  }

  public void setTaobaoAutoAgreeRefund(Boolean taobaoAutoAgreeRefund) {
    this.taobaoAutoAgreeRefund = taobaoAutoAgreeRefund;
  }

  public Boolean isOnlyGiftShipping() {
    return onlyGiftShipping;
  }

  public void setOnlyGiftShipping(Boolean onlyGiftShipping) {
    this.onlyGiftShipping = onlyGiftShipping;
  }
}
