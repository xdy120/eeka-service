package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class VipDeliverQuery extends Query {
    private List<Long> storeIds;
    private LocalDate deliveryDateBegin;
    private LocalDate deliveryDateEnd;
    private LocalDateTime lastOutTimeBegin;
    private LocalDateTime lastOutTimeEnd;

    public List<Long> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Long> storeIds) {
        this.storeIds = storeIds;
    }

    public LocalDate getDeliveryDateBegin() {
        return deliveryDateBegin;
    }

    public void setDeliveryDateBegin(LocalDate deliveryDateBegin) {
        this.deliveryDateBegin = deliveryDateBegin;
    }

    public LocalDate getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(LocalDate deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    public LocalDateTime getLastOutTimeBegin() {
        return lastOutTimeBegin;
    }

    public void setLastOutTimeBegin(LocalDateTime lastOutTimeBegin) {
        this.lastOutTimeBegin = lastOutTimeBegin;
    }

    public LocalDateTime getLastOutTimeEnd() {
        return lastOutTimeEnd;
    }

    public void setLastOutTimeEnd(LocalDateTime lastOutTimeEnd) {
        this.lastOutTimeEnd = lastOutTimeEnd;
    }
}
