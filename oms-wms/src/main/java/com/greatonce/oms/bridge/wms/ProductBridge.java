package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockQueryRequest;
import com.greatonce.oms.bridge.wms.response.SkuCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockQueryResponse;

/**
 * 商品接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public interface ProductBridge extends WmsBridge {

  /**
   * 查询所有库存.
   */
  StockQueryResponse queryStock(StockQueryRequest request);

  /**
   * 创建SKU.
   */
  SkuCreateResponse createSku(SkuCreateRequest request);
}
