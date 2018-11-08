package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-09 10:50
 */
public enum SalesOrderDetailType implements ValueEnum {
  ORIGINAL("原始明细", 1),
  NORMAL("普通明细", 2);
  private final String caption;
  private final int value;

  SalesOrderDetailType(String caption, int value) {
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
