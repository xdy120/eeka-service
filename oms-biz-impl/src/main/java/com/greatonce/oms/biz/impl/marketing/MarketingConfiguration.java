package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.DefaultCodeGenerator;
import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 营销配置类.
 *
 * @author buer
 * @version 2017-12-28 15:43
 */
@Configuration
public class MarketingConfiguration {

  @Value("${sequence.id.workid:1}")
  private int idWorkid;
  @Value("${sequence.code.workid:1}")
  private int codeWorkid;

  @Bean
  public CodeGenerator giftStrategyCodeGenerator() {
    return new DefaultCodeGenerator("GS", codeWorkid);
  }

  @Bean
  public IdGenerator activityIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator activityCodeGenerator() {
    return new DefaultCodeGenerator("AR", codeWorkid);
  }

  @Bean
  public IdGenerator activityDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator presellIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator presellDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator presellStoreIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator presellStoreDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator presellCodeGenerator() {
    return new DefaultCodeGenerator("PR", codeWorkid);
  }
}
