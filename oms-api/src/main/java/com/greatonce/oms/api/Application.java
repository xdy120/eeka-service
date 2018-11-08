package com.greatonce.oms.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * api接口启动类.
 *
 * @author ginta
 */
@EnableCaching
@EnableAsync
@EnableWebMvc
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,
    SecurityAutoConfiguration.class,
    QuartzAutoConfiguration.class})
@ComponentScan(basePackages = "com.greatonce", excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "com.greatonce.oms.search..*"))
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
