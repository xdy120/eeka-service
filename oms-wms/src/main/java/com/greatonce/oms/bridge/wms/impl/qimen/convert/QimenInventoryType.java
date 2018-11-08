package com.greatonce.oms.bridge.wms.impl.qimen.convert;

/**
 * 奇门库存类型.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
public enum QimenInventoryType {
  /**
   * 正品.
   */
  ZP("正品"),
  /**
   * 残次.
   */
  CC("残次"),
  /**
   * 机损.
   */
  JS("机损"),
  /**
   * 箱损.
   */
  XS("箱损"),
  /**
   * 在途.
   */
  ZT("在途");
  public final String value;

  QimenInventoryType(String value) {
    this.value = value;
  }
}
