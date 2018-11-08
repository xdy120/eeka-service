package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * DeliveryStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum DeliveryStatus implements ValueEnum {
  NONE("未发货", 1),
  PART("部分发货", 2),
  ALL("已发货", 3);

  private final String caption;
  private final int value;

  DeliveryStatus(String caption, int value) {
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
