package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;
import com.greatonce.oms.domain.trade.ReturnSign;
import java.util.List;

public class EekaReturnSignRequest extends OmsQimenCustomRequest {


  private List<ReturnSign> returnSigns;

  public List<ReturnSign> getReturnSigns() {
    return returnSigns;
  }

  public void setReturnSigns(List<ReturnSign> returnSigns) {
    this.returnSigns = returnSigns;
  }

}
