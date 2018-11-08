package com.greatonce.oms.biz.impl.base;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基础信息MQ配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Configuration
public class BaseMqConfiguration {
  /**
   * 基础数据交换机.
   */
  public static final String EXCHANGE_STORE = "oms.store";
  public static final String EXCHANGE_STORE_BINDING_KEY = "oms.store.#";


  /**
   * 店铺队列.
   */
  public static final String QUEUE_STORE = "oms.store";
  public static final String QUEUE_STORE_BINDING_KEY = "oms.store.*";

  /**
   * 仓库队列.
   */
  public static final String QUEUE_WAREHOUSE = "oms.warehouse";
  public static final String QUEUE_WAREHOUSE_BINDING_KEY = "oms.warehouse.*";

  /**
   * 虚拟仓队列.
   */
  public static final String QUEUE_VIRTUAL_WAREHOUSE = "oms.virtual.warehouse";
  public static final String QUEUE_VIRTUAL_WAREHOUSE_BINDING_KEY = "oms.virtual.warehouse.*";

  /**
   * 快递队列.
   */
  public static final String QUEUE_EXPRESS = "oms.express";
  public static final String QUEUE_EXPRESS_BINDING_KEY = "oms.express.*";

  /**
   * 用户队列.
   */
  public static final String QUEUE_USER = "oms.user";
  public static final String QUEUE_USER_BINDING_KEY = "oms.user.*";

  /**
   * 角色队列.
   */
  public static final String QUEUE_ROLE = "oms.role";
  public static final String QUEUE_ROLE_BINDING_KEY = "oms.role.*";

  /**
   * 基础数据交换机.
   */
  @Bean
  public TopicExchange storeExchange() {
    return new TopicExchange(EXCHANGE_STORE);
  }

  /**
   * 基础数据交换机绑定.
   *
   * @param omsExchange OMS总交换机
   * @param storeExchange 基础数据交换机
   */
  @Bean
  public Binding storeExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("storeExchange") Exchange storeExchange) {

    return BindingBuilder.bind(storeExchange).to(omsExchange)
        .with(EXCHANGE_STORE_BINDING_KEY);
  }


  /**
   * 店铺队列.
   */
  @Bean
  public Queue storeQueue() {
    return QueueBuilder.durable(QUEUE_STORE).build();
  }

  @Bean
  public Binding storeQueueBinding(@Qualifier("storeExchange") Exchange storeExchange,
      @Qualifier("storeQueue") Queue storeQueue) {
    return BindingBuilder.bind(storeQueue).to(storeExchange).with(QUEUE_STORE_BINDING_KEY)
        .noargs();
  }
}
