package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

public class EekaDispatchOrderCancelResponse extends OmsQimenCustomResponse {

  public EekaDispatchOrderCancelResponse(Long requestId) {
    super(requestId);
  }

  public EekaDispatchOrderCancelResponse(Long requestId, String message) {
    super(requestId, message);
  }
}
