package com.greatonce.oms.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
@Configuration
public class HttpElasticsearchConfiguration {

  @Value("${oms.elasticsearch.host:localhost:9200}")
  private String httpHost;
  @Value("${oms.elasticsearch.connect-time-out:2000}")
  private Integer connectionTimeOut;
  @Value("${oms.elasticsearch.socket-time-out:5000}")
  private Integer socketTimeOut;
  @Value("${oms.elasticsearch.connect-request-time-out:5000}")
  private Integer connectionRequestTimeOut;

  @Bean
  public RestHighLevelClient restHighLevelClient() {
    RestClientBuilder builder = RestClient.builder(HttpHost.create(httpHost));
    builder.setRequestConfigCallback(requestConfigBuilder -> {
      requestConfigBuilder.setConnectTimeout(connectionTimeOut);
      requestConfigBuilder.setSocketTimeout(socketTimeOut);
      requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
      return requestConfigBuilder;
    });
    return new RestHighLevelClient(builder);
  }
}
