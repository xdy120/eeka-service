package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsCombineitemSynchronizeRequest;
import com.qimen.api.response.CombineitemSynchronizeResponse;

/**
 * 组合套装同步适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenCombineitemSynchronizeStrategy extends QimenStrategy {

  CombineitemSynchronizeResponse synchronize(OmsCombineitemSynchronizeRequest request);
}
