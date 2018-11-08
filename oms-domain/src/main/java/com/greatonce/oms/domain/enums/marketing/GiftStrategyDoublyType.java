package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-27 11:57
 */
public enum GiftStrategyDoublyType implements ValueEnum {
  PRODUCT("款数翻倍", 1),
  QUANTITY("件数翻倍", 2);
  private final String caption;
  private final int value;

  GiftStrategyDoublyType(String caption, int value) {
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
