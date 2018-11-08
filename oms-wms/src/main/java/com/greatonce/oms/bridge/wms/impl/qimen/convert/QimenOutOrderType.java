package com.greatonce.oms.bridge.wms.impl.qimen.convert;

/**
 * 奇门出库单类型.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/30
 */
public enum QimenOutOrderType implements QimenEnum {
  /**
   * 普通出库.
   */
  PTCK("普通出库"),
  /**
   * 调拨出库.
   */
  DBCK("调拨出库"),
  /**
   * B2B出库.
   */
  B2BCK("B2B出库"),
  /**
   * 其他出库.
   */
  QTCK("其他出库"),
  /**
   * 采购退货出库.
   */
  CGTH("采购退货出库"),
  /**
   * 虚拟出库.
   */
  XNCK("虚拟出库"),
  /**
   * 唯品出库.
   */
  JITCK("唯品出库");

  private final String value;

  QimenOutOrderType(String title) {
    this.value = title;
  }

  @Override
  public String caption() {
    return value;
  }
}
