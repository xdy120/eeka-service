package com.greatonce.oms.bridge.wms.impl.qimen.custom.eeka;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.qimen.QimenDeliveryOrderBridge;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenDeliveryOrderType;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCreateResponse;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.response.OrderCancelResponse;
import org.springframework.stereotype.Component;

/**
 * 赢家发货单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class EekaDeliveryOrderBridge extends QimenDeliveryOrderBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }

  @Override
  protected DeliveryOrderCancelResponse doCancelOrder(DeliveryOrderCancelRequest request) {
    try {
      com.qimen.api.request.OrderCancelRequest req = new com.qimen.api.request.OrderCancelRequest();
      req.setCustomerId(request.getWarehouse().getWmsApp().getCustomerId());
      req.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
      req.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      req.setOrderCode(request.getOmsCode());
      req.setOrderId(request.getWmsCode());
      req.setOrderType(QimenDeliveryOrderType.JYCK.toString());
      req.setCancelReason(request.getReason());
      final OrderCancelResponse response = qimenWms.call(request.getWarehouse(), req);
      if ("order.process.async".equalsIgnoreCase(response.getCode())) {
        return new DeliveryOrderCancelResponse(request, true);
      }
      return new DeliveryOrderCancelResponse(request);
    } catch (WmsException e) {
      return new DeliveryOrderCancelResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected DeliveryOrderCreateResponse doCreateOrder(DeliveryOrderCreateRequest request) {
    //线下发货是没有仓库
    if (!Assert.isNull(request.getWarehouse().getWarehouseType())) {
      WmsUtil.putExtendProp(request, "warehouseType",
          request.getWarehouse().getWarehouseType().toString());
    }
    WmsUtil.putExtendProp(request, "provinceCode", request.getReceiverProvinceCode());
    WmsUtil.putExtendProp(request, "cityCode", request.getReceiverCityCode());
    WmsUtil.putExtendProp(request, "areaCode", request.getReceiverDistrictCode());
    WmsUtil.putExtendProp(request, "wmsCode", request.getWmsCode());
    if(!Assert.isEmpty(request.getExtendProps())){
      if(!Assert.isNull(request.getExtendProps().get("deliveryTime"))){
        WmsUtil.putExtendProp(request, "deliveryTime", request.getExtendProps().get("deliveryTime").toString());
      }
      if(!Assert.isNull(request.getExtendProps().get("createTime"))){
        WmsUtil.putExtendProp(request, "createTime", request.getExtendProps().get("createTime").toString());
      }
    }
    WmsUtil.putExtendProp(request, "mallPaidTime", DateTimeUtil.format(request.getMallPaidTime()));
    return super.doCreateOrder(request);
  }

}
