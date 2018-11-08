package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品档期上传类型
 *
 * @author Coby
 * @version 2018-2-7
 */
public enum VipScheduleUploadType implements ValueEnum {

  FULL(1, "全量"),
  INCREMENT(2, "增量");

  public final int value;
  public final String caption;

  VipScheduleUploadType(final int value, final String caption) {
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
