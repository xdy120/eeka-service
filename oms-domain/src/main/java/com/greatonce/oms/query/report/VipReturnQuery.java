package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;
import java.util.List;

public class VipReturnQuery extends Query {
    private Long storeId;
    private LocalDateTime unpackedTimeBegin;
    private LocalDateTime unpackedTimeEnd;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public LocalDateTime getUnpackedTimeBegin() {
        return unpackedTimeBegin;
    }

    public void setUnpackedTimeBegin(LocalDateTime unpackedTimeBegin) {
        this.unpackedTimeBegin = unpackedTimeBegin;
    }

    public LocalDateTime getUnpackedTimeEnd() {
        return unpackedTimeEnd;
    }

    public void setUnpackedTimeEnd(LocalDateTime unpackedTimeEnd) {
        this.unpackedTimeEnd = unpackedTimeEnd;
    }
}
