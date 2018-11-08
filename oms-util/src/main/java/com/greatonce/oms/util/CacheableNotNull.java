package com.greatonce.oms.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

/**
 * 缓存非NULL注解.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-19
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable
public @interface CacheableNotNull {

  @AliasFor(annotation = Cacheable.class)
  String[] value() default {};

  @AliasFor(annotation = Cacheable.class)
  String[] cacheNames() default {};

  @AliasFor(annotation = Cacheable.class)
  String key() default "";

  @AliasFor(annotation = Cacheable.class)
  String keyGenerator() default "";

  @AliasFor(annotation = Cacheable.class)
  String cacheManager() default "";

  @AliasFor(annotation = Cacheable.class)
  String cacheResolver() default "";

  @AliasFor(annotation = Cacheable.class)
  String condition() default "";

  @AliasFor(annotation = Cacheable.class)
  String unless() default "#result == null";

  @AliasFor(annotation = Cacheable.class)
  boolean sync() default false;
}
