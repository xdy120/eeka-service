package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-04 18:27
 */
public enum DispatchOrderStatus implements ValueEnum {
  CREATED("新建", 1),
  NOTIFIED("已通知", 2),
  NOTICE_FAILED("通知失败", 3),
  DELIVERED("已发货", 4),
  CANCELED("已取消", 5);
  private final String caption;
  private final int value;

  DispatchOrderStatus(String caption, int value) {
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