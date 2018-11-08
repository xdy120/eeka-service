package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.ProductFilter;
import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.product.ProductSku;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
@Component
public class ProductFilterImpl implements ProductFilter {

  @Override
  public void execute(SkuCreateRequest request, ProductSku sku) {

  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
