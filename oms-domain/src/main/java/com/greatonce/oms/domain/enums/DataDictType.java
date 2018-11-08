package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-11-20 9:24
 */
public enum DataDictType implements ValueEnum {
  DICT("字典", 1),
  GROUP("分组", 2);
  private final String caption;
  private final int value;

  DataDictType(String caption, int value) {
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
