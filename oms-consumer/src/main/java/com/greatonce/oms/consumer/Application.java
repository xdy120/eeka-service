package com.greatonce.oms.consumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableRabbit
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class,
    QuartzAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class},
    scanBasePackages = "com.greatonce")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
