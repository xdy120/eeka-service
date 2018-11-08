package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallTransitionStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallTransitionStatus implements ValueEnum {
  WAIT("待转", 1),
  SUCCESS("成功", 2),
  FAILED("失败", 3);
  private final String caption;
  private final int value;

  MallTransitionStatus(String caption, int value) {
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
