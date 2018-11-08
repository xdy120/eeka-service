package com.greatonce.oms.bridge.wms.impl.qimen.custom.eeka;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.qimen.QimenStockOutOrderBridge;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.VipDeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.domain.enums.WmsType;
import org.springframework.stereotype.Component;

/**
 * 赢家出库单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class EekaStockOutOrderBridge extends QimenStockOutOrderBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }

  @Override
  protected StockOutOrderCreateResponse doCreateOrder(StockOutOrderCreateRequest request) {
    WmsUtil.putExtendProp(request, "warehouseType",
        request.getWarehouse().getWarehouseType().toString());
    WmsUtil.putExtendProp(request, "shopCode", request.getStoreCode());
    WmsUtil.putExtendProp(request, "shopName", request.getStoreName());
    if (request instanceof VipDeliveryOrderCreateRequest) {
      VipDeliveryOrderCreateRequest vipRequest = (VipDeliveryOrderCreateRequest) request;
      WmsUtil.putExtendProp(request, "vipArrivalTime", vipRequest.getVipArrivalTime());
      WmsUtil.putExtendProp(request, "vipWaybillNumber", vipRequest.getExpressNo());
      WmsUtil.putExtendProp(request, "vipPickingCode", vipRequest.getVipPickingCode());
      WmsUtil.putExtendProp(request, "vipStorageNo", vipRequest.getVipStorageNo());
      WmsUtil.putExtendProp(request, "vipCarrierName", vipRequest.getVipCarrierName());
      WmsUtil.putExtendProp(request, "vipDeliveryMethod", vipRequest.getVipDeliveryMethod());
      WmsUtil.putExtendProp(request, "vipBrandCode", vipRequest.getBrandCode());
      WmsUtil.putExtendProp(request, "vipBrandName", vipRequest.getBrandName());
      WmsUtil.putExtendProp(request, "vipWarehouseCode", vipRequest.getVipWarehouseCode());
      WmsUtil.putExtendProp(request, "vipWarehouseName", vipRequest.getVipWarehouseName());
    }
    WmsUtil.putExtendProp(request, "wmsCode", request.getWmsCode());
    if(!Assert.isEmpty(request.getExtendProps())){
      if(!Assert.isNull(request.getExtendProps().get("lastInTime"))){
        WmsUtil.putExtendProp(request, "lastOutTime", request.getExtendProps().get("lastOutTime").toString());
      }
      if(!Assert.isNull(request.getExtendProps().get("createTime"))){
        WmsUtil.putExtendProp(request, "createTime", request.getExtendProps().get("createTime").toString());
      }
    }
    return super.doCreateOrder(request);
  }

}
