package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;

/**
 * company:Shenzhen Greatonce Co Ltd
 * createOrder date:2017/7/3
 * remark:
 *
 * @author:buer
 */
public class ProductMatchInfo {

  /**
   * 商品sku
   */
  private ProductSku productSku;
  /**
   * 铺货关系
   */
  private ProductMallMapping productMallMapping;

  public ProductSku getProductSku() {
    return productSku;
  }

  public void setProductSku(ProductSku productSku) {
    this.productSku = productSku;
  }

  public ProductMallMapping getProductMallMapping() {
    return productMallMapping;
  }

  public void setProductMallMapping(ProductMallMapping productMallMapping) {
    this.productMallMapping = productMallMapping;
  }
}
