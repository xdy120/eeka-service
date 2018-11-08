package com.greatonce.oms.bo.vip;

import com.greatonce.oms.domain.enums.vip.VipReturnStatus;
import com.greatonce.oms.domain.enums.vip.VipSignStatus;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/10/17
 */
public class VipReturnExportBO {

  private String vipReturnCode;
  /**
   * 退供单号
   */
  private String outerCode;
  private VipReturnStatus status;
  private VipSignStatus vipSignStatus;
  private String vipReturnType;
  private String storeName;
  /**
   * 唯品仓库
   */
  private String vipWarehouseName;
  /**
   * 退货仓库
   */
  private String virtualWarehouseName;
  private Integer boxQuantity;
  private String boxNumber;
  private String poCode;
  private String skuCode;
  private String skuName;
  private Double vipPrice;
  private Integer returnQuantity;
  private Integer scanQuantity;
  private Integer noticeQuantity;
  private Double vipAmount;
  private String creator;
  private LocalDateTime createdTime;
  private String signer;
  private LocalDateTime signTime;


  public String getVipReturnCode() {
    return vipReturnCode;
  }

  public void setVipReturnCode(String vipReturnCode) {
    this.vipReturnCode = vipReturnCode;
  }

  public String getOuterCode() {
    return outerCode;
  }

  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode;
  }

  public VipReturnStatus getStatus() {
    return status;
  }

  public void setStatus(VipReturnStatus status) {
    this.status = status;
  }

  public VipSignStatus getVipSignStatus() {
    return vipSignStatus;
  }

  public void setVipSignStatus(VipSignStatus vipSignStatus) {
    this.vipSignStatus = vipSignStatus;
  }

  public String getVipReturnType() {
    return vipReturnType;
  }

  public void setVipReturnType(String vipReturnType) {
    this.vipReturnType = vipReturnType;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getVipWarehouseName() {
    return vipWarehouseName;
  }

  public void setVipWarehouseName(String vipWarehouseName) {
    this.vipWarehouseName = vipWarehouseName;
  }

  public String getVirtualWarehouseName() {
    return virtualWarehouseName;
  }

  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName;
  }

  public Integer getBoxQuantity() {
    return boxQuantity;
  }

  public void setBoxQuantity(Integer boxQuantity) {
    this.boxQuantity = boxQuantity;
  }

  public String getBoxNumber() {
    return boxNumber;
  }

  public void setBoxNumber(String boxNumber) {
    this.boxNumber = boxNumber;
  }

  public String getPoCode() {
    return poCode;
  }

  public void setPoCode(String poCode) {
    this.poCode = poCode;
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

  public Integer getReturnQuantity() {
    return returnQuantity;
  }

  public void setReturnQuantity(Integer returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  public Integer getScanQuantity() {
    return scanQuantity;
  }

  public void setScanQuantity(Integer scanQuantity) {
    this.scanQuantity = scanQuantity;
  }

  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  public Double getVipAmount() {
    return vipAmount;
  }

  public void setVipAmount(Double vipAmount) {
    this.vipAmount = vipAmount;
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

  public String getSigner() {
    return signer;
  }

  public void setSigner(String signer) {
    this.signer = signer;
  }

  public LocalDateTime getSignTime() {
    return signTime;
  }

  public void setSignTime(LocalDateTime signTime) {
    this.signTime = signTime;
  }
}
