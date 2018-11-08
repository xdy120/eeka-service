package com.greatonce.oms.bo.stock;

import java.util.List;

public class StockRatio {
    /** 数量 **/
    private Integer quantity;
    /** 在途数量 **/
    private Integer transitQuantity;
    /** 动销率 **/
    private Double ratio;
    /** 虚拟仓id **/
    private Long virtualWarehouseId;
    /** 虚拟仓名称 **/
    private String virtualWarehouseName;
    /** 实体仓id **/
    private Long warehouseId;
    /** 仓库名称 **/
    private String warehouseName;
    /** 商品规格id **/
    private Long skuId;
    /** 商品规格id **/
    private String skuCode;
    /** 商品规格名称**/
    private String skuName;
    /** 商品id **/
    private String productCode;
    /** 商品名称 **/
    private String productName;

    private List<StockRatio> stockRatios;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTransitQuantity() {
        return transitQuantity;
    }

    public void setTransitQuantity(Integer transitQuantity) {
        this.transitQuantity = transitQuantity;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Long getVirtualWarehouseId() {
        return virtualWarehouseId;
    }

    public void setVirtualWarehouseId(Long virtualWarehouseId) {
        this.virtualWarehouseId = virtualWarehouseId;
    }

    public String getVirtualWarehouseName() {
        return virtualWarehouseName;
    }

    public void setVirtualWarehouseName(String virtualWarehouseName) {
        this.virtualWarehouseName = virtualWarehouseName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
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

    public List<StockRatio> getStockRatios() {
        return stockRatios;
    }

    public void setStockRatios(List<StockRatio> stockRatios) {
        this.stockRatios = stockRatios;
    }
}
