package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallGoodsStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallGoodsStatus implements ValueEnum {
  BUYER_NOT_RECEIVED("买家未收到货", 1),
  BUYER_RECEIVED("买家已收到货", 2),
  BUYER_RETURNED_GOODS("买家已退货", 3);
  private final String caption;
  private final int value;

  MallGoodsStatus(String caption, int value) {
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
