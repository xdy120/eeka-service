package com.greatonce.oms.domain.enums.product;

import com.greatonce.core.ValueEnum;

/**
 * MallProductStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallProductStatus implements ValueEnum {
  ONSALE("上架", 1),
  INSTOCK("下架", 2);
  private final String caption;
  private final int value;

  MallProductStatus(String caption, int value) {
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
