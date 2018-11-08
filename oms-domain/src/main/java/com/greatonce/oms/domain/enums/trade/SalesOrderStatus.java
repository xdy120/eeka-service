package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:13
 */
public enum SalesOrderStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED_ABNORMAL("审核失败", 10),
  AUDITED("已审核", 11),
  DISPATCHED_ABNORMAL("配货异常", 20),
  DISPATCHED("已配货", 22),
  DELIVERY_ABNORMAL("发货异常", 30),
  DELIVERED("已发货", 31),
  INVALID("作废", 100);

  private final String caption;
  private final int value;

  SalesOrderStatus(String caption, int value) {
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
