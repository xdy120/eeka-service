package com.greatonce.oms.web.converter;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转LocalDate.
 *
 * @author buer
 * @version 2018-01-25 19:59
 */
public class String2LocalDateConverter implements Converter<String, LocalDate> {


  @Override
  public LocalDate convert(String source) {
    if (Assert.isEmpty(source)) {
      return null;
    }
    return DateTimeUtil.parserLocalDate(String.valueOf(source));
  }
}
