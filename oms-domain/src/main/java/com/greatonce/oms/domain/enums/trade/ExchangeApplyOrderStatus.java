package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
public enum ExchangeApplyOrderStatus implements ValueEnum {
  CREATED("新建", 1),
  AGREED("已同意", 2),
  DENYED("已拒绝", 3);
  private final String caption;
  private final int value;

  ExchangeApplyOrderStatus(String caption, int value) {
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
