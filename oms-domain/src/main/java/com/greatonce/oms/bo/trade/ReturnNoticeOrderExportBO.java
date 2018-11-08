package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.trade.ReturnNoticeOrderStatus;

import java.time.LocalDateTime;

/**
 * @author Shenzhen cca
 * @version 2018/10/13
 */
public class ReturnNoticeOrderExportBO {

  private String returnNoticeOrderCode;
  private ReturnNoticeOrderStatus status;
  /**
   * 主单入库状态
   */
  private InStatus inStatus;
  private String warehouseName;
  private String boxNo;
  private String returnOrderCode;
  private String skuCode;
  private String skuName;
  /**
   * 明细 的通知数量
   */
  private Integer noticeQuantity;
  /**
   * 明细 的入库数量 (正品+次品)
   */
  private Integer inQuantity;
  /**
   * 主单 的最后入库时间
   */
  private LocalDateTime lastInTime;
  private String creator;
  /**
   * 明细 创建时间
   */
  private LocalDateTime createdTime;
  /**
   * 明细 修改时间
   */
  private LocalDateTime modifiedTime;


  public String getReturnNoticeOrderCode() {
    return returnNoticeOrderCode;
  }

  public void setReturnNoticeOrderCode(String returnNoticeOrderCode) {
    this.returnNoticeOrderCode = returnNoticeOrderCode;
  }

  public ReturnNoticeOrderStatus getStatus() {
    return status;
  }

  public void setStatus(ReturnNoticeOrderStatus status) {
    this.status = status;
  }

  public InStatus getInStatus() {
    return inStatus;
  }

  public void setInStatus(InStatus inStatus) {
    this.inStatus = inStatus;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public String getBoxNo() {
    return boxNo;
  }

  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo;
  }

  public String getReturnOrderCode() {
    return returnOrderCode;
  }

  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode;
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
