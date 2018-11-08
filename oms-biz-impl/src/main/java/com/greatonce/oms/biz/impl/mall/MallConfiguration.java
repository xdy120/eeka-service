package com.greatonce.oms.biz.impl.mall;

import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 商城单据配置.
 *
 * @author buer
 * @version 2017-12-28 15:43
 */
@Configuration
public class MallConfiguration {

  @Value("${sequence.id.workid:1}")
  private int idWorkid;
  @Value("${sequence.code.workid:1}")
  private int codeWorkid;

  @Bean
  public IdGenerator mallSalesOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator mallExchangeOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator mallRefundOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }
}
