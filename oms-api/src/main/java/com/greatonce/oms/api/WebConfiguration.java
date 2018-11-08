package com.greatonce.oms.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.api.qimen.QimenMessageConverter;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

  @Value("${sequence.id.workid:1}")
  int idWorkid;

  @Bean
  public IdGenerator apiIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    fastConverter.setSupportedMediaTypes(Arrays
        .asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8,
            MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN));
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setParserConfig(JsonUtil.PARSER_CONFIG);
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    converters.add(fastConverter);

    QimenMessageConverter qimenConverter = new QimenMessageConverter();
    qimenConverter
        .setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML, MediaType.TEXT_XML));
    converters.add(qimenConverter);
  }
}
