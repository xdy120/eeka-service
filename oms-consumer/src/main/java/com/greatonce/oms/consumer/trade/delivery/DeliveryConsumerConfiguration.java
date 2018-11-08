package com.greatonce.oms.consumer.trade.delivery;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 发货消费者配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-20
 */
@Configuration
public class DeliveryConsumerConfiguration implements ApplicationContextAware {

  @Value("${oms.consumer.trade.delivery-num:1}")
  private Integer deliveryNum;
  @Value("${oms.consumer.trade.wms-delivery-num:1}")
  private Integer wmsDeliveryNum;

  private ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }

  private void createConsumer() {
    for (Integer i = 0; i < this.deliveryNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(SalesOrderDeliveryConsumer.class);
    }

    for (Integer i = 0; i < this.wmsDeliveryNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(SalesOrderWmsDeliveryConsumer.class);
    }
  }
}