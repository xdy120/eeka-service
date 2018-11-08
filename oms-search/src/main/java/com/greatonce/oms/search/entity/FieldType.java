package com.greatonce.oms.search.entity;

import com.greatonce.core.ValueEnum;

public enum FieldType implements ValueEnum {

  TEXT(1, "text"),
  LONG(2, "long"),
  FLOAT(3, "float"),
  BOOLEAN(4, "boolean"),
  DATETIME(6, "datetime"),
  ENUM(7, "enum"),
  STORE(8, "store");

  public final int value;
  public final String caption;

  FieldType(final int value, final String caption) {
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
