package com.greatonce.oms.search.entity;

import com.greatonce.core.ValueEnum;

public enum BoolType implements ValueEnum {

  MUST(1, "must"),
  MUST_NOT(2, "mustNot"),
  SHOULD(3, "should"),
  FILTER(4, "filter");

  public final int value;
  public final String caption;

  BoolType(final int value, final String caption) {
    this.value = value;
    this.caption = caption;
  }

  @Override
  public int value() {
    return value;
  }

  @Override
  public String caption() {
    return caption;
  }

}
