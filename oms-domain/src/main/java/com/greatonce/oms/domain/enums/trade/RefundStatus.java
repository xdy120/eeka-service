package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:13
 */
public enum RefundStatus implements ValueEnum {
  NONE("无退款", 1),
  PART("部分退款", 2),
  ALL("全部退款", 3);
  private final String caption;
  private final int value;

  RefundStatus(String caption, int value) {
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
