package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.DefaultCodeGenerator;
import com.greatonce.core.sequence.DefaultIdGenerator;
import com.greatonce.core.sequence.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 唯品配置.
 *
 * @author buer
 * @version 2017-12-28 15:44
 */
@Configuration
public class VipConfiguration {

  @Value("${sequence.id.workid:1}")
  int idWorkid;
  @Value("${sequence.code.workid:1}")
  int codeWorkid;

  @Bean
  public IdGenerator vipScheduleIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator vipScheduleCodeGenerator() {
    return new DefaultCodeGenerator("VS", codeWorkid);
  }

  @Bean
  public IdGenerator vipScheduleDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator vipAdjustIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator vipAdjustCodeGenerator() {
    return new DefaultCodeGenerator("VA", codeWorkid);
  }

  @Bean
  public IdGenerator vipAdjustDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator vipDispatchIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator vipDispatchCodeGenerator() {
    return new DefaultCodeGenerator("VD", codeWorkid);
  }

  @Bean
  public IdGenerator vipDispatchDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator vipDeliveryIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator vipDeliveryCodeGenerator() {
    return new DefaultCodeGenerator("VL", codeWorkid);
  }

  @Bean
  public IdGenerator vipReturnIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public IdGenerator vipReturnDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }


  @Bean
  public IdGenerator vipReturnNoticeIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator vipReturnCodeGenerator() {
    return new DefaultCodeGenerator("VR", codeWorkid);
  }

  @Bean
  public IdGenerator vipReturnNoticeDetailIdGenerator() {
    return new DefaultIdGenerator(idWorkid);
  }

  @Bean
  public CodeGenerator vipReturnNoticeCodeGenerator() {
    return new DefaultCodeGenerator("VRN", codeWorkid);
  }
}
