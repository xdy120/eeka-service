package com.greatonce.oms.domain.enums.stock;

import com.greatonce.core.ValueEnum;

/**
 * StockOccupancyStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum StockOccupancyStatus implements ValueEnum {
  NONE("不占实物", 1),
  UNLOCK("未锁定", 2),
  LOCK("已锁定", 3);
  private final String caption;
  private final int value;

  StockOccupancyStatus(String caption, int value) {
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
