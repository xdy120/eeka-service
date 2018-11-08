package com.greatonce.oms.domain.enums.stock;

import com.greatonce.core.ValueEnum;

/**
 * StockInOrderStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum LoanInStatus implements ValueEnum {
  NO_OUT("未入库", 1),
  PART_OUT("部分入库", 2),
  ALL_OUT("全部入库", 3);

  private final String caption;
  private final int value;

  LoanInStatus(String caption, int value) {
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
