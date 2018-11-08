package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

public class EekaReturnOrderResponse extends OmsQimenCustomResponse {

  public EekaReturnOrderResponse(Long requestId) {
    super(requestId);
  }

  public EekaReturnOrderResponse(Long requestId,String message) {
    super(requestId,message);
  }


}
