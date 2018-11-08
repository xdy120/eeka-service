package com.greatonce.oms.bo.report;

public class SalesOrderOutBO {
    private String productCode;
    private String productName;
    private String skuCode;
    private String skuName;
    private Integer oosQuantity;
    private Integer preSellOosQuantity;

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

    public Integer getOosQuantity() {
        return oosQuantity;
    }

    public void setOosQuantity(Integer oosQuantity) {
        this.oosQuantity = oosQuantity;
    }

    public Integer getPreSellOosQuantity() {
        return preSellOosQuantity;
    }

    public void setPreSellOosQuantity(Integer preSellOosQuantity) {
        this.preSellOosQuantity = preSellOosQuantity;
    }
}
