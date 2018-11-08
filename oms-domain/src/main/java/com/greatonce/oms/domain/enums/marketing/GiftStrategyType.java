package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 赠品策略类型
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum GiftStrategyType implements ValueEnum {
  NORMAL("普通策略", 1),
  GROUP("策略分组", 2);
  public final int value;
  public final String caption;

  GiftStrategyType(final String caption, final int value) {
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
