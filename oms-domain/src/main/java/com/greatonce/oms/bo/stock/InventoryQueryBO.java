package com.greatonce.oms.bo.stock;

import java.util.List;

/**
 * Created by wangcf on 2018/4/16.
 */
public class InventoryQueryBO {
    private List<String> skuCodes;
    private List<String> wmsSkuIds;

    public List<String> getSkuCodes() {
        return skuCodes;
    }

    public void setSkuCodes(List<String> skuCodes) {
        this.skuCodes = skuCodes;
    }

    public List<String> getWmsSkuIds() {
        return wmsSkuIds;
    }

    public void setWmsSkuIds(List<String> wmsSkuIds) {
        this.wmsSkuIds = wmsSkuIds;
    }
}
