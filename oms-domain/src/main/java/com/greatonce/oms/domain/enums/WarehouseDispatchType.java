package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 仓库配货类型
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum WarehouseDispatchType implements ValueEnum {

  SINGLE(1, "一单一货"),
  MULTIPLE(2, "一单多货");

  public final int value;
  public final String caption;

  WarehouseDispatchType(final int value, final String caption) {
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
