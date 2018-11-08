package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:12
 */
public enum SalesOrderCreateType implements ValueEnum {
  DOWNLOAD("系统下载", 1),
  IMPORT("人工导入", 2),
  MANUAL("人工新建", 3),
  COPY("人工复制", 4),
  MANUAL_DOWNLOAD("人工下载", 5);
  private final String caption;
  private final int value;

  SalesOrderCreateType(String caption, int value) {
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
