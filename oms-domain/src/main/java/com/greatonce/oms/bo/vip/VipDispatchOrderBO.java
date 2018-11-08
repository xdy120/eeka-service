package com.greatonce.oms.bo.vip;

import com.greatonce.oms.domain.vip.VipDispatch;

public class VipDispatchOrderBO extends VipDispatch{
    private Integer noticeQuantity;
    private Integer outQuantity;

    public Integer getNoticeQuantity() {
        return noticeQuantity;
    }

    public void setNoticeQuantity(Integer noticeQuantity) {
        this.noticeQuantity = noticeQuantity;
    }

    public Integer getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(Integer outQuantity) {
        this.outQuantity = outQuantity;
    }
}
