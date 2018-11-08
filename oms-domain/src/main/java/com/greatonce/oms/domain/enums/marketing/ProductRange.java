package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 活动商品范围
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum ProductRange implements ValueEnum {
  ALL(1, "所有款"),
  INCLUDE(2, "包含指定款"),
  EXCLUDE(3, "排除指定款");
  public final int value;
  public final String caption;

  ProductRange(final int value, final String caption) {
    this.value = value;
    this.caption = caption;
  }

  @Override
  public String caption() {
    return caption;
  }

  @Override
  public int value() {
    return value;
  }
}
