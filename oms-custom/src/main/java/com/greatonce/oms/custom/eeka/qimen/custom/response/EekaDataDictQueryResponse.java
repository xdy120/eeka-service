package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.base.DataDictItem;
import java.util.List;

public class EekaDataDictQueryResponse extends OmsQimenCustomResponse {

  public EekaDataDictQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaDataDictQueryResponse(Long requestId, String message) {
    super(requestId, message);
  }

  private List<DataDictItem> dataDictItems;

  public List<DataDictItem> getDataDictItems() {
    return dataDictItems;
  }

  public void setDataDictItems(List<DataDictItem> dataDictItems) {
    this.dataDictItems = dataDictItems;
  }
}
