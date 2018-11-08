package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * CodType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum CodType implements ValueEnum {
  NO_COD("非货到付款", 1),
  COD("支持货到付款", 2),
  ONLY_COD("仅货到付款", 3);

  private final String caption;
  private final int value;

  CodType(String caption, int value) {
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