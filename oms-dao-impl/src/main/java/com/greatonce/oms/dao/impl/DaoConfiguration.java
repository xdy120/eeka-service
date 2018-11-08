package com.greatonce.oms.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.database.mybatis.ConfigurationFactoryBean;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * DaoConfiguration.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-12
 */
@org.springframework.context.annotation.Configuration
public class DaoConfiguration {

  @Resource
  private DataSource omsDataSource;
  @Resource
  private Configuration omsConfiguration;
  @Resource
  private SqlSessionFactory omsSqlSessionFactory;
  @Resource
  private DataSource reportDataSource;
  @Resource
  private Configuration reportConfiguration;
  @Resource
  private SqlSessionFactory reportSqlSessionFactory;
  @Resource
  private DataSource adminDataSource;
  @Resource
  private Configuration adminConfiguration;
  @Resource
  private SqlSessionFactory adminSqlSessionFactory;


  @Bean
  public PlatformTransactionManager platformTransactionManager() {
    return new DataSourceTransactionManager(omsDataSource);
  }

  @Bean
  public ManualTransactionTemplate manualTransactionTemplate(
      PlatformTransactionManager platformTransactionManager) {
    return new ManualTransactionTemplate(platformTransactionManager);
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.oms")
  public DataSource omsDataSource() {
    return DataSourceBuilder.create().type(DruidDataSource.class).build();
  }

  private Configuration createConfiguration(String configLocation) throws Exception {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    ConfigurationFactoryBean factoryBean = new ConfigurationFactoryBean();
    factoryBean.setConfigLocation(resourceLoader.getResource(configLocation));
    factoryBean.setEnumPackage("com.greatonce.oms.domain");
    return factoryBean.getObject();
  }

  private SqlSessionFactory createSqlSessionFactory(DataSource dataSource,
      Configuration configuration, String... mapperLocations) throws Exception {
    ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setConfiguration(configuration);
    if (mapperLocations.length > 1) {
      List<org.springframework.core.io.Resource> resources = new ArrayList<>();
      for (String mapperLocation : mapperLocations) {
        Collections.addAll(resources, resourceLoader
            .getResources(mapperLocation));
      }

      org.springframework.core.io.Resource[] resourcesArray = new org.springframework.core.io.Resource[resources
          .size()];
      resources.toArray(resourcesArray);
      factoryBean.setMapperLocations(resourcesArray);
    } else {
      factoryBean.setMapperLocations(resourceLoader.getResources(mapperLocations[0]));
    }
    factoryBean.afterPropertiesSet();
    return factoryBean.getObject();
  }

  @Bean
  public Configuration omsConfiguration(
      @Value("${spring.mybatis.oms.configLocation:classpath:config/mybatis-config.xml}")
          String configLocation) throws Exception {
    return createConfiguration(configLocation);
  }

  @Bean
  public SqlSessionFactory omsSqlSessionFactory() throws Exception {
    return createSqlSessionFactory(omsDataSource, omsConfiguration,
        "classpath*:mappers/base/**/*.xml",
        "classpath*:mappers/job/**/*.xml",
        "classpath*:mappers/mall/**/*.xml",
        "classpath*:mappers/marketing/**/*.xml",
        "classpath*:mappers/message/**/*.xml",
        "classpath*:mappers/product/**/*.xml",
        "classpath*:mappers/purchase/**/*.xml",
        "classpath*:mappers/stock/**/*.xml",
        "classpath*:mappers/trade/**/*.xml",
        "classpath*:mappers/vip/**/*.xml",
        "classpath*:mappers/finance/**/*.xml");
  }

  @Bean
  public SqlSessionDecorator omsSqlSessionDecorator() {
    return new SqlSessionDecorator(new SqlSessionTemplate(omsSqlSessionFactory));
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.report")
  public DataSource reportDataSource() {
    return DataSourceBuilder.create().type(DruidDataSource.class).build();
  }

  @Bean
  public Configuration reportConfiguration(
      @Value("${spring.mybatis.oms.configLocation:classpath:config/mybatis-config.xml}")
          String configLocation) throws Exception {
    return createConfiguration(configLocation);
  }

  @Bean
  public SqlSessionFactory reportSqlSessionFactory() throws Exception {
    return createSqlSessionFactory(reportDataSource, reportConfiguration,
        "classpath*:mappers/report/**/*.xml");
  }

  @Bean
  public SqlSessionDecorator reportSqlSessionDecorator() {
    return new SqlSessionDecorator(new SqlSessionTemplate(reportSqlSessionFactory));
  }


  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.admin")
  public DataSource adminDataSource() {
    return DataSourceBuilder.create().type(DruidDataSource.class).build();
  }

  @Bean
  public Configuration adminConfiguration(
      @Value("${spring.mybatis.oms.configLocation:classpath:config/mybatis-admin-config.xml}") String configLocation)
      throws Exception {
    return createConfiguration(configLocation);
  }

  @Bean
  public SqlSessionFactory adminSqlSessionFactory() throws Exception {
    return createSqlSessionFactory(adminDataSource, adminConfiguration,
        "classpath*:mappers/admin/**/*.xml");
  }

  @Bean
  public SqlSessionDecorator adminSqlSessionDecorator() {
    return new SqlSessionDecorator(new SqlSessionTemplate(adminSqlSessionFactory));
  }

}
