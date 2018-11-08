package com.greatonce.oms.consumer.vip;

import com.greatonce.oms.biz.impl.vip.VipMqConfiguration;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.consumer.OrderNoticeCondition;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.message.vip.VipDispatchDeliveryMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 唯品发货单导入消费者.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-19
 */
@Component
@OrderNoticeCondition
@RabbitListener(queues = VipMqConfiguration.QUEUE_VIP_MALL_DELIVERY, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class VipDeliveryImportConsumer extends AbstractConsumer {

  @Autowired
  private VipDeliveryService vipDeliveryService;

  @RabbitHandler
  void process(VipDispatchDeliveryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (message.getOutStatus() == OutStatus.ALL_OUT) {
        VipDelivery vipDelivery = vipDeliveryService.getByKey(message.getVipDeliveryId());
        VersionBO versionBO = new VersionBO();
        versionBO.setVersion(vipDelivery.getVersion());
        vipDeliveryService.delivery(vipDelivery, versionBO);
      }
    });
  }
}