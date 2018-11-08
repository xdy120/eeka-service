package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:13
 */
public enum PresellType implements ValueEnum {
  NONE("非预售", 1),
  PART("部分预售", 2),
  ALL("全部预售", 3);
  private final String caption;
  private final int value;

  PresellType(String caption, int value) {
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
