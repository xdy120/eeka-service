package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.DefaultCodeGenerator;
import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author buer
 * @version 2017-12-28 15:45
 */
@Configuration
public class TradeConfiguration {

  @Value("${sequence.id.workid:1}")
  int idWorkid;
  @Value("${sequence.code.workid:1}")
  int codeWorkid;

  @Bean
  public IdGenerator salesOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator salesOrderDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator salesOrderPaymentIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator salesOrderDiscountIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator salesOrderMarketingIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator salesOrderInvoiceIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator dispatchOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator dispatchOrderDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator dispatchOrderDeliveryIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator returnSignIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator returnOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator returnOrderCodeGenerator() {
    return new DefaultCodeGenerator("RTO", codeWorkid);
  }

  @Bean
  public IdGenerator returnOrderDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator returnOrderOutDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator returnNoticeOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator returnNoticeOrderCodeGenerator() {
    return new DefaultCodeGenerator("RNO", codeWorkid);
  }

  @Bean
  public IdGenerator returnNoticeOrderDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator refundOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator exchangeOrderIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator refundOrderCodeGenerator() {
    return new DefaultCodeGenerator("RFO", codeWorkid);
  }

  @Bean
  public IdGenerator refundOrderDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator salesOrderCodeGenerator() {
    return new DefaultCodeGenerator("SO", codeWorkid);
  }

  @Bean
  public CodeGenerator dispatchOrderCodeGenerator() {
    return new DefaultCodeGenerator("DO", codeWorkid);
  }

  @Bean
  public IdGenerator salesOrderDeliveryInfoIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator dispatchOrderDetailExpressIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator exchangeOrderCodeGenerator() {
    return new DefaultCodeGenerator("EO", codeWorkid);
  }

}
