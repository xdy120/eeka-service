package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 策略所匹配的时间
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum TimeType implements ValueEnum {
  PAID_TIME(1, "支付时间"),
  CREATED_TIME(2, "下单时间"),;
  public final int value;
  public final String caption;

  TimeType(final int value, final String caption) {
    this.value = value;
    this.caption = caption;
  }

  @Override
  public String caption() {
    return caption;
  }

  @Override
  public int value() {
    return value;
  }
}
