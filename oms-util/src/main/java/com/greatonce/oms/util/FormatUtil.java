package com.greatonce.oms.util;

import com.greatonce.core.ValueEnum;
import com.greatonce.core.util.Assert;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 格式化工具类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-28
 */
public abstract class FormatUtil {

  /**
   * 格式化小数.
   */
  public static String formatDouble(Double value) {
    return Assert.isNull(value) ? "0.0000" : String.format("%.4f", value);
  }

  /**
   * 格式化小数.
   */
  public static String formatBoolean(Boolean value) {
    return Assert.isTrue(value) ? "是" : "否";
  }


  /**
   * 格式化启用状态.
   */
  public static String formatEnabled(Boolean enabled) {
    return Assert.isNull(enabled) ? "启用" : "禁用";
  }


  /**
   * 格式化整数.
   */
  public static String formatInteger(Integer value) {
    return Assert.isNull(value) ? "0" : String.valueOf(value);
  }

  /**
   * 格式化长整数.
   */
  public static String formatLong(Long value) {
    return Assert.isNull(value) ? "0" : String.valueOf(value);
  }

  /**
   * 格式化日期.
   */
  public static String formatLocalDate(LocalDate value) {
    return Assert.isNull(value) ? "0" : String.valueOf(value);
  }

  /**
   * 格式化时间戳.
   */
  public static String formatLocalDateTime(LocalDateTime value) {
    return Assert.isNull(value) ? "0" : String.valueOf(value);
  }

  /**
   * 格式化枚举.
   */
  public static String formatEnum(ValueEnum valueEnum) {
    return Assert.isNull(valueEnum) ? "" : valueEnum.caption();
  }

  /**
   * 如果value有值返回value，否则返回0.
   */
  public static Double getOrZero(Double value) {
    return value == null ? 0D : value;
  }

  /**
   * 如果value有值返回value，否则返回0.
   */
  public static Integer getOrZero(Integer value) {
    return value == null ? 0 : value;
  }

  /**
   * 如果value有值返回value，否则返回0.
   */
  public static Long getOrZero(Long value) {
    return value == null ? 0L : value;
  }
}
