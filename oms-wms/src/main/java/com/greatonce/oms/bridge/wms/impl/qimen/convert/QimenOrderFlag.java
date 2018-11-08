package com.greatonce.oms.bridge.wms.impl.qimen.convert;

/**
 * 奇门单据标志.
 */
public enum QimenOrderFlag {
  /**
   * 货到付款.
   */
  COD("货到付款"),
  /**
   * 限时配送.
   */
  LIMIT("限时配送"),
  /**
   * 预售.
   */
  PRESELL("预售"),
  /**
   * 已投诉.
   */
  COMPLAIN("已投诉"),
  /**
   * 拆单.
   */
  SPLIT("拆单"),
  /**
   * 换货.
   */
  EXCHANGE("换货"),
  /**
   * 上门.
   */
  VISIT("上门"),
  /**
   * 可改配送方式.
   */
  MODIFYTRANSPORT("可改配送方式"),
  /**
   * 物流宝代理发货.
   */
  CONSIGN("物流宝代理发货"),
  /**
   * 卖家承担运费.
   */
  SELLER_AFFORD("卖家承担运费"),
  /**
   * 分销订单.
   */
  FENXIAO("分销订单");
  public final String value;

  QimenOrderFlag(String value) {
    this.value = value;
  }
}
