package com.greatonce.oms.search.entity;

import com.greatonce.core.ValueEnum;

public enum Operator implements ValueEnum {
  EQUAL(1, "等于"),
  NOT_EQUAL(2, "不等于"),
  GREATER_THAN(3, "大于"),
  GREATER_THAN_EQUAL(4, "大于等于"),
  LESS_THAN(5, "小于"),
  LESS_THAN_EQUAL(6, "小于等于"),
  PRECISE(7, "等于"),
  FUZZY(8, "包含"),
  NOT_FUZZY(9, "不包含"),
  BEGIN_WITH(10, "开始以"),
  END_WITH(11, "结束以"),
  NULL(12, "不存在"),
  NOT_NULL(13, "存在"),
  MULTI_EQUAL(14, "在列表"),
  NOT_MULTI_EQUAL(15, "不在列表");

  public final int value;
  public final String caption;

  Operator(final int value, final String caption) {
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
