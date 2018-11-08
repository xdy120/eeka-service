package com.greatonce.oms.biz.impl.marketing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 营销MQ配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Configuration
public class MarketingMqConfiguration {
  /**
   * 营销交换机.
   */
  public static final String EXCHANGE_MARKETING = "oms.marketing";
  public static final String EXCHANGE_MARKETING_BINDING_KEY = "oms.marketing.#";

  /**
   * 营销交换机.
   */
  @Bean
  public Exchange marketingExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_MARKETING).build();
  }

  /**
   * 营销交换机绑定.
   *
   * @param omsExchange OMS总交换机
   * @param marketingExchange 营销交换机
   */
  @Bean
  public Binding marketingExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("marketingExchange") Exchange marketingExchange) {
    return BindingBuilder.bind(marketingExchange).to(omsExchange)
        .with(EXCHANGE_MARKETING_BINDING_KEY);
  }
}
