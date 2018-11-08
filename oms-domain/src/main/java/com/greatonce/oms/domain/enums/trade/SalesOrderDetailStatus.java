package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:14
 */
public enum SalesOrderDetailStatus implements ValueEnum {
  WAITING("未配货", 2),
  DISPATCHED("已配货", 3),
  DELIVERED("已发货", 4),
  INVALID("作废", 5);
  private final String caption;
  private final int value;

  SalesOrderDetailStatus(String caption, int value) {
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
