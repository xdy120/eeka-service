package com.greatonce.oms.bo.report;

public class SalesOrderDeliverBO {
    private String storeName;
    private Integer orderQuantityTotal;
    private Double orderAmountTotal;
    private Integer orderNotDeliveryTotal;
    private Integer orderDeliveryTotal;
    private Integer productQuantity;
    private Integer notDeliveryQuantity;
    private Integer deliveryQuantity;
    private Double notDeliveryAmountTotal;
    private Double deliveryAmountTotal;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getOrderQuantityTotal() {
        return orderQuantityTotal;
    }

    public void setOrderQuantityTotal(Integer orderQuantityTotal) {
        this.orderQuantityTotal = orderQuantityTotal;
    }

    public Double getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(Double orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public Integer getOrderNotDeliveryTotal() {
        return orderNotDeliveryTotal;
    }

    public void setOrderNotDeliveryTotal(Integer orderNotDeliveryTotal) {
        this.orderNotDeliveryTotal = orderNotDeliveryTotal;
    }

    public Integer getOrderDeliveryTotal() {
        return orderDeliveryTotal;
    }

    public void setOrderDeliveryTotal(Integer orderDeliveryTotal) {
        this.orderDeliveryTotal = orderDeliveryTotal;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getNotDeliveryQuantity() {
        return notDeliveryQuantity;
    }

    public void setNotDeliveryQuantity(Integer notDeliveryQuantity) {
        this.notDeliveryQuantity = notDeliveryQuantity;
    }

    public Integer getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(Integer deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public Double getNotDeliveryAmountTotal() {
        return notDeliveryAmountTotal;
    }

    public void setNotDeliveryAmountTotal(Double notDeliveryAmountTotal) {
        this.notDeliveryAmountTotal = notDeliveryAmountTotal;
    }

    public Double getDeliveryAmountTotal() {
        return deliveryAmountTotal;
    }

    public void setDeliveryAmountTotal(Double deliveryAmountTotal) {
        this.deliveryAmountTotal = deliveryAmountTotal;
    }
}
