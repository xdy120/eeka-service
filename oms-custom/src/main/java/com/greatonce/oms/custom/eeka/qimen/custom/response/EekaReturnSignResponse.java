package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

public class EekaReturnSignResponse extends OmsQimenCustomResponse {

  public EekaReturnSignResponse(Long requestId) {
    super(requestId);
  }

  public EekaReturnSignResponse(Long requestId,String message) {
    super(requestId,message);
  }


}
