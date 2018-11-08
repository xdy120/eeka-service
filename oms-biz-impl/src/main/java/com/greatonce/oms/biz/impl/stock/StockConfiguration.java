package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.DefaultCodeGenerator;
import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 库存配置.
 *
 * @author buer
 * @version 2017-12-28 15:43
 */
@Configuration
public class StockConfiguration {

  @Value("${sequence.id.workid:1}")
  int idWorkid;
  @Value("${sequence.code.workid:1}")
  int codeWorkid;

  @Bean
  public IdGenerator stockOccupancyIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockTramsotIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockVirtualAllotIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator stockVirtualAllotCodeGenerator() {
    return new DefaultCodeGenerator("VA", codeWorkid);
  }

  @Bean
  public IdGenerator stockVirtualAllotDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockOutIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator stockOutCodeGenerator() {
    return new DefaultCodeGenerator("OO", codeWorkid);
  }

  @Bean
  public IdGenerator stockOutDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockInIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator stockInCodeGenerator() {
    return new DefaultCodeGenerator("IO", codeWorkid);
  }

  @Bean
  public IdGenerator stockInDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockLoanOutIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockLoanInIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockLoanOutDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockLoanInDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator stockLoanOutCodeGenerator() {
    return new DefaultCodeGenerator("LO", codeWorkid);
  }

  @Bean
  public CodeGenerator stockLoanInCodeGenerator() {
    return new DefaultCodeGenerator("LI", codeWorkid);
  }

  @Bean
  public IdGenerator stockOutBatchIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockOutBatchDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockInBatchIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator stockInBatchDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }
}
