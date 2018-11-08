package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.product.ProductSku;

public class EekaProductSkuQueryResponse extends OmsQimenCustomResponse {

  public EekaProductSkuQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaProductSkuQueryResponse(Long requestId, String message) {
    super(requestId, message);
  }

  private ProductSku productSku;

  public ProductSku getProductSku() {
    return productSku;
  }

  public void setProductSku(ProductSku productSku) {
    this.productSku = productSku;
  }
}
