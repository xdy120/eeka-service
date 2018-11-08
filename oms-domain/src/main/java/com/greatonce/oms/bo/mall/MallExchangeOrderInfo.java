package com.greatonce.oms.bo.mall;

import java.time.LocalDateTime;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/30
 */
public class MallExchangeOrderInfo {

    /**
     * 退款号
     */
    private String exchangeId;
    /**
     * 平台子订单号
     */
    private String oid;
    /**
     * 买家昵称
     */
    private String buyerNick;
    /**
     * 卖家昵称
     */
    private String sellerNick;
    /**
     * 更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 当前换货状态
     */
    private String exchangeStatus;
    /**
     * 原因
     */
    private String reason;
    /**
     * 描述
     */
    private String description;
    /**
     * 支付号
     */
    private String payNo;
    /**
     * 退回快递单号
     */
    private String buyerExpressNo;
    /**
     * 退回快递名称
     */
    private String buyerExpressName;
    /**
     * 换出快递单号
     */
    private String sellerExpressNo;
    /**
     * 换出快递名称
     */
    private String sellerExpressName;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 退款版本
     */
    private String version;
    /**
     * 平台退入商品ID
     */
    private String inMallProductId;
    /**
     * 平台退入商品名称
     */
    private String inMallProductName;
    /**
     * 外部退入商品编码
     */
    private String inOuterCode;
    /**
     * 平台退入Sku信息
     */
    private String inMallSkuId;
    /**
     * 平台退入Sku信息
     */
    private String inMallSkuName;
    /**
     * 外部退入规格编码
     */
    private String inOuterSkuCode;
    /**
     * 平台换出商品ID
     */
    private String outMallProductId;
    /**
     * N
     * 平台换出商品名称
     */
    private String outMallProductame;
    /**
     * 外部换出商品编码
     */
    private String outOuterCode;
    /**
     * 平台换出Sku信息
     */
    private String outMallSkuId;
    /**
     * 平台换出Sku信息
     */
    private String outMallSkuName;
    /**
     * 外部换出规格编码
     */
    private String outOuterSkuCode;
    /**
     * 换出数量
     */
    private Integer quantity;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getBuyerExpressNo() {
        return buyerExpressNo;
    }

    public void setBuyerExpressNo(String buyerExpressNo) {
        this.buyerExpressNo = buyerExpressNo;
    }

    public String getBuyerExpressName() {
        return buyerExpressName;
    }

    public void setBuyerExpressName(String buyerExpressName) {
        this.buyerExpressName = buyerExpressName;
    }

    public String getSellerExpressNo() {
        return sellerExpressNo;
    }

    public void setSellerExpressNo(String sellerExpressNo) {
        this.sellerExpressNo = sellerExpressNo;
    }

    public String getSellerExpressName() {
        return sellerExpressName;
    }

    public void setSellerExpressName(String sellerExpressName) {
        this.sellerExpressName = sellerExpressName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInMallProductId() {
        return inMallProductId;
    }

    public void setInMallProductId(String inMallProductId) {
        this.inMallProductId = inMallProductId;
    }

    public String getInMallProductName() {
        return inMallProductName;
    }

    public void setInMallProductName(String inMallProductName) {
        this.inMallProductName = inMallProductName;
    }

    public String getInOuterCode() {
        return inOuterCode;
    }

    public void setInOuterCode(String inOuterCode) {
        this.inOuterCode = inOuterCode;
    }

    public String getInMallSkuId() {
        return inMallSkuId;
    }

    public void setInMallSkuId(String inMallSkuId) {
        this.inMallSkuId = inMallSkuId;
    }

    public String getInMallSkuName() {
        return inMallSkuName;
    }

    public void setInMallSkuName(String inMallSkuName) {
        this.inMallSkuName = inMallSkuName;
    }

    public String getInOuterSkuCode() {
        return inOuterSkuCode;
    }

    public void setInOuterSkuCode(String inOuterSkuCode) {
        this.inOuterSkuCode = inOuterSkuCode;
    }

    public String getOutMallProductId() {
        return outMallProductId;
    }

    public void setOutMallProductId(String outMallProductId) {
        this.outMallProductId = outMallProductId;
    }

    public String getOutMallProductame() {
        return outMallProductame;
    }

    public void setOutMallProductame(String outMallProductame) {
        this.outMallProductame = outMallProductame;
    }

    public String getOutOuterCode() {
        return outOuterCode;
    }

    public void setOutOuterCode(String outOuterCode) {
        this.outOuterCode = outOuterCode;
    }

    public String getOutMallSkuId() {
        return outMallSkuId;
    }

    public void setOutMallSkuId(String outMallSkuId) {
        this.outMallSkuId = outMallSkuId;
    }

    public String getOutMallSkuName() {
        return outMallSkuName;
    }

    public void setOutMallSkuName(String outMallSkuName) {
        this.outMallSkuName = outMallSkuName;
    }

    public String getOutOuterSkuCode() {
        return outOuterSkuCode;
    }

    public void setOutOuterSkuCode(String outOuterSkuCode) {
        this.outOuterSkuCode = outOuterSkuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
