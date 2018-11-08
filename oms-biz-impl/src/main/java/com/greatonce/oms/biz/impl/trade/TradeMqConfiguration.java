package com.greatonce.oms.biz.impl.trade;

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
 * 订单MQ配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Configuration
public class TradeMqConfiguration {

  /**
   * 交易交换机.
   */
  public static final String EXCHANGE_TRADE = "oms.trade";
  public static final String EXCHANGE_TRADE_BINDING_KEY = "oms.trade.#";

  /**
   * 订单转化队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_TRANSLATE = "oms.trade.sales.order.translate";
  public static final String QUEUE_TRADE_SALES_ORDER_TRANSLATE_DOWNLOAD_BINDING_KEY = "oms.trade.sales.order.download";
  public static final String QUEUE_TRADE_SALES_ORDER_TRANSLATE_IMPORTED_BINDING_KEY = "oms.trade.sales.order.imported";

  /**
   * 订单仓库发货队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_WMS_DELIVERY = "oms.trade.sales.order.wms.delivery";
  public static final String QUEUE_TRADE_SALES_ORDER_WMS_DELIVERY_BINDING_KEY = "oms.trade.dispatch.order.delivery";

  /**
   * 售后申请转化队列.
   */
  public static final String QUEUE_TRADE_REFUND_ORDER_TRANSLATE = "oms.trade.refund.order.translate";
  public static final String QUEUE_TRADE_REFUND_ORDER_TRANSLATE_BINDING_KEY = "oms.trade.refund.order.download";

  /**
   * 换货单转化队列.
   */
  public static final String QUEUE_TRADE_EXCHANGE_ORDER_TRANSLATE = "oms.trade.exchange.order.translate";
  public static final String QUEUE_TRADE_EXCHANGE_ORDER_TRANSLATE_BINDING_KEY = "oms.trade.exchange.order.download";

  /**
   * 配货队列.
   * <p/>
   * 绑定消息： 1.订单审核 2.预售订单到达发货日期配货 3.留单订单到期配货 4.订单自动配货 5.退款取消 6.缺货重配 7.退款成功
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH = "oms.trade.sales.order.dispatch";
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_BINDING_KEY = "oms.trade.sales.order.audit";
  public static final String QUEUE_TRADE_SALES_ORDER_PRESELL_DELIVERY_BINDING_KEY = "oms.trade.sales.order.presell.delivery";
  public static final String QUEUE_TRADE_SALES_ORDER_HOLD_EXPIRED_BINDING_KEY = "oms.trade.sales.order.hold.expired";
  public static final String QUEUE_TRADE_SALES_ORDER_AUTO_DISPATCH_BINDING_KEY = "oms.trade.sales.order.notice.auto.dispatch";
  public static final String QUEUE_TRADE_SALES_ORDER_CANCEL_REFUND_BINDING_KEY = "oms.trade.sales.order.refund.cancel";
  public static final String QUEUE_TRADE_SALES_ORDER_AGREE_REFUND_BINDING_KEY = "oms.trade.sales.order.refund.agree";
  public static final String QUEUE_TRADE_SALES_ORDER_OOS_REDISPATCH_BINDING_KEY = "oms.trade.sales.order.oos.redispatch";
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCHED_BINDING_KEY = "oms.trade.sales.order.dispatched";

  /**
   * 配货30分钟延迟队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_30 = "oms.trade.sales.order.dispatch.delay.30";
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_30_BINDING_KEY = "oms.trade.sales.order.audit.delay.30";

  /**
   * 配货45分钟延迟队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_45 = "oms.trade.sales.order.dispatch.delay.45";
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_45_BINDING_KEY = "oms.trade.sales.order.audit.delay.45";

  /**
   * 配货60分钟延迟队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_60 = "oms.trade.sales.order.dispatch.delay.60";
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_60_BINDING_KEY = "oms.trade.sales.order.audit.delay.60";

  /**
   * 配货120分钟延迟队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_120 = "oms.trade.sales.order.dispatch.delay.120";
  public static final String QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_120_BINDING_KEY = "oms.trade.sales.order.audit.delay.120";

  /**
   * 订单发货队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DELIVERY = "oms.trade.sales.order.delivery";
  public static final String QUEUE_TRADE_SALES_ORDER_DELIVERY_BINDING_KEY = "oms.trade.sales.order.wms.delivery";
  public static final String QUEUE_TRADE_SALES_ORDER_EXPRESS_NOTICE_BINDING_KEY = "oms.trade.dispatch.order.wms.express.notice";

  /**
   * 订单延时发货队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_DELIVERY_DELAY_120 = "oms.trade.sales.order.delivery.delay.120";
  public static final String QUEUE_TRADE_SALES_ORDER_REDELIVERY_BINDING = "oms.trade.sales.order.delivery.retry";

  /**
   * 订单发货后平台修改快递队列.
   */
  public static final String QUEUE_TRADE_MALL_RESET_EXPRESS = "oms.trade.mall.express.reset";
  public static final String QUEUE_TRADE_MALL_RESET_EXPRESS_BINDING_KEY = "oms.trade.mall.delivery.express.reset";

