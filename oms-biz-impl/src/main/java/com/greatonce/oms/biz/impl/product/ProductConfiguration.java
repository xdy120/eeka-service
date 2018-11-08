package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 商品配置类.
 *
 * @author buer
 * @version 2017-12-28 15:43
 */
@Configuration
public class ProductConfiguration {

  @Value("${sequence.id.workid:1}")
  private int idWorkid;
  @Value("${sequence.code.workid:1}")
  private int codeWorkid;

  @Bean
  public IdGenerator productIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator productSkuIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator productMallMappingIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }
}
