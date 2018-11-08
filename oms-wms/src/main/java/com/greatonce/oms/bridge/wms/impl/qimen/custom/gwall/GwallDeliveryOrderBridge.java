package com.greatonce.oms.bridge.wms.impl.qimen.custom.gwall;

import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.impl.qimen.QimenDeliveryOrderBridge;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCancelResponse;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.request.DeliveryorderCreateRequest.DeliveryOrder;
import com.qimen.api.request.OrderCancelRequest;
import com.qimen.api.response.OrderCancelResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 巨沃发货单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class GwallDeliveryOrderBridge extends QimenDeliveryOrderBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_GWALL};
  }

  @Override
  protected DeliveryOrder createDeliveryOrder(DeliveryOrderCreateRequest request) {
    GwallDeliveryOrder deliveryOrder = new GwallDeliveryOrder();

    Map<String, String> map = new HashMap<>(4);
    map.put("key2", request.getConsolidationCode());
    map.put("key3", request.getConsolidationName());
    map.put("key1", request.getSortation());
    map.put("routeCode", request.getRouteCode());
    deliveryOrder.setExtendProps(map);
    return deliveryOrder;
  }

  @Override
  protected DeliveryOrderCancelResponse doCancelOrder(DeliveryOrderCancelRequest request) {
    try {
      OrderCancelRequest req = buildOrderCancelRequest(request);
      final OrderCancelResponse response = qimenWms.call(request.getWarehouse(), req, false);
      if (response.isSuccess() || response.getMessage().contains("订单已经取消")) {
        return new DeliveryOrderCancelResponse(request);
      } else {
        return new DeliveryOrderCancelResponse(request, false, response.getMessage());
      }
    } catch (WmsException e) {
      return new DeliveryOrderCancelResponse(request, false, e.getMessage());
    }
  }

  static class GwallDeliveryOrder extends DeliveryOrder {

    private Map extendProps;

    public Map getExtendProps() {
      return extendProps;
    }

    public void setExtendProps(Map extendProps) {
      this.extendProps = extendProps;
    }
  }
}
