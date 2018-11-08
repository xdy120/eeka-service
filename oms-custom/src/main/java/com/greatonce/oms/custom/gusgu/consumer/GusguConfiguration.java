package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.oms.custom.kingdee.K3CloudClient;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@GusguConsumerCondition
public class GusguConfiguration {

  @Value("${oms.consumer.custom.kingdee.url}")
  private String url;
  @Value("${oms.consumer.custom.kingdee.login-url}")
  private String loginUrl;
  @Value("${oms.consumer.custom.kingdee.acct-id}")
  private String acctId;
  @Value("${oms.consumer.custom.kingdee.username}")
  private String username;
  @Value("${oms.consumer.custom.kingdee.password}")
  private String password;
  @Value("${oms.consumer.custom.kingdee.lcid}")
  private Integer lcid;


  @Bean
  public K3CloudClient k3CloudClient() {
    return new K3CloudClient(url, loginUrl, acctId, username, password, lcid);
  }

  /**
   * B2C销售出库、调拨出库
   */
  @Bean
  public Queue gusguKingdeeDeliveryOrderNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_DELIVERY_ORDER_PUSH).build();
  }

  @Bean
  public Binding gusguKingdeeDeliveryOrderNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeeDeliveryOrderNoticeQueue") Queue gusguKingdeeDeliveryOrderNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeeDeliveryOrderNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_DELIVERY_ORDER_PUSH_BINDING_KEY).noargs();
  }

  @Bean
  public Binding gusguKingdeeStockOutOrderNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeeDeliveryOrderNoticeQueue") Queue gusguKingdeeDeliveryOrderNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeeDeliveryOrderNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_STOCK_OUT_ORDER_PUSH_BINDING_KEY).noargs();
  }

  /**
   * B2C销售退货
   */
  @Bean
  public Queue gusguKingdeeReturnOrderNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_RETURN_ORDER_PUSH).build();
  }

  @Bean
  public Binding gusguKingdeeReturnOrderNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeeReturnOrderNoticeQueue") Queue gusguKingdeeReturnOrderNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeeReturnOrderNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_RETURN_ORDER_PUSH_BINDING_KEY).noargs();
  }

  /**
   * 商品资料同步
   */
  @Bean
  public Queue gusguKingdeeProductSkuNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_PRODUCT_SKU_PUSH).build();
  }

  @Bean
  public Binding gusguKingdeeProductSkuNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeeProductSkuNoticeQueue") Queue gusguKingdeeProductSkuNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeeProductSkuNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_PRODUCT_SKU_PUSH_BINDING_KEY).noargs();
  }

  /**
   * 收款单推送
   */
  @Bean
  public Queue gusguKingdeeAlipayRecordNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_ALIPAY_RECORD_PUSH).build();
  }

  @Bean
  public Binding gusguKingdeeAlipayRecordNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeeAlipayRecordNoticeQueue") Queue gusguKingdeeAlipayRecordNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeeAlipayRecordNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_ALIPAY_RECORD_PUSH_BINDING_KEY).noargs();
  }

  /**
   * 采购退入
   */
  @Bean
  public Queue gusguKingdeePurchaseInNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_PURCHASE_IN_NOTICE).build();
  }

  @Bean
  public Binding gusguKingdeePurchaseInNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeePurchaseInNoticeQueue") Queue gusguKingdeePurchaseInNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeePurchaseInNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_PURCHASE_IN_NOTICE_BINDING_KEY).noargs();
  }


  /**
   * 采购退货出库
   */
  @Bean
  public Queue gusguKingdeePurchaseReturnNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_PURCHASE_RETURN_OUT_NOTICE).build();
  }

  @Bean
  public Binding gusguKingdeePurchaseReturnNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeePurchaseReturnNoticeQueue") Queue gusguKingdeePurchaseReturnNoticeQueue) {
    return BindingBuilder.bind(gusguKingdeePurchaseReturnNoticeQueue).to(omsExchange)
        .with(GusguConstants.QUEUE_PURCHASE_RETURN_OUT_NOTICE_BINDING_KEY).noargs();
  }

  /**
   * 会员创建
   */
  @Bean
  public Queue gusguKingdeeMemberCreateNoticeQueue() {
    return QueueBuilder.durable(GusguConstants.QUEUE_MEMBER_CREATE_NOTICE).build();
  }

  @Bean
  public Binding gusguKingdeeMemberCreateNoticeQueueBinding(
      @Qualifier("omsExchange") Exchange omsExchange,
      @Qualifier("gusguKingdeeMemberCreateNoticeQueue") Queue gusguKingdeeMemberCreateNoticeQueue) {
    return BindingBuilder
        .bind(gusguKingdeeMemberCreateNoticeQueue)
        .to(omsExchange)
        .with(GusguConstants.QUEUE_MEMBER_CREATE_NOTICE_BINDING_KEY)
        .noargs();
  }

}
