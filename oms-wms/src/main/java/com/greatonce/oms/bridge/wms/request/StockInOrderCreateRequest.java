package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 入库单创建
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/27
 */
public class StockInOrderCreateRequest extends WmsOrderRequest {

  /**
   * 物流公司编码(SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通、ZTO=中通(ZTO)、HTKY=百世汇通、 UC=优速、STO=申通、TTKDEX=天天快递、QFKD=全峰、FAST=快捷、POSTB=邮政小包、GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、 AMAZON=亚马逊物流、OTHER=其他;只传英文编码)
   */
  private String expressCode;
  /**
   * 物流公司名称
   */
  private String expressName;
  /**
   * 运单号
   */
  private String expressNo;
  /**
   * 退回原因
   */
  private String reason;
  /**
   * 收件人*
   */
  private String receiverName;
  /**
   * 收件邮编
   */
  private String receiverZipCode;
  /**
   * 收件人电话*
   */
  private String receiverTelephone;
  /**
   * 收件人手机*
   */
  private String receiverMobile;
  /**
   * 收件人邮箱
   */
  private String receiverEmail;
  /**
   * 收件人国家*
   */
  private String receiverCountry;
  /**
   * 收件人省*
   */
  private String receiverProvince;
  /**
   * 收件人市*
   */
  private String receiverCity;
  /**
   * 收件人区*
   */
  private String receiverArea;
  /**
   * 收件人村镇
   */
  private String receiverTown;
  /**
   * 收件人地址*
   */
  private String receiverAddress;
  /**
   * 收件人备注
   */
  private String receiverRemark;
  /**
   * 发件人
   */
  private String senderName;
  /**
   * 发件人手机
   */
  private String senderMobile;
  /**
   * 发件人省
   */
  private String senderProvince;
  /**
   * 发件人市
   */
  private String senderCity;
  /**
   * 发件人区
   */
  private String senderCounty;
  /**
   * 发件人地址
   */
  private String senderAddress;
  /**
   * 发件人公司
   */
  private String senderCompany;
  /**
   * 发件人邮政编码
   */
  private String senderZipCode;
  /**
   * 发件人电话
   */
  private String senderTelephone;
  /**
   * 发件人邮箱
   */
  private String senderEmail;
  /**
   * 发件人国家编码
   */
  private String senderCountry;
  /**
   * 发件人村镇
   */
  private String senderTown;
  /**
   * 区域
   */
  private String senderArea;
  /**
   * 发件人备注
   */
  private String senderRemark;
  /**
   * 预期到货时间
   */
  private LocalDateTime expectStartTime;
  /**
   * 最迟预期到货时间
   */
  private LocalDateTime expectEndTime;
  /**
   * 店铺名称
   */
  private String storeName;
  /**
   * 店铺编码
   */
  private String storeCode;
  /**
   * 供应商编码
   */
  private String supplierCode;
  /**
   * 供应商名称
   */
  private String supplierName;
  /**
   * 来源单号
   */
  private String purchaseOrderCode;
  /**
   * 单据明细
   */
  private Collection<StockInOrderDetail> details;

  public StockInOrderCreateRequest(Warehouse warehouse) {
    super(warehouse);
  }

  public String getExpressCode() {
    return expressCode;
  }

  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public String getReceiverZipCode() {
    return receiverZipCode;
  }

  public void setReceiverZipCode(String receiverZipCode) {
    this.receiverZipCode = receiverZipCode;
  }

  public String getReceiverTelephone() {
    return receiverTelephone;
  }

  public void setReceiverTelephone(String receiverTelephone) {
    this.receiverTelephone = receiverTelephone;
  }

  public String getReceiverMobile() {
    return receiverMobile;
  }

  public void setReceiverMobile(String receiverMobile) {
    this.receiverMobile = receiverMobile;
  }

  public String getReceiverEmail() {
    return receiverEmail;
  }

  public void setReceiverEmail(String receiverEmail) {
    this.receiverEmail = receiverEmail;
  }

  public String getReceiverCountry() {
    return receiverCountry;
  }

  public void setReceiverCountry(String receiverCountry) {
    this.receiverCountry = receiverCountry;
  }

  public String getReceiverProvince() {
    return receiverProvince;
  }

