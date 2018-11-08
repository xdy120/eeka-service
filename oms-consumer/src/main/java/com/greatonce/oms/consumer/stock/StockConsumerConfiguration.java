package com.greatonce.oms.consumer.stock;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 库存消费配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/10
 */
@Configuration
@StockUploadCondition
public class StockConsumerConfiguration implements ApplicationContextAware {

  @Value("${oms.consumer.stock-upload.num:1}")
  private Integer consumerNum;

  private ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }

  private void createConsumer() {
    for (Integer i = 0; i < this.consumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(StockUploadConsumer.class);
    }
  }
}
