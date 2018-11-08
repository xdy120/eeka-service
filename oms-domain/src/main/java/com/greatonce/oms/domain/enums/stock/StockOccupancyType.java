package com.greatonce.oms.domain.enums.stock;

import com.greatonce.core.ValueEnum;

/**
 * StockOccupancyType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum StockOccupancyType implements ValueEnum {
  COMBINATION_LOCK("套装锁定", 1),
  SALES_ORDER("销售订单", 2),
  DISPATCH_ORDER("配货单", 3),
  RETURN_ORDER("退换货单", 4),
  ACTIVITY("活动报名", 5),
  OUT_ORDER("出库单", 6),
  VIP_SCHEDULE("唯品档期", 7),
  VIP_SALES("唯品销售", 8),
  VIP_DISPATCH("唯品配货", 9),
  PURCHASE_RETURN("采购退货", 10),
  LOAN_OUT("借调出库", 11);
  private final String caption;
  private final int value;

  StockOccupancyType(String caption, int value) {
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
