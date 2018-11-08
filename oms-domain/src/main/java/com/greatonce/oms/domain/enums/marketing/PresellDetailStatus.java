package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * PresellDetailStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum PresellDetailStatus implements ValueEnum {
  NOT_STARTED("未开始", 1),
  STARTED("已开始", 2),
  FINISHED("已结束", 3);

  private final String caption;
  private final int value;

  PresellDetailStatus(String caption, int value) {
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