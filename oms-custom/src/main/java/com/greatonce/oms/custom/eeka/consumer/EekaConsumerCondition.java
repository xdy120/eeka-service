package com.greatonce.oms.custom.eeka.consumer;

import com.greatonce.oms.custom.eeka.EekaCondition;
import com.greatonce.oms.util.OmsConsumerService;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@OmsConsumerService
@EekaCondition
public @interface EekaConsumerCondition {

}
