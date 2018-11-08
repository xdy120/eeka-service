package com.greatonce.oms.consumer.vip;

import com.greatonce.oms.biz.impl.vip.VipMqConfiguration;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.consumer.OrderNoticeCondition;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.message.vip.VipDispatchBindMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 唯品发货单通知WMS.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@Component
@OrderNoticeCondition
@RabbitListener(queues = VipMqConfiguration.QUEUE_VIP_DISPATCH_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class VipDispatchOrderNoticeConsumer extends AbstractConsumer {

  @Autowired
  private VipDispatchService vipDispatchService;


  @RabbitHandler
  void process(VipDispatchBindMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      VipDispatch vipDispatch = vipDispatchService.getByKey(message.getVipDispatchId());
      vipDispatchService.noticeWms(vipDispatch);
    });
  }
}
