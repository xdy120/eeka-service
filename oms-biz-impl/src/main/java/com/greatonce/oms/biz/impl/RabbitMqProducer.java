package com.greatonce.oms.biz.impl;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.message.Message;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息模板包装类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/9
 */
@Component
public class RabbitMqProducer implements MqProducer {

  @Resource
  private RabbitTemplate rabbitTemplate;

  /**
   * 发送消息.
   *
   * @param message 消息
   */
  @Override
  public void send(Message message) {
    rabbitTemplate.convertAndSend(message.exchange(), message.routingKey(), message);
  }

  @Override
  public void send(Collection<? extends Message> messages) {
    messages.forEach(this::send);
  }
}
