package com.greatonce.oms.consumer.vip;

import com.greatonce.oms.biz.impl.vip.VipMqConfiguration;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.consumer.OrderNoticeCondition;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.message.vip.VipReturnNoticeCreateMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 唯品退货单通知WMS.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@Component
@OrderNoticeCondition
@RabbitListener(queues = VipMqConfiguration.QUEUE_VIP_RETURN_NOTICE_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class VipReturnOrderNoticeConsumer extends AbstractConsumer {

  @Autowired
  private VipReturnNoticeService vipReturnNoticeService;

  @RabbitHandler
  void process(VipReturnNoticeCreateMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      VipReturnNotice vipReturnNotice = vipReturnNoticeService
          .getByKey(message.getVipReturnNoticeId());
      vipReturnNoticeService.noticeWms(vipReturnNotice);
    });
  }
}
