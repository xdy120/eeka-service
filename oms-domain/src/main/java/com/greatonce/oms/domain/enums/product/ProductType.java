package com.greatonce.oms.domain.enums.product;

import com.greatonce.core.ValueEnum;

/**
 * ProductType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ProductType implements ValueEnum {
  PHYSICAL("实物商品", 1),
  VIRTUAL("虚拟商品", 2);
  private final String caption;
  private final int value;

  ProductType(String caption, int value) {
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
