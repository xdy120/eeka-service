package com.greatonce.oms.domain.enums.stock;

import com.greatonce.core.ValueEnum;

/**
 * StockInOrderStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum StockLoanInStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED("已审核", 2),
  NOTICED("已通知", 3),
  NOTICE_FAILED("通知失败", 4),
  CANCEL("已取消", 5);

  private final String caption;
  private final int value;

  StockLoanInStatus(String caption, int value) {
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
