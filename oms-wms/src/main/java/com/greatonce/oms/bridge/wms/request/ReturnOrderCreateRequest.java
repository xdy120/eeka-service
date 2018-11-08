package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import java.util.Collection;

/**
 * 退货单创建请求
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class ReturnOrderCreateRequest extends WmsOrderRequest {

  public ReturnOrderCreateRequest(Warehouse warehouse) {
    super(warehouse);
  }

  /**
   * 店铺编码
   */
  private String storeCode;
  /**
   * 店铺名称
   */
  private String storeName;
  /**
   * 用字符串格式来表示订单标记列表(比如VISIT^ SELLER_AFFORD^SYNC_RETURN_BILL等;中间用“^”来隔开订单标记list (所 有字母全部大写) VISIT=上门；SELLER_AFFORD=是否卖家承担运费(默认是)SYNC_RETURN_BILL=同时退回发票)
   */
  private String orderFlag;
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
   * 买家昵称
   */
  private String buyerNick;
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
   * 单据明细
   */
  private Collection<ReturnOrderDetail> details;

  public String getStoreCode() {
    return storeCode;
  }

  public void setStoreCode(String storeCode) {
    this.storeCode = storeCode;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getOrderFlag() {
    return orderFlag;
  }

  public void setOrderFlag(String orderFlag) {
    this.orderFlag = orderFlag;
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

  public String getBuyerNick() {
    return buyerNick;
  }

  public void setBuyerNick(String buyerNick) {
    this.buyerNick = buyerNick;
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

  public Collection<ReturnOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(Collection<ReturnOrderDetail> details) {
    this.details = details;
  }

  /**
   * 单据明细
   */
  public static class ReturnOrderDetail extends WmsOrderDetail {

    /**
     * 交易平台单号
     */
    private String tradeId;
    /**
     * 交易子订单号
     */
    private String oid;

    public String getTradeId() {
      return tradeId;
    }

    public void setTradeId(String tradeId) {
      this.tradeId = tradeId;
    }

    public String getOid() {
      return oid;
    }

    public void setOid(String oid) {
      this.oid = oid;
    }
  }
}
