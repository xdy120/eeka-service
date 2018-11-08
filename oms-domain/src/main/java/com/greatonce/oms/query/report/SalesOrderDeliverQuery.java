package com.greatonce.oms.query.report;

import com.greatonce.core.database.Query;
import java.time.LocalDateTime;
import java.util.List;

public class SalesOrderDeliverQuery extends Query {
    private List<Long> storeIds;
    private LocalDateTime mallPaidTimeBegin;
    private LocalDateTime mallPaidTimeEnd;

    public List<Long> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Long> storeIds) {
        this.storeIds = storeIds;
    }

    public LocalDateTime getMallPaidTimeBegin() {
        return mallPaidTimeBegin;
    }

    public void setMallPaidTimeBegin(LocalDateTime mallPaidTimeBegin) {
        this.mallPaidTimeBegin = mallPaidTimeBegin;
    }

    public LocalDateTime getMallPaidTimeEnd() {
        return mallPaidTimeEnd;
    }

    public void setMallPaidTimeEnd(LocalDateTime mallPaidTimeEnd) {
        this.mallPaidTimeEnd = mallPaidTimeEnd;
    }
}