  /**
   * 退货通知单通知队列.
   */
  public static final String QUEUE_TRADE_RETURN_NOTICE_ORDER_NOTICE = "oms.trade.return.notice.order.notice";
  public static final String QUEUE_TRADE_RETURN_NOTICE_ORDER_NOTICE_BINDING_KEY = "oms.trade.return.notice.order.create";

  /**
   * 配货单通知队列.
   */
  public static final String QUEUE_TRADE_DISPATCH_ORDER_NOTICE = "oms.trade.dispatch.order.notice";
  public static final String QUEUE_TRADE_DISPATCH_ORDER_NOTICE_BINDING_KEY = "oms.trade.dispatch.order.create";

  /**
   * 配货单延时通知队列.
   */
  public static final String QUEUE_TRADE_DISPATCH_ORDER_NOTICE_DELAY_120 = "oms.trade.dispatch.order.notice.delay.120";
  public static final String QUEUE_TRADE_DISPATCH_ORDER_RENOTICE = "oms.trade.dispatch.order.notice.retry";

  /**
   * 自动退款审核队列
   */
  public static final String QUEUE_TRADE_REFUND_AUDIT = "oms.trade.refund.audit";
  public static final String QUEUE_TRADE_REFUND_AUDIT_BINDING_KEY = "oms.trade.return.order.scan";


  public static final String QUEUE_TRADE_REFUND_AGREE = "oms.trade.mall.refund.agree";
  public static final String QUEUE_TRADE_REFUND_AGREE_BINDING_KEY = "oms.trade.mall.refund.agree";


  /**
   * 配货单取消通知队列.
   */
  public static final String QUEUE_TRADE_DISPATCH_ORDER_CANCEL_NOTICE = "oms.trade.dispatch.order.cancel.notice";
  public static final String QUEUE_TRADE_DISPATCH_ORDER_CANCEL_BINDING_KEY = "oms.trade.dispatch.order.cancel";

  public static final String QUEUE_TRADE_SALES_ORDER_EVENT_NOTICE = "oms.trade.sales.order.event.notice";
  public static final String QUEUE_TRADE_SALES_ORDER_EVENT_NOTICE_BINDING_KEY = "oms.trade.sales.order.#";

  /**
   * 订单预退款队列.
   */
  public static final String QUEUE_TRADE_SALES_ORDER_PREREFUND_INTERCEPT_DISPATCH = "oms.trade.sales.order.prerefund.intercept.dispatch";
  public static final String QUEUE_TRADE_SALES_ORDER_PREREFUND_INTERCEPT_DISPATCH_BINDING_KEY = "oms.trade.sales.order.prerefund.mall.notice";

