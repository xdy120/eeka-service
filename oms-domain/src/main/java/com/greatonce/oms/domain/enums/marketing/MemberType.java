package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 会员类型
 *
 * @author Coby
 * @version 2017-11-16
 */
public enum MemberType implements ValueEnum {

  ALL(1, "全部"),
  NEW(2, "新会员"),
  OLD(3, "老会员");

  public final int value;
  public final String caption;

  MemberType(final int value, final String caption) {
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
