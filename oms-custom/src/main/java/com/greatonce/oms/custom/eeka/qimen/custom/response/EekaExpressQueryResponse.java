package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.base.Express;
import java.util.List;

public class EekaExpressQueryResponse extends OmsQimenCustomResponse {

  public EekaExpressQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaExpressQueryResponse(Long requestId, String message) {
    super(requestId, message);
  }

  private List<Express> expresses;

  public List<Express> getExpresses() {
    return expresses;
  }

  public void setExpresses(List<Express> expresses) {
    this.expresses = expresses;
  }
}
