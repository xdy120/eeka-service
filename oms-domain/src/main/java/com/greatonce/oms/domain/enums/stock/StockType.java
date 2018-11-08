package com.greatonce.oms.domain.enums.stock;

import com.greatonce.core.ValueEnum;

/**
 * StockType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum StockType implements ValueEnum {
  /**
   * 正品.
   */
  QUALIFIED("正品", 1),
  /**
   * 次品.
   */
  DEFECTIVE("次品", 2);
  private final String caption;
  private final int value;

  StockType(String caption, int value) {
    this.caption = caption;
    this.value = value;
  }

  @Override
  public int value() {
    return this.value;
  }

  @Override
  public String caption() {
    return this.caption;
  }
}
