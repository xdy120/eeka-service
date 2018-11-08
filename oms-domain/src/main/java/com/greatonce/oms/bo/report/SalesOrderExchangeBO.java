package com.greatonce.oms.bo.report;

import java.time.LocalDateTime;

public class SalesOrderExchangeBO {
    private LocalDateTime time;
    private String storeName;
    /*订单量*/
    private Integer quantity;
    /*未审单量*/
    private Integer notAtuditeQuantity;
    /*已审单量*/
    private Integer auditedQuantity;
    /*订单配货量*/
    private Integer dispatchQuantity;
    /*订单发货量*/
    private Integer deliverQuantity;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getNotAtuditeQuantity() {
        return notAtuditeQuantity;
    }

    public void setNotAtuditeQuantity(Integer notAtuditeQuantity) {
        this.notAtuditeQuantity = notAtuditeQuantity;
    }

    public Integer getAuditedQuantity() {
        return auditedQuantity;
    }

    public void setAuditedQuantity(Integer auditedQuantity) {
        this.auditedQuantity = auditedQuantity;
    }

    public Integer getDispatchQuantity() {
        return dispatchQuantity;
    }

    public void setDispatchQuantity(Integer dispatchQuantity) {
        this.dispatchQuantity = dispatchQuantity;
    }

    public Integer getDeliverQuantity() {
        return deliverQuantity;
    }

    public void setDeliverQuantity(Integer deliverQuantity) {
        this.deliverQuantity = deliverQuantity;
    }
}
