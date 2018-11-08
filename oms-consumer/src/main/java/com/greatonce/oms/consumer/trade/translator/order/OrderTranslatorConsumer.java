package com.greatonce.oms.consumer.trade.translator.order;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.message.trade.MallSalesOrderMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 订单转化队列消费.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/6
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_SALES_ORDER_TRANSLATE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class OrderTranslatorConsumer extends AbstractConsumer {

  @Autowired
  private IdGenerator translatorIdGenerator;
  @Autowired
  private MallSalesOrderService mallSalesOrderService;
  @Autowired
  private OrderTranslator orderTranslator;

  @RabbitHandler
  void process(MallSalesOrderMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      MallSalesOrder mallSalesOrder = mallSalesOrderService.getByKey(message.getMallSalesOrderId());
      OrderTranslatableContext context = new OrderTranslatableContext(translatorIdGenerator.next(),
          mallSalesOrder);
      orderTranslator.translate(context);
    });
  }
}
