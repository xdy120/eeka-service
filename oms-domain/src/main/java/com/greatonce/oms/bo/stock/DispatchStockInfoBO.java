package com.greatonce.oms.bo.stock;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/28
 */
public class DispatchStockInfoBO {
    private Long skuId;
    private Long virtualWarehouseId;
    private Integer quantity;
    private Integer lockQuantity;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getVirtualWarehouseId() {
        return virtualWarehouseId;
    }

    public void setVirtualWarehouseId(Long virtualWarehouseId) {
        this.virtualWarehouseId = virtualWarehouseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    @Override
    public String toString() {
        return "DispatchStockInfoBO{" +
                "skuId=" + skuId +
                ", virtualWarehouseId=" + virtualWarehouseId +
                ", quantity=" + quantity +
                ", lockQuantity=" + lockQuantity +
                '}';
    }
}
