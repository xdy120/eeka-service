package com.greatonce.oms.biz.impl.message;

import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 信息配置.
 *
 * @author buer
 * @version 2017-12-28 15:45
 */
@Configuration
public class MessageConfiguration {

  @Value("${sequence.id.workid:1}")
  private int idWorkid;

  @Bean
  public IdGenerator messageIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }
}
