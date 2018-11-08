package com.greatonce.oms.biz.impl.vip;

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
 * 唯品消息配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Configuration
public class VipMqConfiguration {

  /**
   * 唯品交换机.
   */
  public static final String EXCHANGE_VIP = "oms.vip";
  public static final String EXCHANGE_VIP_BINDING_KEY = "oms.vip.#";
  /**
   * 唯品配货单通知队列.
   */
  public static final String QUEUE_VIP_DISPATCH_NOTICE = "oms.vip.dispatch.notice";
  public static final String QUEUE_VIP_DISPATCH_NOTICE_BINDING_KEY = "oms.vip.dispatch.binding";

  /**
   * 唯品拣货单发货队列.
   */
  public static final String QUEUE_VIP_MALL_DELIVERY = "oms.vip.mall.delivery";
  public static final String QUEUE_VIP_MALL_DELIVERY_BINDING_KEY = "oms.vip.dispatch.delivery";

  /**
   * 唯品退货单通知队列.
   */
  public static final String QUEUE_VIP_RETURN_NOTICE_NOTICE = "oms.vip.return.notice.notice";
  public static final String QUEUE_VIP_RETURN_NOTICE_NOTICE_BINDING_KEY = "oms.vip.return.notice.create";

  /**
   * 唯品交换机.
   */
  @Bean
  public Exchange vipExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_VIP).build();
  }

  /**
   * 唯品交换机绑定.
   *
   * @param omsExchange OMS总交换机
   * @param vipExchange 唯品交换机
   */
  @Bean
  public Binding vipExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("vipExchange") Exchange vipExchange) {
    return BindingBuilder.bind(vipExchange).to(omsExchange)
        .with(EXCHANGE_VIP_BINDING_KEY);
  }

  /**
   * 唯品发退货单通知队列.
   */
  @Bean
  public Queue vipReturnNoticeQueue() {
    return QueueBuilder.durable(QUEUE_VIP_RETURN_NOTICE_NOTICE).build();
  }

  @Bean
  public Binding vipReturnNoticeQueueBinding(@Qualifier("vipExchange") Exchange vipExchange,
      @Qualifier("vipReturnNoticeQueue") Queue vipReturnNoticeQueue) {
    return BindingBuilder.bind(vipReturnNoticeQueue).to(vipExchange)
        .with(QUEUE_VIP_RETURN_NOTICE_NOTICE_BINDING_KEY).noargs();
  }


  /**
   * 唯品配货单通知队列.
   */
  @Bean
  public Queue vipDispatchNoticeQueue() {
    return QueueBuilder.durable(QUEUE_VIP_DISPATCH_NOTICE).build();
  }

  @Bean
  public Binding vipDispatchNoticeQueueBinding(@Qualifier("vipExchange") Exchange vipExchange,
      @Qualifier("vipDispatchNoticeQueue") Queue vipDispatchNoticeQueue) {
    return BindingBuilder.bind(vipDispatchNoticeQueue).to(vipExchange)
        .with(QUEUE_VIP_DISPATCH_NOTICE_BINDING_KEY).noargs();
  }


  /**
   * 唯品出仓单自动发货队列.
   */
  @Bean
  public Queue vipMallDeliveryQueue() {
    return QueueBuilder.durable(QUEUE_VIP_MALL_DELIVERY).build();
  }

  @Bean
  public Binding vipMallDeliveryQueueBinding(@Qualifier("vipExchange") Exchange vipExchange,
      @Qualifier("vipMallDeliveryQueue") Queue vipDeliveryImportQueue) {
    return BindingBuilder.bind(vipDeliveryImportQueue).to(vipExchange)
        .with(QUEUE_VIP_MALL_DELIVERY_BINDING_KEY).noargs();
  }
}
