package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

public enum PresellStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED("已审核", 2),
  BEGIN("已开始", 3),
  END("已结束", 4),
  INVALID("已作废", 5);

  private final String caption;
  private final int value;

  PresellStatus(String caption, int value) {
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