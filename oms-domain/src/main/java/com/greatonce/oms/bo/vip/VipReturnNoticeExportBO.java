package com.greatonce.oms.bo.vip;

import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.vip.VipReturnNoticeStatus;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/10/17
 */
public class VipReturnNoticeExportBO {

  private String vipReturnNoticeCode;
  private String vipReturnCode;
  private VipReturnNoticeStatus status;
  private InStatus inStatus;
  private String storeName;
  private String warehouseName;
  private String skuCode;
  private String skuName;
  private Double vipPrice;
  private Integer noticeQuantity;
  /**
   * 正品入库 + 次品入库
   */
  private Integer inQuantity;
  private Double vipAmount;
  private LocalDateTime lastInTime;
  private String creator;
  private LocalDateTime createdTime;
  private LocalDateTime modifiedTime;

  public String getVipReturnNoticeCode() {
    return vipReturnNoticeCode;
  }

  public void setVipReturnNoticeCode(String vipReturnNoticeCode) {
    this.vipReturnNoticeCode = vipReturnNoticeCode;
  }

  public String getVipReturnCode() {
    return vipReturnCode;
  }

  public void setVipReturnCode(String vipReturnCode) {
    this.vipReturnCode = vipReturnCode;
  }

  public VipReturnNoticeStatus getStatus() {
    return status;
  }

  public void setStatus(VipReturnNoticeStatus status) {
    this.status = status;
  }

  public InStatus getInStatus() {
    return inStatus;
  }

  public void setInStatus(InStatus inStatus) {
    this.inStatus = inStatus;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public Double getVipPrice() {
    return vipPrice;
  }

  public void setVipPrice(Double vipPrice) {
    this.vipPrice = vipPrice;
  }

  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  public Integer getInQuantity() {
    return inQuantity;
  }

  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  public Double getVipAmount() {
    return vipAmount;
  }

  public void setVipAmount(Double vipAmount) {
    this.vipAmount = vipAmount;
  }

  public LocalDateTime getLastInTime() {
    return lastInTime;
  }

  public void setLastInTime(LocalDateTime lastInTime) {
    this.lastInTime = lastInTime;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}
