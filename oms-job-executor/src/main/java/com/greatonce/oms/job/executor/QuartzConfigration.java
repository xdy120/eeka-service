package com.greatonce.oms.job.executor;

import com.greatonce.core.supports.quartz.AutowireBeanJobFactory;
import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * 任务配置
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/3
 */
@Configuration
public class QuartzConfigration {

  @Bean
  public AutowireBeanJobFactory autowireBeanJobFactory() {
    return new AutowireBeanJobFactory();
  }

  @Bean
  public Properties quartzProperties(
      @Value("${oms.quartz.config:config/quartz.properties}") String quartzPath)
      throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource(quartzPath));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }

  /**
   * attention:
   * Details：定义quartz调度工厂
   */
  @Bean(name = "globalScheduler")
  public SchedulerFactoryBean schedulerFactory(Properties quartzProperties) {
    SchedulerFactoryBean bean = new SchedulerFactoryBean();
    bean.setJobFactory(autowireBeanJobFactory());
    bean.setQuartzProperties(quartzProperties);
    bean.setOverwriteExistingJobs(true);
    bean.setStartupDelay(1);
    return bean;
  }
}