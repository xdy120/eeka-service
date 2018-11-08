package com.greatonce.oms.consumer.trade.dispatch;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 配货配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/30
 */
@Configuration
@DispatchOrderCondition
public class DispatchConsumerConfiguration implements ApplicationContextAware {

  @Value("${oms.consumer.dispatch.num:1}")
  private Integer consumerNum;
  private ApplicationContext context;

  private void createConsumer() {
    for (Integer i = 0; i < this.consumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(DispatchConsumer.class);
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }

}
