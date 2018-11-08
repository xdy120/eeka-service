package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallStepOrderStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallStepOrderStatus implements ValueEnum {
  DEPOSIT("订金", 1),
  RETAINAGE("尾款", 2);
  private final String caption;
  private final int value;

  MallStepOrderStatus(String caption, int value) {
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
