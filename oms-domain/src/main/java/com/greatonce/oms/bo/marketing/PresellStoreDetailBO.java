package com.greatonce.oms.bo.marketing;

import com.greatonce.oms.domain.marketing.PresellStoreDetail;

/**
 * Created by wangc on 2018/1/9.
 * 预售计划明细自定义返回实体
 */
public class PresellStoreDetailBO extends PresellStoreDetail{
    private String storeName;
    private String productCode;
    private String productName;
    private String skuCode;
    private String skuName;
    private Integer quantity;

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
}
