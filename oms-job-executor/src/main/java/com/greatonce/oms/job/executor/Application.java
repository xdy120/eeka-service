package com.greatonce.oms.job.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

/**
 * @author buer
 * @version 2018-01-03 16:54
 */
@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class,
    JpaRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages = "com.greatonce", excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "com.greatonce.oms.search..*"))
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
