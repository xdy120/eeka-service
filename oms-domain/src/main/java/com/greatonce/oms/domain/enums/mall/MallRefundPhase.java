package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallRefundPhase
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallRefundPhase implements ValueEnum {
  ON_SALE("售中", 1),
  AFTER_SALE("售后", 2);
  private final String caption;
  private final int value;

  MallRefundPhase(String caption, int value) {
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
