package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品调整类型
 *
 * @author Coby
 * @version 2017-12-15
 */
public enum VipAdjustType implements ValueEnum {

  ADD(1, "增加占用"),
  LESSEN(2, "减少占用");

  public final int value;
  public final String caption;

  VipAdjustType(final int value, final String caption) {
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
