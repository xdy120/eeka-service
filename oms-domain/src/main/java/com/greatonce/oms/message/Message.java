package com.greatonce.oms.message;

import com.greatonce.core.TenantContext;

/**
 * MQ消息抽象类 实现类需要提供消息发送的exchange，避免发送到默认exchange 实现类需要提供消息的routingkey，用于消息路由
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public abstract class Message {

  /**
   * 租户ID
   */
  private String tenantId;

  public Message() {
    this.tenantId = TenantContext.getTenantId();
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * 返回消息对应的路由器
   *
   * @return ExchangeName
   */
  public String exchange() {
    return "oms";
  }

  /**
   * 返回消息路由键
   *
   * @return RoutingKey
   */
  public abstract String routingKey();

  @Override
  public String toString() {
    return "Msg{" +
        "tenantId='" + tenantId + '\'' +
        "exchange='" + exchange() + '\'' +
        "routingKey='" + routingKey() + '\'' +
        '}';
  }
}
