package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:13
 */
public enum SalesOrderPayStatus implements ValueEnum {
  WAIT("未付", 1),
  PREPAY("预付", 2),
  PAID("已付", 3);
  private final String caption;
  private final int value;

  SalesOrderPayStatus(String caption, int value) {
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
