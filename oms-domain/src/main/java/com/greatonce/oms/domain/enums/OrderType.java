package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 单据类型.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
public enum OrderType implements ValueEnum {
  /**
   * 其他.
   */
  OTHER("其他", 1),
  /**
   * B2C销售单.
   */
  B2C_SALES_ORDER("B2C销售单", 101),

  /**
   * B2C配货单.
   */
  B2C_DISPATCH_ORDER("B2C配货单", 102),

  /**
   * B2C退换货单.
   */
  B2C_RETURN_ORDER("B2C退换货单", 103),

  /**
   * B2C退换货通知单.
   */
  B2C_RETURN_NOTICE_ORDER("B2C退换货通知单", 104),

  /**
   * B2C退款单.
   */
  B2C_REFUND_ORDER("B2C退款单", 105),

  /**
   * B2B通知单.
   */
  B2B("B2B通知单", 201),

  /**
   * 采购单.
   */
  PURCHASE_ORDER("采购单", 301),
  /**
   * 采购退货单.
   */
  PURCHASE_RETURN_ORDER("采购退货单", 302),
  /**
   * 采购通知单.
   */
  PURCHASE_NOTICE_ORDER("采购通知单", 303),
  /**
   * 唯品配货单.
   */
  VIP_DISPATCH_ORDER("唯品配货单", 401),

  /**
   * 唯品发货单.
   */
  VIP_DELIVERY_ORDER("唯品发货单", 402),

  /**
   * 唯品退货单.
   */
  VIP_RETURN_ORDER("唯品退货单", 403),

  /**
   * 唯品退货通知单.
   */
  VIP_RETURN_NOTICE_ORDER("唯品退货通知单", 404),

  /**
   * 普通入库单.
   */
  IN_ORDER("入库单", 501),

  /**
   * 普通出库单.
   */
  OUT_ORDER("出库单", 502),
  /**
   * 借调入库单.
   */
  LOAN_IN_ORDER("借调入库单", 503),
  /**
   * 借调出库单.
   */
  LOAN_OUT_ORDER("借调出库单", 504),
  /**
   * 虚拟调拨单.
   */
  VIRTUAL_ALLOW("虚拟调拨单", 602);

  private final String caption;
  private final int value;

  OrderType(String caption, int value) {
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
