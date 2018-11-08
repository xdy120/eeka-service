package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:13
 */
public enum SourceType implements ValueEnum {
  OTHER("其他", 0),
  MOBILE("手机", 1),
  PC("PC", 2);
  private final String caption;
  private final int value;

  SourceType(String caption, int value) {
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
