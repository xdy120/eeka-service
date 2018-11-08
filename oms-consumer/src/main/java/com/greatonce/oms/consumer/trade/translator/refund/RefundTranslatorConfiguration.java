package com.greatonce.oms.consumer.trade.translator.refund;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 退款单转化.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/30
 */
@Configuration
@TranslatorRefundCondition
public class RefundTranslatorConfiguration implements ApplicationContextAware {

  private ApplicationContext context;

  @Value("${oms.consumer.translate.refund.num:1}")
  private Integer consumerNum;

  private void createConsumer() {
    for (Integer i = 0; i < this.consumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(RefundTranslatorConsumer.class);
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }
}
