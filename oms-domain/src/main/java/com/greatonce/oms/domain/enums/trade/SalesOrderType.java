package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:12
 */
public enum SalesOrderType implements ValueEnum {
  SALES("销售订单", 1),
  EXPENSE("费用订单", 2),
  SUBSCRIBES("预订订单", 3),
  EXCHANGE("换货订单", 4),
  REISSUE("补发订单", 5),
  INVOICE("补发票订单", 6);
  private final String caption;
  private final int value;

  SalesOrderType(String caption, int value) {
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
