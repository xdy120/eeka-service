package com.greatonce.oms.bridge.wms.impl.qimen.convert;

/**
 * 奇门单据类型.
 */
public enum QimenDeliveryOrderType implements QimenEnum {
  /**
   * 一般交易出库单.
   */
  JYCK("一般交易出库单"),
  /**
   * 普通出库.
   */
  HHCK("换货出库单"),
  /**
   * 补发出库单.
   */
  BFCK("补发出库单"),
  /**
   * 其他出库单.
   */
  QTCK("其他出库单");
  public final String value;

  QimenDeliveryOrderType(String value) {
    this.value = value;
  }

  @Override
  public String caption() {
    return this.value;
  }
}
