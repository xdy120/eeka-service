package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.vip.VipReturn;

public class EekaVipReturnQueryResponse extends OmsQimenCustomResponse {

  public EekaVipReturnQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaVipReturnQueryResponse(Long requestId, String message) {
    super(requestId, message);
  }

  private VipReturn vipReturn;

  private Integer noticeQuantity;

  public VipReturn getVipReturn() {
    return vipReturn;
  }

  public void setVipReturn(VipReturn vipReturn) {
    this.vipReturn = vipReturn;
  }

  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }
}
