package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * JSON配置类型
 *
 * @author Coby
 * @version 2017-11-16
 */
public enum SettingType implements ValueEnum {

  ORDER_AUDIT_STRATEGY(1, "审单策略"),
  SALES_ORDER_CONFIG(2, "订单配置"),
  RETURN_ORDER_CONFIG(3, "退换货单配置"),
  INVENTORY_CONFIG(4, "库存配置"),
  PRODUCT_CONFIG(5, "商品配置"),
  RECEIPTS_IN_OUT_CONFIG(6, "出入库单据配置");

  public final int value;
  public final String caption;

  SettingType(int value, String caption) {
    this.value = value;
    this.caption = caption;
  }

  @Override
  public String caption() {
    return caption;
  }

  @Override
  public int value() {
    return value;
  }
}
