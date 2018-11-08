package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.DefaultCodeGenerator;
import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author buer
 * @version 2017-12-28 15:43
 */
@Configuration
public class PurchaseConfiguration {

  @Value("${sequence.id.workid:1}")
  int idWorkid;
  @Value("${sequence.code.workid:1}")
  int codeWorkid;

  @Bean
  public IdGenerator supplierIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator purchaseOrderCodeGenerator() throws Exception {
    return new DefaultCodeGenerator("PO", codeWorkid);
  }

  @Bean
  public IdGenerator purchaseOrderIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator purchaseOrderDetailIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator purchaseNoticeOrderCodeGenerator() throws Exception {
    return new DefaultCodeGenerator("PNO", codeWorkid);
  }

  @Bean
  public IdGenerator purchaseNoticeOrderIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator purchaseNoticeOrderDetailIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator purchaseReturnOrderCodeGenerator() throws Exception {
    return new DefaultCodeGenerator("PRO", codeWorkid);
  }

  @Bean
  public IdGenerator purchaseReturnOrderIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator purchaseReturnOrderDetailIdGenerator() throws Exception {
    return new DefaultIdGenerator(idWorkid);
  }
}
