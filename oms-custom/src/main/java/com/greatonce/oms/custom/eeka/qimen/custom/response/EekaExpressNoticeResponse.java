package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;

/**
 * @author Lcc
 * @version 2018/7/3 18:28
 */
public class EekaExpressNoticeResponse extends OmsQimenCustomResponse {

  public EekaExpressNoticeResponse(long requestId) {
    super(requestId);
  }

  public EekaExpressNoticeResponse(long requestId, String message) {
    super(requestId, message);
  }
}
