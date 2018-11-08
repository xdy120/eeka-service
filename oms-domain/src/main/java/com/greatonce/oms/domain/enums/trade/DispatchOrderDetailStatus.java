package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-04 18:28
 */
public enum DispatchOrderDetailStatus implements ValueEnum {
  WAITING("待发货", 1),
  DELIVERED("已发货", 2),
  CANCELED("已取消", 3);
  private final String caption;
  private final int value;

  DispatchOrderDetailStatus(String caption, int value) {
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
