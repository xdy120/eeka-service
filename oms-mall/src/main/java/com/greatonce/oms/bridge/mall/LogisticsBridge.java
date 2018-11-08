package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.WaybillCancelRequest;
import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;
import com.greatonce.oms.bridge.mall.request.WaybillUpdateRequest;
import com.greatonce.oms.bridge.mall.response.WaybillCancelResponse;
import com.greatonce.oms.bridge.mall.response.WaybillGetResponse;
import com.greatonce.oms.bridge.mall.response.WaybillUpdateResponse;

/**
 * 物流接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
public interface LogisticsBridge extends MallBridge {

  /**
   * 获取电子面单.
   */
  WaybillGetResponse getWaybill(WaybillGetRequest request);

  /**
   * 更新电子面单.
   */
  WaybillUpdateResponse updateWaybill(WaybillUpdateRequest request);

  /**
   * 取消电子面单.
   */
  WaybillCancelResponse cancelWaybill(WaybillCancelRequest request);
}
