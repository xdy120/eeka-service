package com.greatonce.oms.bridge.wms.impl.qimen.custom.eeka;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.qimen.QimenReturnOrderBridge;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.request.ReturnorderQueryRequest;
import com.qimen.api.response.ReturnorderQueryResponse;
import org.springframework.stereotype.Component;

/**
 * 赢家退货单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-25
 */
@Component
public class EekaReturnOrderBridge extends QimenReturnOrderBridge {

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }

  @Override
  protected ReturnOrderCreateResponse doCreateOrder(ReturnOrderCreateRequest request) {
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

  /**
   * 查询退货单.
   *
   * @link taobao.qimen.returnorder.query
   */
  @Override
  protected ReturnOrderQueryResponse doQueryOrder(ReturnOrderQueryRequest request) {
    try {
      ReturnorderQueryRequest req = new ReturnorderQueryRequest();
      req.setReturnOrderCode(request.getOmsCode());
      req.setReturnOrderId(request.getWmsCode());
      final ReturnorderQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);
      final ReturnOrderQueryResponse response = new ReturnOrderQueryResponse(request);
      if (rsp.getReturnOrder() != null) {
        if (Assert.isNull(rsp.getReturnOrder().getRemark())
            || "已取消".equals(rsp.getReturnOrder().getRemark())) {
          response.setExists(false);
        } else {
          response.setExists(true);
        }
      } else {
        response.setExists(false);
      }
      return response;
    } catch (Exception e) {
      return new ReturnOrderQueryResponse(request, false, e.getMessage());
    }
  }

}
