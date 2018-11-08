package com.greatonce.oms.consumer.trade;

import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.message.trade.DispatchOrderCreateMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * B2C发货单通知WMS.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_DISPATCH_ORDER_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class DispatchOrderNoticeConsumer extends AbstractConsumer {

  @Autowired
  private DispatchOrderService dispatchOrderService;

  @RabbitHandler
  void process(DispatchOrderCreateMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      DispatchOrder dispatchOrder = dispatchOrderService.getByKey(message.getDispatchOrderId());
      dispatchOrderService.noticeWms(dispatchOrder);
    });
  }
}