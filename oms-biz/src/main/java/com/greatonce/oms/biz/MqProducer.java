package com.greatonce.oms.biz;

import com.greatonce.oms.message.Message;
import java.util.Collection;

/**
 * 消息生产者.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/6/2
 */
public interface MqProducer {

  /**
   * 发送消息.
   */
  void send(Message message);

  /**
   * 批量发送消息.
   */
  void send(Collection<? extends Message> messages);
}
