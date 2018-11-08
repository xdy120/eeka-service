package com.greatonce.oms.consumer.trade;

import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.message.trade.ReturnNoticeOrderCreateMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * B2C退货单通知WMS.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_RETURN_NOTICE_ORDER_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class ReturnOrderNoticeConsumer extends AbstractConsumer {

  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;

  @RabbitHandler
  void process(ReturnNoticeOrderCreateMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService
          .getByKey(message.getReturnNoticeOrderId());
      VersionBO versionBO = new VersionBO();
      versionBO.setVersion(returnNoticeOrder.getVersion());
      returnNoticeOrderService.noticeWms(returnNoticeOrder, versionBO);
    });
  }
}
