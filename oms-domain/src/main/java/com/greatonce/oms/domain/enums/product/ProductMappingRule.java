package com.greatonce.oms.domain.enums.product;

import com.greatonce.core.ValueEnum;

/**
 * ProductMappingRule
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ProductMappingRule implements ValueEnum {
  SKU_CODE("规格编码", 1),
  PRODUCT_CODE("商品编码", 2),
  PRODUCT_CODE_OR_SKU_CODE("商品编码或规格编码", 3),
  PRODUCT_CODE_AND_SKU_CODE("商品编码和规格编码", 4);
  private final String caption;
  private final int value;

  ProductMappingRule(String caption, int value) {
    this.caption = caption;
    this.value = value;
  }

  @Override
  public int value() {
    return this.value;
  }

  @Override
  public String caption() {
    return this.caption;
  }
}
