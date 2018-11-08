package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

public class EekaVipReturnSignResponse extends OmsQimenCustomResponse {

  public EekaVipReturnSignResponse(Long requestId) {
    super(requestId);
  }

  public EekaVipReturnSignResponse(Long requestId,String message) {
    super(requestId,message);
  }


}
