package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

public enum VipJitType implements ValueEnum {

  NORMAL(0, "普通JIT"),
  OXO(1, "OXO"),
  WIW(2, "仓中仓"),
  JIT(3, "预调拨");

  public final int value;
  public final String caption;

  VipJitType(final int value, final String caption) {
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
