package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 仓储接口类型.
 *
 * @author buer
 * @version 2017-11-15 15:01
 */
public enum WmsType implements ValueEnum {
  QIMEN("奇门", 1),
  QIMEN_GWALL("巨沃奇门", 2),
  QIMEN_FLUX("富勒奇门", 3),
  QIMEN_BEST("百世奇门", 4),
  QIMEN_CAINIAO("菜鸟奇门", 5),
  QIMEN_JD("京东奇门", 6),
  QIMEN_WFY("维富友奇门", 7),
  QIMEN_SF("顺丰奇门", 7),
  QIMEN_EEKA("赢家奇门", 51);
  private final String caption;
  private final int value;

  WmsType(String caption, int value) {
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
