package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.product.ProductSku;

/**
 * SKU修改请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-14
 */
public class SkuCreateRequest extends WmsRequest {

  private final ProductSku sku;

  public SkuCreateRequest(Warehouse warehouse, ProductSku sku) {
    super(warehouse);
    this.sku = sku;
  }

  public ProductSku getSku() {
    return sku;
  }
}
