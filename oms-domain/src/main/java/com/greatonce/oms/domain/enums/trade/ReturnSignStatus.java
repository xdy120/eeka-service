package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * ReturnSignStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ReturnSignStatus implements ValueEnum {
  CREATED("新建", 1),
  UNPACK("已拆包", 2),
  INVALID("已作废", 3);
  private final String caption;
  private final int value;

  ReturnSignStatus(String caption, int value) {
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

