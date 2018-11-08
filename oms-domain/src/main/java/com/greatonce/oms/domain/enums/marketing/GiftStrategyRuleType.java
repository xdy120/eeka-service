package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 赠品策略类型
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum GiftStrategyRuleType implements ValueEnum {
  MONEY("金额", 1),
  PRODUCT_QUANTITY("款数", 2),
  QUANTITY("件数", 3),
  LUCK_BAG("福袋", 4);
  public final int value;
  public final String caption;

  GiftStrategyRuleType(final String caption, final int value) {
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
