package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallDataProcessStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallDataProcessStatus implements ValueEnum {
  WAITING("等待处理", 1),
  SUCCESS("处理成功", 2),
  FAILED("处理失败", 3),;
  private final String caption;
  private final int value;

  MallDataProcessStatus(String caption, int value) {
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
