package com.greatonce.oms.consumer.trade;

import com.greatonce.oms.consumer.OrderNoticeCondition;
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
@OrderNoticeCondition
public class TradeNoticeConsumerConfiguration implements ApplicationContextAware {

  @Value("${oms.consumer.notice.dispatch-num:1}")
  private Integer dispatchNoticeConsumerNum;
  @Value("${oms.consumer.notice.return-num:1}")
  private Integer returnNoticeConsumerNum;

  private ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    createConsumer();
  }

  private void createConsumer() {
    for (Integer i = 0; i < this.dispatchNoticeConsumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(DispatchOrderNoticeConsumer.class);
    }

    for (Integer i = 0; i < this.returnNoticeConsumerNum; i++) {
      this.context.getAutowireCapableBeanFactory().createBean(ReturnOrderNoticeConsumer.class);
    }
  }
}
