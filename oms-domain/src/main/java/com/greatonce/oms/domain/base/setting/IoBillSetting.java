package com.greatonce.oms.domain.base.setting;

import java.io.Serializable;

/**
 * 出入库单据配置..
 */
public class IoBillSetting implements Serializable {

  /**
   * 采购订单审核时自动生成采购通知单.
   */
  private Boolean autoGenPurchaseNotice;

  /**
   * 采购退货必须关联原采购订单，否则不能保存.
   */
  private Boolean requireOriginOrder;

  /**
   * 唯品档期检查库存.
   */
  private Boolean vipScheduleCheckInventory;

  /**
   * 唯品价异常时拦截操作
   */
  private Boolean vipPriceAbnormalIntercept;

  /**
   * 唯品退货单生成唯品退货通知单时.
   */
  private NoticeOrderNumGranularity vipReturnNoticeOrderNum;

  /**
   * B2B退货单生成B2B退货通知单时.
   */
  private NoticeOrderNumGranularity priceMaintenanceGranularity;

  /**
   * 线下发货物流接口.
   */
  private String offlineDeliveryAppId;

  public Boolean isAutoGenPurchaseNotice() {
    return autoGenPurchaseNotice;
  }

  public void setAutoGenPurchaseNotice(Boolean autoGenPurchaseNotice) {
    this.autoGenPurchaseNotice = autoGenPurchaseNotice;
  }

  public Boolean isRequireOriginOrder() {
    return requireOriginOrder;
  }

  public void setRequireOriginOrder(Boolean requireOriginOrder) {
    this.requireOriginOrder = requireOriginOrder;
  }

  public Boolean isVipScheduleCheckInventory() {
    return vipScheduleCheckInventory;
  }

  public void setVipScheduleCheckInventory(Boolean vipScheduleCheckInventory) {
    this.vipScheduleCheckInventory = vipScheduleCheckInventory;
  }

  public Boolean isVipPriceAbnormalIntercept() {
    return vipPriceAbnormalIntercept;
  }

  public void setVipPriceAbnormalIntercept(Boolean vipPriceAbnormalIntercept) {
    this.vipPriceAbnormalIntercept = vipPriceAbnormalIntercept;
  }

  public NoticeOrderNumGranularity getVipReturnNoticeOrderNum() {
    return vipReturnNoticeOrderNum;
  }

  public void setVipReturnNoticeOrderNum(
      NoticeOrderNumGranularity vipReturnNoticeOrderNum) {
    this.vipReturnNoticeOrderNum = vipReturnNoticeOrderNum;
  }

  public NoticeOrderNumGranularity getPriceMaintenanceGranularity() {
    return priceMaintenanceGranularity;
  }

  public void setPriceMaintenanceGranularity(
      NoticeOrderNumGranularity priceMaintenanceGranularity) {
    this.priceMaintenanceGranularity = priceMaintenanceGranularity;
  }

  public String getOfflineDeliveryAppId() {
    return offlineDeliveryAppId;
  }

  public void setOfflineDeliveryAppId(String offlineDeliveryAppId) {
    this.offlineDeliveryAppId = offlineDeliveryAppId;
  }

  public enum NoticeOrderNumGranularity {
    RETURN_NUMBER, SCAN_NUMBER
  }
}
