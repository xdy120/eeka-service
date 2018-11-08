package com.greatonce.oms.consumer.trade.translator.exchange;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 换货单转化配置.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/30
 */
@Configuration
@TranslatorExchangeCondition
public class ExchangeTranslatorConfiguration implements ApplicationContextAware {

  private ApplicationContext context;

  @Value("${oms.consumer.translate.exchange.num:1}")
  private Integer consumerNum;


  private void createConsumer() {
    for (Integer i = 0; i < this.consumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(ExchangeTranslatorConsumer.class);
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }
}
