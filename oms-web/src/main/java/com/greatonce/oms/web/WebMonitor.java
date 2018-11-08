package com.greatonce.oms.web;

import com.greatonce.core.monitor.TimeMonitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 监控.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-15
 */
@Aspect
@Component
@ConditionalOnProperty(value = "oms.monitor")
public class WebMonitor extends TimeMonitor {

  @Override
  @Around("execution(* com.greatonce.oms.web..*(..))")
  protected Object around(ProceedingJoinPoint pjp) throws Throwable {
    return super.around(pjp);
  }
}
