package com.greatonce.oms.bridge.wms.impl.qimen.custom.eeka;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.qimen.QimenStockInOrderBridge;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.domain.enums.WmsType;
import org.springframework.stereotype.Component;

/**
 * 赢家入库单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class EekaStockInOrderBridge extends QimenStockInOrderBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }

  @Override
  protected StockInOrderCreateResponse doCreateOrder(StockInOrderCreateRequest request) {
    WmsUtil.putExtendProp(request, "warehouseType",
        request.getWarehouse().getWarehouseType().toString());
    WmsUtil.putExtendProp(request, "shopCode", request.getStoreCode());
    WmsUtil.putExtendProp(request, "shopName", request.getStoreName());
    WmsUtil.putExtendProp(request, "wmsCode", request.getWmsCode());
    if(!Assert.isEmpty(request.getExtendProps())){
      if(!Assert.isNull(request.getExtendProps().get("lastInTime"))){
        WmsUtil.putExtendProp(request, "lastInTime", request.getExtendProps().get("lastInTime").toString());
      }
      if(!Assert.isNull(request.getExtendProps().get("createTime"))){
        WmsUtil.putExtendProp(request, "createTime", request.getExtendProps().get("createTime").toString());
      }
    }
    return super.doCreateOrder(request);
  }

}
