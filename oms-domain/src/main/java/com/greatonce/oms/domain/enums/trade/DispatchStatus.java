package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:13
 */
public enum DispatchStatus implements ValueEnum {
  NONE("未配货", 1),
  PART("部分配货", 2),
  ALL("全部配货", 3);
  private final String caption;
  private final int value;

  DispatchStatus(String caption, int value) {
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
