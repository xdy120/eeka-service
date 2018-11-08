package com.greatonce.oms.biz.impl.stock;

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
 * 库存消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Configuration
public class StockMqConfiguration {

  /**
   * 库存交换机.
   */
  public static final String EXCHANGE_STOCK = "oms.stock";
  public static final String EXCHANGE_STOCK_BINDING_KEY = "oms.stock.#";

  /**
   * 库存上传队列.
   */
  public static final String QUEUE_STOCK_UPLOAD = "oms.stock.upload";
  public static final String QUEUE_STOCK_UPLOAD_BINDING_KEY = "oms.stock.quantity.change";

  /**
   * 借出单审核后自动推送Wms队列.
   */
  public static final String QUEUE_STOCK_LOAN_OUT_NOTICE = "oms.stock.loan.out.notice";
  public static final String QUEUE_STOCK_LOAN_OUT_NOTICE_BINDING_KEY = "oms.stock.loan.out.audited";

  /**
   * 借出单审核后自动推送Wms队列.
   */
  public static final String QUEUE_STOCK_LOAN_IN_NOTICE = "oms.stock.loan.in.notice";
  public static final String QUEUE_STOCK_LOAN_IN_NOTICE_BINDING_KEY = "oms.stock.loan.in.audited";

  /**
   * 出库单审核后自动推送Wms队列.
   */
  public static final String QUEUE_STOCK_OUT_NOTICE = "oms.stock.out.notice";
  public static final String QUEUE_STOCK_OUT_NOTICE_BINDING_KEY = "oms.stock.out.audited";

  /**
   * 入库单审核后自动推送Wms队列.
   */
  public static final String QUEUE_STOCK_IN_NOTICE = "oms.stock.in.notice";
  public static final String QUEUE_STOCK_IN_NOTICE_BINDING_KEY = "oms.stock.in.audited";


  /**
   * 还入单归还.
   */
  public static final String QUEUE_STOCK_LOAN_OUT_RETURN = "oms.stock.loan.out.return";
  public static final String QUEUE_STOCK_LOAN_OUT_RETURN_BINDING_KEY = "oms.stock.loan.in.in";


  @Bean
  public Exchange stockExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_STOCK).build();
  }


  @Bean
  public Binding stockExchangeBinding(@Qualifier("omsExchange") TopicExchange omsExchange,
      @Qualifier("stockExchange") Exchange stockExchange) {
    return BindingBuilder.bind(stockExchange).to(omsExchange)
        .with(EXCHANGE_STOCK_BINDING_KEY);
  }

  @Bean
  public Queue stockUploadQueue() {
    return QueueBuilder.durable(QUEUE_STOCK_UPLOAD).build();
  }

  @Bean
  public Binding stockUploadQueueBinding(@Qualifier("stockExchange") Exchange stockExchange,
      @Qualifier("stockUploadQueue") Queue stockUploadQueue) {
    return BindingBuilder.bind(stockUploadQueue).to(stockExchange)
        .with(QUEUE_STOCK_UPLOAD_BINDING_KEY).noargs();
  }

  @Bean
  public Queue stockLoanOutAuditNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_STOCK_LOAN_OUT_NOTICE).build();
  }

  @Bean
  public Binding stockLoanOutAuditNoticeWmsQueueBinding(
      @Qualifier("stockExchange") Exchange stockExchange,
      @Qualifier("stockLoanOutAuditNoticeWmsQueue") Queue stockLoanOutAuditNoticeWmsQueue) {
    return BindingBuilder.bind(stockLoanOutAuditNoticeWmsQueue).to(stockExchange)
        .with(QUEUE_STOCK_LOAN_OUT_NOTICE_BINDING_KEY).noargs();
  }

  @Bean
  public Queue stockLoanOutReturnQueue() {
    return QueueBuilder.durable(QUEUE_STOCK_LOAN_OUT_RETURN).build();
  }

  @Bean
  public Binding stockLoanOutReturnQueueBinding(
      @Qualifier("stockExchange") Exchange stockExchange,
      @Qualifier("stockLoanOutReturnQueue") Queue stockLoanOutReturnQueue) {
    return BindingBuilder.bind(stockLoanOutReturnQueue).to(stockExchange)
        .with(QUEUE_STOCK_LOAN_OUT_RETURN_BINDING_KEY).noargs();
  }

  @Bean
  public Queue stockLoanInAuditNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_STOCK_LOAN_IN_NOTICE).build();
  }

  @Bean
  public Binding stockLoanInAuditNoticeWmsQueueBinding(
      @Qualifier("stockExchange") Exchange stockExchange,
      @Qualifier("stockLoanInAuditNoticeWmsQueue") Queue stockLoanInAuditNoticeWmsQueue) {
    return BindingBuilder.bind(stockLoanInAuditNoticeWmsQueue).to(stockExchange)
        .with(QUEUE_STOCK_LOAN_IN_NOTICE_BINDING_KEY).noargs();
  }

  @Bean
  public Queue stockOutAuditNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_STOCK_OUT_NOTICE).build();
  }

  @Bean
  public Binding stockOutAuditNoticeWmsQueueBinding(
      @Qualifier("stockExchange") Exchange stockExchange,
      @Qualifier("stockOutAuditNoticeWmsQueue") Queue stockOutAuditNoticeWmsQueue) {
    return BindingBuilder.bind(stockOutAuditNoticeWmsQueue).to(stockExchange)
        .with(QUEUE_STOCK_OUT_NOTICE_BINDING_KEY).noargs();
  }

  @Bean
  public Queue stockInAuditNoticeWmsQueue() {
    return QueueBuilder.durable(QUEUE_STOCK_IN_NOTICE).build();
  }

  @Bean
  public Binding stockInAuditNoticeWmsQueueBinding(
      @Qualifier("stockExchange") Exchange stockExchange,
      @Qualifier("stockInAuditNoticeWmsQueue") Queue stockInAuditNoticeWmsQueue) {
    return BindingBuilder.bind(stockInAuditNoticeWmsQueue).to(stockExchange)
        .with(QUEUE_STOCK_IN_NOTICE_BINDING_KEY).noargs();
  }
}
