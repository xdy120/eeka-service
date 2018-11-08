package com.greatonce.oms.bo.stock;

/**
 * @author buer
 * @version 2018-01-06 16:49
 */
public class StockCheckResultBO{

    private Long skuId;
    private Long virtualWarehouseId;
    /**
     * 库存数
     */
    private Integer quantity;
    /**
     * 包含订单的占用数
     */
    private Integer lockQuantity;
    /**
     * 不含订单的占用数
     */
    private Integer lessLockQuantity;

    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public Long getVirtualWarehouseId(){
        return virtualWarehouseId;
    }

    public void setVirtualWarehouseId(Long virtualWarehouseId){
        this.virtualWarehouseId = virtualWarehouseId;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public Integer getLockQuantity(){
        return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity){
        this.lockQuantity = lockQuantity;
    }

    public Integer getLessLockQuantity(){
        return lessLockQuantity;
    }

    public void setLessLockQuantity(Integer lessLockQuantity){
        this.lessLockQuantity = lessLockQuantity;
    }
}
