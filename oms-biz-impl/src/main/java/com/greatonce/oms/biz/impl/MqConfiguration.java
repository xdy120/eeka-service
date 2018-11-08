package com.greatonce.oms.biz.impl;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/10
 */
@Configuration
public class MqConfiguration {
  /**
   * 总交换机.
   */
  public static final String EXCHANGE_OMS = "oms";

  /**
   * OMS总交换机.
   */
  @Bean
  public TopicExchange omsExchange() {
    return new TopicExchange(EXCHANGE_OMS);
  }
}
