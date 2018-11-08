package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;
import com.greatonce.oms.domain.product.ProductSku;

/**
 * 商品过滤器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface ProductFilter extends WmsFilter {

  void execute(SkuCreateRequest request, ProductSku sku);
}