  /**
   * 交易交换机.
   */
  @Bean
  public Exchange tradeExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_TRADE).build();
  }

  /**
   * 交易交换机绑定.
   *
   * @param omsExchange OMS总交换机
   * @param tradeExchange 交易交换机
   */
  @Bean
  public Binding tradeExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("tradeExchange") Exchange tradeExchange) {
    return BindingBuilder.bind(tradeExchange).to(omsExchange)
        .with(EXCHANGE_TRADE_BINDING_KEY);
  }

  @Bean
  public Queue salesTranslateQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_TRANSLATE).build();
  }

  @Bean
  public Binding salesTranslateQueueBindingWithDownload(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("salesTranslateQueue") Queue salesTranslateQueue) {
    return BindingBuilder
        .bind(salesTranslateQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_TRANSLATE_DOWNLOAD_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding salesTranslateQueueBindingWithImport(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("salesTranslateQueue") Queue salesTranslateQueue) {
    return BindingBuilder
        .bind(salesTranslateQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_TRANSLATE_IMPORTED_BINDING_KEY)
        .noargs();
  }

  /**
   * 订单转化队列.
   */
  @Bean
  public Queue refundTranslateQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_REFUND_ORDER_TRANSLATE).build();
  }

  @Bean
  public Binding refundTranslateQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("refundTranslateQueue") Queue refundTranslateQueue) {
    return BindingBuilder
        .bind(refundTranslateQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_REFUND_ORDER_TRANSLATE_BINDING_KEY)
        .noargs();
  }

  /**
   * 订单转化队列.
   */
  @Bean
  public Queue exchangeTranslateQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_EXCHANGE_ORDER_TRANSLATE).build();
  }

  @Bean
  public Binding exchangeTranslateQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("exchangeTranslateQueue") Queue exchangeTranslateQueue) {
    return BindingBuilder
        .bind(exchangeTranslateQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_EXCHANGE_ORDER_TRANSLATE_BINDING_KEY)
        .noargs();
  }

  //region 配货队列，延迟队列

  /**
   * 配货队列.
   */
  @Bean
  public Queue dispatchQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DISPATCH).build();
  }

  @Bean
  public Binding dispatchQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DISPATCH_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding dispatchQueuePresellDeliveryBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_PRESELL_DELIVERY_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding dispatchQueueHoldExpiredBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_HOLD_EXPIRED_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding dispatchQueueAutoDispatchBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_AUTO_DISPATCH_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding salesOrderCancelRefundDispatchQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_CANCEL_REFUND_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding salesOrderAgreeRefundDispatchQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_AGREE_REFUND_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding salesOrderOOSRedispatchDispatchQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_OOS_REDISPATCH_BINDING_KEY)
        .noargs();
  }

  @Bean
  public Binding salesOrderDispatchedQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchQueue") Queue dispatchQueue) {
    return BindingBuilder
        .bind(dispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DISPATCHED_BINDING_KEY)
        .noargs();
  }

  /**
   * 30分钟延时配货队列.
   */
  @Bean
  public Queue dispatchDelay30Queue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_30)
        .withArgument("x-dead-letter-exchange", EXCHANGE_TRADE)
        .withArgument("x-dead-letter-routing-key", QUEUE_TRADE_SALES_ORDER_DISPATCH_BINDING_KEY)
        .withArgument("x-message-ttl", 1000 * 60 * 30)
        .build();
  }

  @Bean
  public Binding dispatchDelay30QueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchDelay30Queue") Queue dispatchDelay30Queue) {
    return BindingBuilder.bind(dispatchDelay30Queue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_30_BINDING_KEY).noargs();
  }

  @Bean
  public Queue dispatchDelay45Queue() {

    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_45)
        .withArgument("x-dead-letter-exchange", EXCHANGE_TRADE)
        .withArgument("x-dead-letter-routing-key", QUEUE_TRADE_SALES_ORDER_DISPATCH_BINDING_KEY)
        .withArgument("x-message-ttl", 1000 * 60 * 45)
        .build();
  }

  @Bean
  public Binding dispatchDelay45QueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchDelay45Queue") Queue dispatchDelay45Queue) {
    return BindingBuilder.bind(dispatchDelay45Queue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_45_BINDING_KEY).noargs();
  }

  @Bean
  public Queue dispatchDelay60Queue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_60)
        .withArgument("x-dead-letter-exchange", EXCHANGE_TRADE)
        .withArgument("x-dead-letter-routing-key", QUEUE_TRADE_SALES_ORDER_DISPATCH_BINDING_KEY)
        .withArgument("x-message-ttl", 1000 * 60 * 60)
        .build();
  }

  @Bean
  public Binding dispatchDelay60QueueQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchDelay60Queue") Queue dispatchDelay60Queue) {
    return BindingBuilder.bind(dispatchDelay60Queue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_60_BINDING_KEY).noargs();
  }

  @Bean
  public Queue dispatchDelay120Queue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_120)
        .withArgument("x-dead-letter-exchange", EXCHANGE_TRADE)
        .withArgument("x-dead-letter-routing-key", QUEUE_TRADE_SALES_ORDER_DISPATCH_BINDING_KEY)
        .withArgument("x-message-ttl", 1000 * 60 * 120)
        .build();
  }

  @Bean
  public Binding dispatchDelay120QueueQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchDelay60Queue") Queue dispatchDelay60Queue) {
    return BindingBuilder.bind(dispatchDelay60Queue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DISPATCH_DELAY_120_BINDING_KEY).noargs();
  }

  /**
   * wms发货队列.
   */
  @Bean
  public Queue salesOrderWmsDeliveryQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_WMS_DELIVERY).build();
  }

  @Bean
  public Binding salesOrderWmsDeliveryQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("salesOrderWmsDeliveryQueue") Queue salesOrderWmsDeliveryQueue) {
    return BindingBuilder
        .bind(salesOrderWmsDeliveryQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_WMS_DELIVERY_BINDING_KEY)
        .noargs();
  }

  /**
   * 发货队列.
   */
  @Bean
  public Queue deliveryQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DELIVERY).build();
  }

  @Bean
  public Binding deliveryQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("deliveryQueue") Queue deliveryQueue) {
    return BindingBuilder.bind(deliveryQueue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_DELIVERY_BINDING_KEY).noargs();
  }

  @Bean
  public Binding expressNoticeQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("deliveryQueue") Queue expressNoticeQueue) {
    return BindingBuilder.bind(expressNoticeQueue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_EXPRESS_NOTICE_BINDING_KEY).noargs();
  }

  /**
   * 延时发货队列.
   */
  @Bean
  public Queue deliveryDelay120Queue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_DELIVERY_DELAY_120)
        .withArgument("x-dead-letter-exchange", EXCHANGE_TRADE)
        .withArgument("x-dead-letter-routing-key", QUEUE_TRADE_SALES_ORDER_DELIVERY_BINDING_KEY)
        .withArgument("x-message-ttl", 1000 * 60 * 120)
        .build();
  }

  @Bean
  public Binding deliveryRetryBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("deliveryDelay120Queue") Queue deliveryDelay120Queue) {
    return BindingBuilder.bind(deliveryDelay120Queue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_REDELIVERY_BINDING).noargs();
  }

  /**
   * 订单发货后修改快递队列.
   */
  @Bean
  public Queue mallResetExpressQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_MALL_RESET_EXPRESS).build();
  }

  @Bean
  public Binding mallResetExpressQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("mallResetExpressQueue") Queue mallResetExpressQueue) {
    return BindingBuilder.bind(mallResetExpressQueue).to(tradeExchange)
        .with(QUEUE_TRADE_MALL_RESET_EXPRESS_BINDING_KEY).noargs();
  }

  /**
   * 退货通知队列.
   */
  @Bean
  public Queue returnNoticeQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_RETURN_NOTICE_ORDER_NOTICE).build();
  }

  @Bean
  public Binding returnNoticeQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("returnNoticeQueue") Queue returnNoticeQueue) {
    return BindingBuilder.bind(returnNoticeQueue).to(tradeExchange)
        .with(QUEUE_TRADE_RETURN_NOTICE_ORDER_NOTICE_BINDING_KEY).noargs();
  }

  /**
   * 配货单通知.
   */
  @Bean
  public Queue dispatchNoticeQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_DISPATCH_ORDER_NOTICE).build();
  }

  @Bean
  public Binding dispatchNoticeQueueBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchNoticeQueue") Queue dispatchNoticeQueue) {
    return BindingBuilder.bind(dispatchNoticeQueue).to(tradeExchange)
        .with(QUEUE_TRADE_DISPATCH_ORDER_NOTICE_BINDING_KEY).noargs();
  }

  /**
   * 配货单延时通知.
   */
  @Bean
  public Queue dispatchNoticeDelay120Queue() {
    return QueueBuilder.durable(QUEUE_TRADE_DISPATCH_ORDER_NOTICE_DELAY_120)
        .withArgument("x-dead-letter-exchange", EXCHANGE_TRADE)
        .withArgument("x-dead-letter-routing-key", QUEUE_TRADE_DISPATCH_ORDER_NOTICE)
        .withArgument("x-message-ttl", 1000 * 60 * 120)
        .build();
  }

  @Bean
  public Binding dispatchNoticeRetryBinding(@Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchNoticeDelay120Queue") Queue dispatchNoticeDelay120Queue) {
    return BindingBuilder.bind(dispatchNoticeDelay120Queue).to(tradeExchange)
        .with(QUEUE_TRADE_DISPATCH_ORDER_RENOTICE).noargs();
  }

  /**
   * 配货单取消通知.
   */
  @Bean
  public Queue dispatchCancelNoticeQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_DISPATCH_ORDER_CANCEL_NOTICE).build();
  }

  @Bean
  public Binding dispatchCancelNoticeQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("dispatchCancelNoticeQueue") Queue dispatchCancelNoticeQueue) {
    return BindingBuilder.bind(dispatchCancelNoticeQueue).to(tradeExchange)
        .with(QUEUE_TRADE_DISPATCH_ORDER_CANCEL_BINDING_KEY).noargs();
  }

  @Bean
  public Queue salesOrderEventNoticeQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_EVENT_NOTICE).build();
  }

  @Bean
  public Binding salesOrderEventNoticeQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("salesOrderEventNoticeQueue") Queue salesOrderEventNoticeQueue) {
    return BindingBuilder.bind(salesOrderEventNoticeQueue).to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_EVENT_NOTICE_BINDING_KEY).noargs();
  }

  @Bean
  public Queue mallRefundAgreeQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_REFUND_AGREE).build();
  }

  @Bean
  public Queue refundAuditQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_REFUND_AUDIT).build();
  }

  @Bean
  public Binding mallRefundAgreeQueueBinding(
      @Qualifier("tradeExchange") Exchange tradeExchange,
      @Qualifier("mallRefundAgreeQueue") Queue mallRefundAgreeQueue) {
    return BindingBuilder.bind(mallRefundAgreeQueue).to(tradeExchange)
        .with(QUEUE_TRADE_REFUND_AGREE_BINDING_KEY).noargs();
  }

  @Bean
  public Binding refundAuditQueueBinding(@Qualifier("refundAuditQueue") Queue refundAuditQueue,
      @Qualifier("tradeExchange") Exchange tradeExchange) {
    return BindingBuilder
        .bind(refundAuditQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_REFUND_AUDIT_BINDING_KEY)
        .noargs();
  }

  /**
   * 销售订单预退款拦截配货队列.
   */
  @Bean
  public Queue salesOrderPreRefundInterceptDispatchQueue() {
    return QueueBuilder.durable(QUEUE_TRADE_SALES_ORDER_PREREFUND_INTERCEPT_DISPATCH).build();
  }

  @Bean
  public Binding salesOrderPreRefundInterceptDispatchQueueBinding(
      @Qualifier("salesOrderPreRefundInterceptDispatchQueue") Queue salesOrderPreRefundInterceptDispatchQueue,
      @Qualifier("tradeExchange") Exchange tradeExchange) {
    return BindingBuilder
        .bind(salesOrderPreRefundInterceptDispatchQueue)
        .to(tradeExchange)
        .with(QUEUE_TRADE_SALES_ORDER_PREREFUND_INTERCEPT_DISPATCH_BINDING_KEY)
        .noargs();
  }
}

