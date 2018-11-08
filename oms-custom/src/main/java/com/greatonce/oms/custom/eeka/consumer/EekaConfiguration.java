package com.greatonce.oms.custom.eeka.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 赢家配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
@Configuration
@EekaConsumerCondition
public class EekaConfiguration {

  private ApplicationContext context;

  /**
   * Fms发货通知队列.
   */
  @Bean
  public Queue eekaFmsDeliveryNoticeQueue() {
    return QueueBuilder.durable(EekaConstants.QUEUE_DELIVERY_ORDER_NOTICE).build();
  }

  @Bean
  public Binding eekaFmsDeliveryNoticeQueueBinding(@Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsDeliveryNoticeQueue") Queue eekaFmsDeliveryNoticeQueue) {
    return BindingBuilder.bind(eekaFmsDeliveryNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_DELIVERY_ORDER_NOTICE_BINDING_KEY).noargs();
  }

  @Bean
  public Binding eekaFmsDeliveryNoticeQueueRepostingBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsDeliveryNoticeQueue") Queue eekaFmsDeliveryNoticeQueue) {
    return BindingBuilder.bind(eekaFmsDeliveryNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_DELIVERY_ORDER_NOTICE_REPOSTING_BINDING_KEY).noargs();
  }

  @Bean
  public Queue eekaFmsReturnNoticeQueue() {
    return QueueBuilder.durable(EekaConstants.QUEUE_RETURN_ORDER_NOTICE).build();
  }

  @Bean
  public Binding eekaFmsReturnNoticeQueueBinding(@Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsReturnNoticeQueue") Queue eekaFmsReturnNoticeQueue) {
    return BindingBuilder.bind(eekaFmsReturnNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_RETURN_ORDER_NOTICE_BINDING_KEY).noargs();
  }

  @Bean
  public Binding eekaFmsReturnReviewQueueBinding(@Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsReturnNoticeQueue") Queue eekaFmsReturnNoticeQueue) {
    return BindingBuilder.bind(eekaFmsReturnNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_RETURN_ORDER_REVIEW_BINDING_KEY).noargs();
  }

  @Bean
  public Binding eekaFmsReturnNoticeQueueRepostingBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsReturnNoticeQueue") Queue eekaFmsReturnNoticeQueue) {
    return BindingBuilder.bind(eekaFmsReturnNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_RETURN_ORDER_NOTICE_REPOSTING_BINDING_KEY).noargs();
  }

  @Bean
  public Queue eekaFmsVipDeliveryNoticeQueue() {
    return QueueBuilder.durable(EekaConstants.QUEUE_VIP_DISPATCH_ORDER_DELIVERY_NOTICE).build();
  }

  @Bean
  public Binding eekaFmsVipDeliveryNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsVipDeliveryNoticeQueue") Queue eekaFmsVipDeliveryNoticeQueue) {
    return BindingBuilder.bind(eekaFmsVipDeliveryNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_VIP_DISPATCH_ORDER_DELIVERY_BINDING_KEY).noargs();
  }

  @Bean
  public Binding eekaFmsVipDeliveryNoticeQueueRepostingBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsVipDeliveryNoticeQueue") Queue eekaFmsVipDeliveryNoticeQueue) {
    return BindingBuilder.bind(eekaFmsVipDeliveryNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_VIP_DISPATCH_ORDER_DELIVERY_REPOSTING_BINDING_KEY).noargs();
  }

  @Bean
  public Queue eekaFmsVipReturnNoticeQueue() {
    return QueueBuilder.durable(EekaConstants.QUEUE_VIP_RETURN_ORDER_NOTICE).build();
  }

  @Bean
  public Binding eekaFmsVipReturnNoticeQueueBinding(@Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsVipReturnNoticeQueue") Queue eekaFmsVipReturnNoticeQueue) {
    return BindingBuilder.bind(eekaFmsVipReturnNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_VIP_RETURN_ORDER_IN_BINDING_KEY).noargs();
  }

  @Bean
  public Binding eekaFmsVipReturnNoticeQueueRepostingBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("eekaFmsVipReturnNoticeQueue") Queue eekaFmsVipReturnNoticeQueue) {
    return BindingBuilder.bind(eekaFmsVipReturnNoticeQueue).to(omsExchange)
        .with(EekaConstants.QUEUE_VIP_RETURN_ORDER_IN_REPOSTING_BINDING_KEY).noargs();
  }
}
