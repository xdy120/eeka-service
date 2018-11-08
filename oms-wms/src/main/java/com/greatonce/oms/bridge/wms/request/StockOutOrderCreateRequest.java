package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import java.util.Collection;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
public class StockOutOrderCreateRequest extends WmsOrderRequest {

  public StockOutOrderCreateRequest(Warehouse warehouse) {
    super(warehouse);
  }

  /**
   * 要求出库时间
   */
  private String scheduleDate;
  /**
   * 物流公司编码(SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通 、ZTO=中通(ZTO)、HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递、QFKD=全峰、FAST=快捷、POSTB=邮政小包、GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、AMAZON=亚马逊物流、OTHER=其他;只传英文编码)
   */
  private String expressCode;
  /**
   * 物流公司名称(包括干线物流公司等)
   */
  private String expressName;
  /**
   * 运单号
   */
  private String expressNo;
  /**
   * 供应商编码
   */
  private String supplierCode;
  /**
   * 供应商名称
   */
  private String supplierName;
  /**
   * 提货方式(到仓自提、快递、干线物流)
   */
  private String transportMode;
  /**
   * 证件号
   */
  private String receiverId;
  /**
   * 收件人证件类型(1-身份证、2-军官证、3-护照、4-其他)
   */
  private String receiverIdType;
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
  private String senderArea;
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
   * 发件人备注
   */
  private String senderRemark;
  /**
   * 店铺名称
   */
  private String storeName;
  /**
   * 店铺编码
   */
  private String storeCode;
  /**
   * 品牌编码
   */
  private String brandCode;
  /**
   * 品牌名称
   */
  private String brandName;
  /**
   * 单据明细
   */
  private Collection<StockOutOrderDetail> details;

  public String getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(String scheduleDate) {
    this.scheduleDate = scheduleDate;
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

  public String getTransportMode() {
    return transportMode;
  }

  public void setTransportMode(String transportMode) {
    this.transportMode = transportMode;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(String receiverId) {
    this.receiverId = receiverId;
  }

  public String getReceiverIdType() {
    return receiverIdType;
  }

  public void setReceiverIdType(String receiverIdType) {
    this.receiverIdType = receiverIdType;
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

  public String getSenderArea() {
    return senderArea;
  }

  public void setSenderArea(String senderArea) {
    this.senderArea = senderArea;
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

  public String getSenderRemark() {
    return senderRemark;
  }

  public void setSenderRemark(String senderRemark) {
    this.senderRemark = senderRemark;
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

  public String getBrandCode() {
    return brandCode;
  }

  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public Collection<StockOutOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(Collection<StockOutOrderDetail> details) {
    this.details = details;
  }

  public static class StockOutOrderDetail extends WmsOrderDetail {

    private Double sellingPrice;
    private Double sellingAmount;

    public Double getSellingPrice() {
      return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
      this.sellingPrice = sellingPrice;
    }

    public Double getSellingAmount() {
      return sellingAmount;
    }

    public void setSellingAmount(Double sellingAmount) {
      this.sellingAmount = sellingAmount;
    }
  }
}
