package com.greatonce.oms.web.converter;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串装LocalDateTime.
 *
 * @author buer
 * @version 2018-01-25 19:59
 */
public class String2LocalDateTimeConverter implements Converter<String, LocalDateTime> {

  @Override
  public LocalDateTime convert(String source) {
    if (Assert.isEmpty(source)) {
      return null;
    }
    return DateTimeUtil.parserLocalDateTime(String.valueOf(source));
  }
}
