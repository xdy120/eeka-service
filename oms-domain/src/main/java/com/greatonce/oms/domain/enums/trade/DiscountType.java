package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:14
 */
public enum DiscountType implements ValueEnum {
  /**
   * 其他
   */
  OTHER("其他", 0),
  /**
   * 套装优惠
   */
  SUIT("套装优惠", 20),
  /**
   * 闪团优惠
   */
  FLASH_GROUP("闪团优惠", 28),
  /**
   * 团购优惠
   */
  GROUP_PURCHASE("团购优惠", 29),
  /**
   * 单品促销优惠
   */
  SINGLE_PRODUCT("单品促销优惠", 30),
  /**
   * 满返满送(返现)
   */
  FULL_BACK_FULL_SEND("满返满送(返现)", 35),
  /**
   * 代金券
   */
  VOUCHER("代金券", 36),
  /**
   * 店铺优惠
   */
  STORE_DISCOUNT("店铺优惠 ", 100);
  private final String caption;
  private final int value;

  DiscountType(String caption, int value) {
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
