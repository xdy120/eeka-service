package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.StoreAddressQueryRequest;
import com.greatonce.oms.bridge.mall.response.StoreAddressQueryResponse;

/**
 * 查询换货地址.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public interface StoreBridge extends MallBridge {

  StoreAddressQueryResponse queryAddress(StoreAddressQueryRequest request);
}
