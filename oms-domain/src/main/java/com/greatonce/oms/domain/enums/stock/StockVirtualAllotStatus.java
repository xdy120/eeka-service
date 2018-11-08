package com.greatonce.oms.domain.enums.stock;

import com.greatonce.core.ValueEnum;

/**
 * StockVirtualAllotStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum StockVirtualAllotStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED("已审核", 2),
  INVALID("已作废", 3);
  private final String caption;
  private final int value;

  StockVirtualAllotStatus(String caption, int value) {
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
