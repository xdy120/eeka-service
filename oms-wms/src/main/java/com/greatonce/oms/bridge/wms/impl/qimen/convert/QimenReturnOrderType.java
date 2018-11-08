package com.greatonce.oms.bridge.wms.impl.qimen.convert;

/**
 * 奇门退货入库类型.
 */
public enum QimenReturnOrderType implements QimenEnum {
  /**
   * 退货入库.
   */
  THRK("退货入库"),
  /**
   * 换货入库.
   */
  HHRK("换货入库");
  public final String value;

  QimenReturnOrderType(String value) {
    this.value = value;
  }

  @Override
  public String caption() {
    return this.value;
  }
}
