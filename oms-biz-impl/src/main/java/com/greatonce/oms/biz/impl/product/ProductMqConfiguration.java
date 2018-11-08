package com.greatonce.oms.biz.impl.product;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 商品MQ配置类.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/28
 */
@Configuration
public class ProductMqConfiguration {

  /**
   * 商品交换机.
   */
  public static final String EXCHANGE_PRODUCT = "oms.product";
  public static final String EXCHANGE_PRODUCT_BINDING_KEY = "oms.product.#";

  /**
   * 商品WMS推送队列.
   */
  public static final String QUEUE_PRODUCT_SKU_NOTICE_WMS = "oms.product.sku.wms.notice";


  /**
   * 商品交换机.
   */
  @Bean
  public Exchange productExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_PRODUCT).build();
  }

  /**
   * 商品交换机绑定.
   */
  @Bean
  public Binding productExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("productExchange") Exchange productExchange) {
    return BindingBuilder.bind(productExchange).to(omsExchange)
        .with(EXCHANGE_PRODUCT_BINDING_KEY);
  }

  /**
   * 商品索引更新队列.
   */
  @Bean
  public Queue productSkuNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_PRODUCT_SKU_NOTICE_WMS).build();
  }

  @Bean
  public Binding productSkuNoticeWmsBinding(
      @Qualifier("productExchange") Exchange productExchange,
      @Qualifier("productSkuNoticeWmsQueue") Queue productSkuNoticeWmsQueue) {
    return BindingBuilder.bind(productSkuNoticeWmsQueue)
        .to(productExchange)
        .with("oms.product.sku.#")
        .noargs();
  }
}
