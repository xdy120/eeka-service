package com.greatonce.oms.biz.impl;

import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.supports.amqp.FastJsonMessageConverter;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 业务配置.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
@Configuration
public class BizConfiguration {

  @Value("${sequence.id.workid:1}")
  private int idWorkId;

  @Value("${oms.biz.task.pool.size:5}")
  private int bizCorePoolSize;
  @Value("${oms.biz.task.max.pool.size:20}")
  private int bizMaxPoolSize;
  @Value("${oms.biz.task.queue.capacity:5}")
  private int bizQueueCapacity;
  @Value("${oms.biz.task.keep.alive.seconds:3000}")
  private int bizKeepAliveSeconds;


  /**
   * MQ模板.
   */
  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(new FastJsonMessageConverter());
    return rabbitTemplate;
  }


  @Bean
  @Primary
  public IdGenerator idGenerator() {
    return new DefaultIdGenerator(idWorkId);
  }

  /**
   * 默认的业务线程池.
   */
  @Bean
  public AsyncTaskExecutor bizExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(bizCorePoolSize);
    executor.setMaxPoolSize(bizMaxPoolSize);
    executor.setQueueCapacity(bizQueueCapacity);
    executor.setThreadNamePrefix("oms-biz-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setKeepAliveSeconds(bizKeepAliveSeconds);
    return executor;
  }
}
