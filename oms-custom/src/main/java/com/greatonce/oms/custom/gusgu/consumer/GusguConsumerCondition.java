package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.oms.custom.gusgu.GusguCondition;
import com.greatonce.oms.util.OmsConsumerService;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@OmsConsumerService
@GusguCondition
@ConditionalOnProperty(value = "oms.consumer.custom.kingdee.enabled", havingValue = "true")
public @interface GusguConsumerCondition {

}
