package com.greatonce.oms.domain.enums.product;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-11-16 14:13
 */
public enum ProductAttributeType implements ValueEnum {
  DATA_DICT("数据字典", 1),
  TEXT("文本", 2);
  private final String caption;
  private final int value;

  ProductAttributeType(String caption, int value) {
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
