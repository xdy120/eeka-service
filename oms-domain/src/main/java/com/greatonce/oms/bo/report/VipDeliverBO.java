package com.greatonce.oms.bo.report;

import java.time.LocalDateTime;

public class VipDeliverBO {

    private String storeName;
    private String productCode;
    private String productName;
    private String skuCode;
    private String skuName;
    private Integer quantity;
    private String vipDispatchCode;
    private Double settlementAmount;
    private String expressName;
    private String expressNo;
    private Integer noticeQuantity;
    private Integer outQuantity;
    private LocalDateTime lastOutTime;


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getVipDispatchCode() {
        return vipDispatchCode;
    }

    public void setVipDispatchCode(String vipDispatchCode) {
        this.vipDispatchCode = vipDispatchCode;
    }

    public Double getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
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

    public Integer getNoticeQuantity() {
        return noticeQuantity;
    }

    public void setNoticeQuantity(Integer noticeQuantity) {
        this.noticeQuantity = noticeQuantity;
    }

    public Integer getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(Integer outQuantity) {
        this.outQuantity = outQuantity;
    }

    public LocalDateTime getLastOutTime() {
        return lastOutTime;
    }

    public void setLastOutTime(LocalDateTime lastOutTime) {
        this.lastOutTime = lastOutTime;
    }
}
