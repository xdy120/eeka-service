package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 赠品策略类型
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum GiftStrategyGiftType implements ValueEnum {
  GOODS("商品", 1),
  EXPRESS("快递", 2),;
  public final int value;
  public final String caption;

  GiftStrategyGiftType(final String caption, final int value) {
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
