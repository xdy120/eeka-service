package com.greatonce.oms.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.greatonce.core.Constants;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.oms.web.converter.String2ListConverter;
import com.greatonce.oms.web.converter.String2LocalDateConverter;
import com.greatonce.oms.web.converter.String2LocalDateTimeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/6/2
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Value("classpath:config/pkcs8_private_key.pem")
  private Resource privateKey;

  @Bean
  public PrivateKey loginPrivateKey()
      throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
    try (InputStream inputStream = privateKey.getInputStream()) {
      final List<String> lines = IOUtils.readLines(inputStream, Constants.CHARSET_UTF8);
      StringBuilder builder = new StringBuilder();
      for (String line : lines) {
        if (!line.startsWith("--")) {
          builder.append(line);
        }
      }
      String keyContent = builder.toString();
      return SecurityUtil.getPrivateKey(keyContent);
    }
  }


  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.removeConvertible(String.class, Collection.class);
    registry.addConverter(new String2ListConverter((ConversionService) registry));
    registry.addConverter(String.class, LocalDate.class, new String2LocalDateConverter());
    registry.addConverter(String.class, LocalDateTime.class, new String2LocalDateTimeConverter());
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    Long2StringJsonFilter filter = new Long2StringJsonFilter();
    fastConverter.setSupportedMediaTypes(Arrays
        .asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8,
            MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN));
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setParserConfig(JsonUtil.PARSER_CONFIG);
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    fastJsonConfig.setSerializeFilters(filter);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    converters.add(fastConverter);
  }
}
