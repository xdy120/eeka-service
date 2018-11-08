package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:14
 */
public enum SalesOrderDetailRefundStatus implements ValueEnum {
  NONE("未退款", 1),
  APPLY("已申请退款", 2),
  REFUND("已退款", 3);
  private final String caption;
  private final int value;

  SalesOrderDetailRefundStatus(String caption, int value) {
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
