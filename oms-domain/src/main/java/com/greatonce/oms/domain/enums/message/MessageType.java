package com.greatonce.oms.domain.enums.message;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-15 18:19
 */
public enum MessageType implements ValueEnum {
  SYSTEM_NOTIFICATION("系统通知", 1),
  NOTIFICATION("通知", 2),
  WARNING("异常警告", 3),
  DATA_EXPORT("数据导出", 4);
  private final String caption;
  private final int value;

  MessageType(String caption, int value) {
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
