package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * ReturnOrderStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ReturnOrderStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED("已审核", 2),
  REVIEWED("已复核", 3),
  INVALID("已作废", 4);

  private final String caption;
  private final int value;

  ReturnOrderStatus(String caption, int value) {
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

