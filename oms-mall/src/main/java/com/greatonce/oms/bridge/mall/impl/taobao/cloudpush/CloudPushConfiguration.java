package com.greatonce.oms.bridge.mall.impl.taobao.cloudpush;

import com.alibaba.druid.pool.DruidDataSource;
import com.greatonce.core.database.mybatis.ConfigurationFactoryBean;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 云推配置类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
@org.springframework.context.annotation.Configuration
public class CloudPushConfiguration {

  @Resource
  DataSource rdsDataSource;
  @Resource
  Configuration rdsConfiguration;
  @Resource
  SqlSessionFactory rdsSqlSessionFactory;

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.rds")
  public DataSource rdsDataSource() {
    return DataSourceBuilder.create().type(DruidDataSource.class).build();
  }

  @Bean
  public Configuration rdsConfiguration() throws Exception {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    ConfigurationFactoryBean factoryBean = new ConfigurationFactoryBean();
    factoryBean.setConfigLocation(resourceLoader
        .getResource(
            "classpath:com/greatonce/oms/bridge/mall/impl/taobao/cloudpush/mybatis-config.xml"));
    return factoryBean.getObject();
  }

  @Bean
  public SqlSessionFactory rdsSqlSessionFactory() throws Exception {
    ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(rdsDataSource);
    factoryBean.setConfiguration(rdsConfiguration);
    factoryBean.setMapperLocations(resourceLoader.getResources(
        "classpath:com/greatonce/oms/bridge/mall/impl/taobao/cloudpush/mappers/*.xml"));
    factoryBean.afterPropertiesSet();
    return factoryBean.getObject();
  }

  @Bean
  public SqlSessionDecorator rdsSqlSessionDecorator() throws Exception {
    return new SqlSessionDecorator(new SqlSessionTemplate(rdsSqlSessionFactory));
  }
}
