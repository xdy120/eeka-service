package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-22 17:19
 */
public enum DispatchMode implements ValueEnum {
  /**
   * 明细有货先发
   */
  ANY("明细有货先发", 1),
  /**
   * 整单有货先发
   */
  ALL("整单有货先发", 2);
  private final String caption;
  private final int value;

  DispatchMode(String caption, int value) {
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
