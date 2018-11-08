package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 性别类型
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum SexType implements ValueEnum {

  MALE(1, "男"),
  FEMALE(2, "女");

  public final int value;
  public final String caption;

  SexType(final int value, final String caption) {
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
