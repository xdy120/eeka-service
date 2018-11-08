package com.greatonce.oms.biz.impl.purchase;

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
 * 采购交换机配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Configuration
public class PurchaseMqConfiguration {

  /**
   * 采购交换机.
   */
  public static final String EXCHANGE_PURCHASE = "oms.purchase";
  public static final String EXCHANGE_PURCHASE_BINDING_KEY = "oms.purchase.#";

  /**
   * 采购单自动生成通知单队列.
   */
  public static final String QUEUE_PURCHASE_NOTICE_ORDER_CREATE = "oms.purchase.notice.order.create";
  public static final String QUEUE_PURCHASE_NOTICE_ORDER_CREATE_BINDING_KEY = "oms.purchase.order.audited";

  /**
   * 采购单通知单自动推送WMS队列.
   */
  public static final String QUEUE_PURCHASE_NOTICE_ORDER_NOTICE = "oms.purchase.notice.order.notice";
  public static final String QUEUE_PURCHASE_NOTICE_ORDER_NOTICE_BINDING_KEY = "oms.purchase.notice.order.created";

  /**
   * 采购退货单自动推送WMS队列.
   */
  public static final String QUEUE_PURCHASE_RETURN_NOTICE = "oms.purchase.return.notice";
  public static final String QUEUE_PURCHASE_RETURN_NOTICE_BINDING_KEY = "oms.purchase.return.audited";

  /**
   * 采购交换机.
   */
  @Bean
  public Exchange purchaseExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_PURCHASE).build();
  }

  /**
   * 采购交换机绑定.
   *
   * @param omsExchange OMS总交换机
   * @param purchaseExchange 交易交换机
   */
  @Bean
  public Binding purchaseExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("purchaseExchange") Exchange purchaseExchange) {
    return BindingBuilder.bind(purchaseExchange).to(omsExchange)
        .with(EXCHANGE_PURCHASE_BINDING_KEY);
  }

  /**
   * 采购订单审核后自动生成采购通知单.
   */
  @Bean
  public Queue purchaseNoticeOrderCreateQueue() {
    return QueueBuilder.durable(QUEUE_PURCHASE_NOTICE_ORDER_CREATE).build();
  }

  @Bean
  public Binding purchaseNoticeOrderCreateQueueBinding(
      @Qualifier("purchaseExchange") Exchange purchaseExchange,
      @Qualifier("purchaseNoticeOrderCreateQueue") Queue purchaseNoticeOrderCreateQueue) {
    return BindingBuilder.bind(purchaseNoticeOrderCreateQueue).to(purchaseExchange)
        .with(QUEUE_PURCHASE_NOTICE_ORDER_CREATE_BINDING_KEY).noargs();
  }

  /**
   * 生成采购通知单后自动推送到WMS.
   */
  @Bean
  public Queue purchaseNoticeOrderNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_PURCHASE_NOTICE_ORDER_NOTICE).build();
  }

  @Bean
  public Binding purchaseNoticeOrderNoticeWmsQueueBinding(
      @Qualifier("purchaseExchange") Exchange purchaseExchange,
      @Qualifier("purchaseNoticeOrderNoticeWmsQueue") Queue purchaseNoticeOrderNoticeWmsQueue) {
    return BindingBuilder.bind(purchaseNoticeOrderNoticeWmsQueue).to(purchaseExchange)
        .with(QUEUE_PURCHASE_NOTICE_ORDER_NOTICE_BINDING_KEY).noargs();
  }

  /**
   * 采购退货单审核后自动推送到WMS.
   */
  @Bean
  public Queue purchaseReturnAuditedNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_PURCHASE_RETURN_NOTICE).build();
  }

  @Bean
  public Binding purchaseReturnAuditedNoticeWmsQueueBinding(
      @Qualifier("purchaseExchange") Exchange purchaseExchange,
      @Qualifier("purchaseReturnAuditedNoticeWmsQueue") Queue purchaseReturnAuditedNoticeWmsQueue) {
    return BindingBuilder.bind(purchaseReturnAuditedNoticeWmsQueue).to(purchaseExchange)
        .with(QUEUE_PURCHASE_RETURN_NOTICE_BINDING_KEY).noargs();
  }
}
