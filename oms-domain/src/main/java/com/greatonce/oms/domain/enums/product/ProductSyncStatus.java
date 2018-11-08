package com.greatonce.oms.domain.enums.product;

import com.greatonce.core.ValueEnum;

/**
 * ProductSyncStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ProductSyncStatus implements ValueEnum {
  NONE("未同步", 1),
  PART("部分同步", 2),
  ALL("全部同步", 3);
  private final String caption;
  private final int value;

  ProductSyncStatus(String caption, int value) {
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
