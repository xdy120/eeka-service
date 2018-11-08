package com.greatonce.oms.biz.impl;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.time.Duration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * Redis 配置类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/26
 */
@EnableCaching
@Configuration
public class RedisConfiguration {

  @Bean
  public RedisCacheConfiguration redisCacheConfiguration() {
    GenericFastJsonRedisSerializer redisSerializer = new GenericFastJsonRedisSerializer();
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
    configuration = configuration.serializeValuesWith(
        RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
        .entryTtl(Duration.ofDays(30));
    return configuration;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    GenericFastJsonRedisSerializer redisSerializer = new GenericFastJsonRedisSerializer();
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setDefaultSerializer(redisSerializer);
    return template;
  }
}
