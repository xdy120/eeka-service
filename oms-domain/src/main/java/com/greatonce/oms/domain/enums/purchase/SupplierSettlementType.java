package com.greatonce.oms.domain.enums.purchase;

import com.greatonce.core.ValueEnum;

/**
 * SupplierSettlementType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum SupplierSettlementType implements ValueEnum {
  NOW_SETTLEMENT("现结", 1),
  MONTH_SETTLEMENT("月结", 2);
  private final String caption;
  private final int value;

  SupplierSettlementType(String caption, int value) {
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

