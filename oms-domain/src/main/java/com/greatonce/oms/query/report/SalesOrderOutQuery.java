package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;

public class SalesOrderOutQuery extends Query {
    private String productCode;
    private String skuCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
}
