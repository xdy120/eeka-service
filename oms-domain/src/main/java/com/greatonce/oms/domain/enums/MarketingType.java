package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/10
 */
public enum MarketingType implements ValueEnum {
  ACTIVITY("活动报名", 1),
  PRE_SELL("预售", 2),
  VIP_SCHEDULE("唯品档期", 3),
  GIFT("赠品营销", 4);
  private final String caption;
  private final int value;

  MarketingType(String caption, int value) {
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
