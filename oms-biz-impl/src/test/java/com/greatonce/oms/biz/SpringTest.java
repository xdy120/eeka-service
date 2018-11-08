package com.greatonce.oms.biz;

import com.greatonce.oms.biz.SpringTest.SpringTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-31
 */
@ActiveProfiles("dev-gusgu-ssh")
@SpringBootTest(classes = SpringTestConfiguration.class)
@RunWith(SpringRunner.class)
public class SpringTest {


  @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
      JpaRepositoriesAutoConfiguration.class}, scanBasePackages = "com.greatonce")
  public static class SpringTestConfiguration {
  }
}
