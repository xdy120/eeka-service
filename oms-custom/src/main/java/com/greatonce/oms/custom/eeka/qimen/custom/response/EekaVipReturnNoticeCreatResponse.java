package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

public class EekaVipReturnNoticeCreatResponse extends OmsQimenCustomResponse {

  public EekaVipReturnNoticeCreatResponse(Long requestId) {
    super(requestId);
  }

  public EekaVipReturnNoticeCreatResponse(Long requestId,String message) {
    super(requestId,message);
  }


}
