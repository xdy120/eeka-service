package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.oms.bridge.mall.LogisticsBridge;
import com.greatonce.oms.bridge.mall.request.WaybillCancelRequest;
import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;
import com.greatonce.oms.bridge.mall.request.WaybillUpdateRequest;
import com.greatonce.oms.bridge.mall.response.WaybillCancelResponse;
import com.greatonce.oms.bridge.mall.response.WaybillGetResponse;
import com.greatonce.oms.bridge.mall.response.WaybillUpdateResponse;

/**
 * 物流抽象类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
public abstract class AbstractLogisticsBridge extends AbstractBridge implements LogisticsBridge {

  @Override
  public WaybillGetResponse getWaybill(WaybillGetRequest request) {
    if (isTesting()) {
      return new WaybillGetResponse(request);
    } else {
      return doGetWaybill(request);
    }
  }

  @Override
  public WaybillUpdateResponse updateWaybill(WaybillUpdateRequest request) {
    if (isTesting()) {
      return new WaybillUpdateResponse(request);
    } else {
      return doUpdateWaybill(request);
    }
  }

  @Override
  public WaybillCancelResponse cancelWaybill(WaybillCancelRequest request) {
    if (isTesting()) {
      return new WaybillCancelResponse(request);
    } else {
      return doCancelWaybill(request);
    }
  }

  protected abstract WaybillGetResponse doGetWaybill(WaybillGetRequest request);

  protected abstract WaybillUpdateResponse doUpdateWaybill(WaybillUpdateRequest request);

  protected abstract WaybillCancelResponse doCancelWaybill(WaybillCancelRequest request);
}
