package com.greatonce.oms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动类.
 *
 * @author ginta
 */
@EnableAsync
@EnableWebMvc
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
    JpaRepositoriesAutoConfiguration.class}, scanBasePackages = "com.greatonce")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
