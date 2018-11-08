package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;

import java.time.LocalDateTime;

public class SalesOrderExchangeQuery extends Query {
    private Long storeId;
    private LocalDateTime createTime;
    private LocalDateTime auditedTime;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getAuditedTime() {
        return auditedTime;
    }

    public void setAuditedTime(LocalDateTime auditedTime) {
        this.auditedTime = auditedTime;
    }
}
