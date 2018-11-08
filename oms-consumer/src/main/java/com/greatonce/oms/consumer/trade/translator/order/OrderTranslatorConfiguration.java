package com.greatonce.oms.consumer.trade.translator.order;

import com.greatonce.oms.consumer.trade.translator.order.impl.AuditTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.ExpressTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.InvoiceTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.MarketingTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.MemberTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.OrderInfoTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.PaymentTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.PreTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.ProductTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.ReceiveTranslator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 订单转化配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/26
 */
@Configuration
@TranslatorOrderCondition
public class OrderTranslatorConfiguration implements ApplicationContextAware {

  private ApplicationContext context;
  @Value("${oms.consumer.translate.order.num:1}")
  private Integer consumerNum;

  @Autowired
  PreTranslator preTranslator;
  @Autowired
  OrderInfoTranslator orderInfoTranslator;
  @Autowired
  ReceiveTranslator receiveTranslator;
  @Autowired
  ProductTranslator productTranslator;
  @Autowired
  PaymentTranslator paymentTranslator;
  @Autowired
  InvoiceTranslator invoiceTranslator;
  @Autowired
  ExpressTranslator expressTranslator;
  @Autowired
  MemberTranslator memberTranslator;
  @Autowired
  MarketingTranslator marketingTranslator;
  @Autowired
  AuditTranslator auditTranslator;


  @Bean
  OrderTranslator orderTranslator(PlatformTransactionManager transactionManager) {
    OrderTranslator orderTranslator = new OrderTranslator(transactionManager);
    orderTranslator.registerTranslator(preTranslator);
    orderTranslator.registerTranslator(orderInfoTranslator);
    orderTranslator.registerTranslator(receiveTranslator);
    orderTranslator.registerTranslator(productTranslator);
    orderTranslator.registerTranslator(paymentTranslator);
    orderTranslator.registerTranslator(invoiceTranslator);
    orderTranslator.registerTranslator(expressTranslator);
    orderTranslator.registerTranslator(memberTranslator);
    orderTranslator.registerTranslator(marketingTranslator);
    orderTranslator.registerTranslator(auditTranslator);
    return orderTranslator;
  }


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }

  private void createConsumer() {
    for (Integer i = 0; i < this.consumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(OrderTranslatorConsumer.class);
    }
  }
}
