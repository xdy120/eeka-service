package com.greatonce.oms.bridge.wms.qimen;

import com.greatonce.oms.bridge.wms.qimen.request.OmsItemsSynchronizeRequest;
import com.qimen.api.response.ItemsSynchronizeResponse;

/**
 * 商品同步适配器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public interface QimenItemsSynchronizeStrategy extends QimenStrategy {

  ItemsSynchronizeResponse synchronize(OmsItemsSynchronizeRequest request);
}
