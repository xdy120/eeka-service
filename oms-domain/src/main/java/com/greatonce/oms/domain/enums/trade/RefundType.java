package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-17 9:45
 */
public enum RefundType implements ValueEnum {
  ONLY_REFUND("仅退款", 1),
  RETURN_AND_REFUND("退货退款", 2);
  private final String caption;
  private final int value;

  RefundType(String caption, int value) {
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
