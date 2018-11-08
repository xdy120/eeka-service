package com.greatonce.oms.bridge.wms.impl.qimen.convert;

/**
 * 奇门入库单类型.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
public enum QimenInOrderType implements QimenEnum {
  /**
   * 生产入库.
   */
  SCRK("生产入库"),
  /**
   * 领用入库.
   */
  LYRK("领用入库"),
  /**
   * 残次品入库.
   */
  CCRK("残次品入库"),
  /**
   * 采购入库.
   */
  CGRK("采购入库"),
  /**
   * 调拨入库.
   */
  DBRK("调拨入库"),
  /**
   * 其他入库.
   */
  QTRK("其他入库"),
  /**
   * B2B入库.
   */
  B2BRK("B2B入库"),
  /**
   * 虚拟入库.
   */
  XNRK("虚拟入库");
  public final String value;

  QimenInOrderType(String value) {
    this.value = value;
  }

  @Override
  public String caption() {
    return this.value;
  }
}
