package com.greatonce.oms.consumer;

import com.greatonce.core.supports.amqp.FastJsonMessageConverter;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/6/2
 */
@Configuration
public class ConsumerConfiguration {
//
//  @Bean
//  public AsyncTaskExecutor consumerExecutor(
//      @Value("${oms.consumer.task.corePoolSize:16}") int corePoolSize,
//      @Value("${oms.consumer.task.maxPoolSize:32}") int maxPoolSize,
//      @Value("${oms.consumer.task.queueCapacity:64}") int queueCapacity,
//      @Value("${oms.consumer.task.keepAliveSeconds:60000}") int keepAliveSeconds,
//      @Value("${oms.consumer.task.prefix:oms-consumer}") String prefix
//  ) {
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setCorePoolSize(corePoolSize);
//    executor.setMaxPoolSize(maxPoolSize);
//    executor.setQueueCapacity(queueCapacity);
//    executor.setThreadNamePrefix(prefix);
//    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//    executor.setKeepAliveSeconds(keepAliveSeconds);
//    return executor;
//  }

  @Bean
  @Primary
  public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties properties,
      ObjectProvider<ConnectionNameStrategy> connectionNameStrategy) throws Exception {
    return getConnectionFactory(properties, connectionNameStrategy);
  }

  @Bean
  public CachingConnectionFactory consumerConnectionFactory(RabbitProperties properties,
      ObjectProvider<ConnectionNameStrategy> connectionNameStrategy) throws Exception {
    return getConnectionFactory(properties, connectionNameStrategy);
  }


  private CachingConnectionFactory getConnectionFactory(
      RabbitProperties properties,
      ObjectProvider<ConnectionNameStrategy> connectionNameStrategy)
      throws Exception {
    PropertyMapper map = PropertyMapper.get();
    CachingConnectionFactory factory = new CachingConnectionFactory(
        Objects.requireNonNull(getRabbitConnectionFactoryBean(properties).getObject()));
    map.from(properties::determineAddresses).to(factory::setAddresses);
    map.from(properties::isPublisherConfirms).to(factory::setPublisherConfirms);
    map.from(properties::isPublisherReturns).to(factory::setPublisherReturns);
    RabbitProperties.Cache.Channel channel = properties.getCache().getChannel();
    map.from(channel::getSize).whenNonNull().to(factory::setChannelCacheSize);
    map.from(channel::getCheckoutTimeout).whenNonNull().as(Duration::toMillis)
        .to(factory::setChannelCheckoutTimeout);
    RabbitProperties.Cache.Connection connection = properties.getCache()
        .getConnection();
    map.from(connection::getMode).whenNonNull().to(factory::setCacheMode);
    map.from(connection::getSize).whenNonNull()
        .to(factory::setConnectionCacheSize);
    map.from(connectionNameStrategy::getIfUnique).whenNonNull()
        .to(factory::setConnectionNameStrategy);
    return factory;
  }

  private RabbitConnectionFactoryBean getRabbitConnectionFactoryBean(
      RabbitProperties properties) throws Exception {
    PropertyMapper map = PropertyMapper.get();
    RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
    map.from(properties::determineHost).whenNonNull().to(factory::setHost);
    map.from(properties::determinePort).to(factory::setPort);
    map.from(properties::determineUsername).whenNonNull()
        .to(factory::setUsername);
    map.from(properties::determinePassword).whenNonNull()
        .to(factory::setPassword);
    map.from(properties::determineVirtualHost).whenNonNull()
        .to(factory::setVirtualHost);
    map.from(properties::getRequestedHeartbeat).whenNonNull()
        .asInt(Duration::getSeconds).to(factory::setRequestedHeartbeat);
    RabbitProperties.Ssl ssl = properties.getSsl();
    if (ssl.isEnabled()) {
      factory.setUseSSL(true);
      map.from(ssl::getAlgorithm).whenNonNull().to(factory::setSslAlgorithm);
      map.from(ssl::getKeyStoreType).to(factory::setKeyStoreType);
      map.from(ssl::getKeyStore).to(factory::setKeyStore);
      map.from(ssl::getKeyStorePassword).to(factory::setKeyStorePassphrase);
      map.from(ssl::getTrustStoreType).to(factory::setTrustStoreType);
      map.from(ssl::getTrustStore).to(factory::setTrustStore);
      map.from(ssl::getTrustStorePassword).to(factory::setTrustStorePassphrase);
    }
    map.from(properties::getConnectionTimeout).whenNonNull()
        .asInt(Duration::toMillis).to(factory::setConnectionTimeout);
    factory.afterPropertiesSet();
    return factory;
  }


//  /**
//   * MQ监听.
//   */
//  @Bean
//  public SimpleRabbitListenerContainerFactory jsaFactory(
//      @Qualifier("consumerConnectionFactory") ConnectionFactory consumerConnectionFactory,
//      @Qualifier("consumerExecutor") AsyncTaskExecutor consumerExecutor,
//      SimpleRabbitListenerContainerFactoryConfigurer configurer) {
//    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//    factory.setTaskExecutor(consumerExecutor);
//    factory.setMessageConverter(new FastJsonMessageConverter());
//    configurer.configure(factory, consumerConnectionFactory);
//    return factory;
//  }


  /**
   * MQ监听.
   */
  @Bean
  public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory consumerConnectionFactory,
      SimpleRabbitListenerContainerFactoryConfigurer configurer) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    factory.setMessageConverter(new FastJsonMessageConverter());
    configurer.configure(factory, consumerConnectionFactory);
    return factory;
  }
}