  public void setReceiverProvince(String receiverProvince) {
    this.receiverProvince = receiverProvince;
  }

  public String getReceiverCity() {
    return receiverCity;
  }

  public void setReceiverCity(String receiverCity) {
    this.receiverCity = receiverCity;
  }

  public String getReceiverArea() {
    return receiverArea;
  }

  public void setReceiverArea(String receiverArea) {
    this.receiverArea = receiverArea;
  }

  public String getReceiverTown() {
    return receiverTown;
  }

  public void setReceiverTown(String receiverTown) {
    this.receiverTown = receiverTown;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public String getReceiverRemark() {
    return receiverRemark;
  }

  public void setReceiverRemark(String receiverRemark) {
    this.receiverRemark = receiverRemark;
  }

  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public String getSenderMobile() {
    return senderMobile;
  }

  public void setSenderMobile(String senderMobile) {
    this.senderMobile = senderMobile;
  }

  public String getSenderProvince() {
    return senderProvince;
  }

  public void setSenderProvince(String senderProvince) {
    this.senderProvince = senderProvince;
  }

  public String getSenderCity() {
    return senderCity;
  }

  public void setSenderCity(String senderCity) {
    this.senderCity = senderCity;
  }

  public String getSenderCounty() {
    return senderCounty;
  }

  public void setSenderCounty(String senderCounty) {
    this.senderCounty = senderCounty;
  }

  public String getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(String senderAddress) {
    this.senderAddress = senderAddress;
  }

  public String getSenderCompany() {
    return senderCompany;
  }

  public void setSenderCompany(String senderCompany) {
    this.senderCompany = senderCompany;
  }

  public String getSenderZipCode() {
    return senderZipCode;
  }

  public void setSenderZipCode(String senderZipCode) {
    this.senderZipCode = senderZipCode;
  }

  public String getSenderTelephone() {
    return senderTelephone;
  }

  public void setSenderTelephone(String senderTelephone) {
    this.senderTelephone = senderTelephone;
  }

  public String getSenderEmail() {
    return senderEmail;
  }

  public void setSenderEmail(String senderEmail) {
    this.senderEmail = senderEmail;
  }

  public String getSenderCountry() {
    return senderCountry;
  }

  public void setSenderCountry(String senderCountry) {
    this.senderCountry = senderCountry;
  }

  public String getSenderTown() {
    return senderTown;
  }

  public void setSenderTown(String senderTown) {
    this.senderTown = senderTown;
  }

  public String getSenderArea() {
    return senderArea;
  }

  public void setSenderArea(String senderArea) {
    this.senderArea = senderArea;
  }

  public String getSenderRemark() {
    return senderRemark;
  }

  public void setSenderRemark(String senderRemark) {
    this.senderRemark = senderRemark;
  }

  public LocalDateTime getExpectStartTime() {
    return expectStartTime;
  }

  public void setExpectStartTime(LocalDateTime expectStartTime) {
    this.expectStartTime = expectStartTime;
  }

  public LocalDateTime getExpectEndTime() {
    return expectEndTime;
  }

  public void setExpectEndTime(LocalDateTime expectEndTime) {
    this.expectEndTime = expectEndTime;
  }

  public String getSupplierCode() {
    return supplierCode;
  }

  public void setSupplierCode(String supplierCode) {
    this.supplierCode = supplierCode;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getStoreCode() {
    return storeCode;
  }

  public void setStoreCode(String storeCode) {
    this.storeCode = storeCode;
  }

  public String getPurchaseOrderCode() {
    return purchaseOrderCode;
  }

  public void setPurchaseOrderCode(String purchaseOrderCode) {
    this.purchaseOrderCode = purchaseOrderCode;
  }

  public Collection<StockInOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(Collection<StockInOrderDetail> details) {
    this.details = details;
  }

  /**
   * 单据明细
   */
  public static class StockInOrderDetail extends WmsOrderDetail {

    private double purchasePrice;
    private double sellingPrice;
    private double sellingAmount;

    public double getPurchasePrice() {
      return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
      this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
      return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
      this.sellingPrice = sellingPrice;
    }

    public double getSellingAmount() {
      return sellingAmount;
    }

    public void setSellingAmount(double sellingAmount) {
      this.sellingAmount = sellingAmount;
    }
  }
}
