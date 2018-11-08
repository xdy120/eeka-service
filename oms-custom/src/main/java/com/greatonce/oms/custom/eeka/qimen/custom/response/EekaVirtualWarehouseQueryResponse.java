package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import java.util.List;

public class EekaVirtualWarehouseQueryResponse extends OmsQimenCustomResponse {

  public EekaVirtualWarehouseQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaVirtualWarehouseQueryResponse(Long requestId, String message) {
    super(requestId, message);
  }

  private List<VirtualWarehouse> virtualWarehouses;

  public List<VirtualWarehouse> getVirtualWarehouses() {
    return virtualWarehouses;
  }

  public void setVirtualWarehouses(
      List<VirtualWarehouse> virtualWarehouses) {
    this.virtualWarehouses = virtualWarehouses;
  }
}
