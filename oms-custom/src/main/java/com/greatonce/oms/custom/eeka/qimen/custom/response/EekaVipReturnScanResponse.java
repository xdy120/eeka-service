package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

public class EekaVipReturnScanResponse extends OmsQimenCustomResponse {

  public EekaVipReturnScanResponse(Long requestId) {
    super(requestId);
  }

  public EekaVipReturnScanResponse(Long requestId,String message) {
    super(requestId,message);
  }


}
